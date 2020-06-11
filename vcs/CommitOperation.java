package vcs;

import utils.ErrorCodeManager;
import utils.IDGenerator;
import utils.OperationType;

import java.util.ArrayList;

public class CommitOperation extends VcsOperation {
    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        if (vcs.getStaging().size() == 0) {
           return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        String message = "";
        for (int i = 1; i < operationArgs.size(); i++) {
            message += operationArgs.get(i);
            if (i < operationArgs.size() - 1) {
                message += " ";
            }
        }
        Commit commit = new Commit(message, IDGenerator.generateCommitID(), vcs);
        vcs.getActiveBranch().getCommits().add(commit);
        vcs.setActiveCommit(commit);
        //vcs.setActiveSnapshot(commit.getSnapshot());
        vcs.clearStaging();

        return 0;
    }
}
