package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.AbstractOperation;
import utils.IDGenerator;
import utils.OutputWriter;
import utils.Visitor;

import java.util.ArrayList;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private ArrayList<AbstractOperation> staging;
   // private ArrayList<Commit> commits;
    private ArrayList<Branch> branches;
    private Branch activeBranch;
    private Commit activeCommit;
    private Commit parentCommit;
    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);
        parentCommit = new Commit("First commit", IDGenerator.generateCommitID(), this);
        staging = new ArrayList<AbstractOperation>();
       // commits = new ArrayList<>();
       // commits.add(new Commit("", 1, this));
        branches = new ArrayList<>();
        branches.add(new Branch(parentCommit, "master"));
        branches.get(0).getCommits().add(parentCommit);
        activeBranch = branches.get(0);
        activeCommit = parentCommit;
        activeCommit.setBranch(branches.get(0));
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        return vcsOperation.execute(this);
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public ArrayList<AbstractOperation> getStaging() {
        return staging;
    }

    public void clearStaging() {
        this.staging = new ArrayList<AbstractOperation>();
    }

    public FileSystemSnapshot getActiveSnapshot() {
        return activeSnapshot;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public Branch getActiveBranch() {
        return activeBranch;
    }

    public Commit getActiveCommit() {
        return activeCommit;
    }

    public void setActiveSnapshot(FileSystemSnapshot activeSnapshot) {
        this.activeSnapshot = activeSnapshot;
    }

    public void setActiveBranch(Branch activeBranch) {
        this.activeBranch = activeBranch;
    }

    public void setActiveCommit(Commit activeCommit) {
        this.activeCommit = activeCommit;
    }

}
