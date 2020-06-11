package vcs;

import filesystem.FileSystemSnapshot;

public class Commit {
    private String message;
    private int id;
    private FileSystemSnapshot snapshot;
    private Branch branch;

    public Commit(String message, int id, Vcs vcs) {
        this.message = message;
        this.id = id;
        this.snapshot = vcs.getActiveSnapshot().cloneFileSystem();
        this.branch = vcs.getActiveBranch();
    }

    public final void setBranch(Branch branch) {
        this.branch = branch;
    }

    public final String getMessage() {
        return message;
    }

    public final int getId() {
        return id;
    }

    public final FileSystemSnapshot getSnapshot() {
        return snapshot;
    }

    public final Branch getBranch() {
        return branch;
    }
}
