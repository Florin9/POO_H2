package vcs;

import java.util.ArrayList;

public class Branch {
    private Commit parent;
    private String name;
    private ArrayList<Commit> commits;

    public Branch(Commit parent, String name) {
        this.parent = parent;
        this.name = name;
        this.commits = new ArrayList<>();
    }

    public final Commit getParent() {
        return parent;
    }

    public final String getName() {
        return name;
    }

    public final ArrayList<Commit> getCommits() {
        return commits;
    }
}
