package vcs;

import utils.OperationType;

import java.util.ArrayList;

public class RollbackOperation extends VcsOperation {
    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        vcs.clearStaging();
        vcs.setActiveSnapshot(vcs.getActiveCommit().getSnapshot());
        return 0;
    }
}
