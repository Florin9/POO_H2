package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public class CheckoutOperation extends VcsOperation {

    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        if (vcs.getStaging().size() != 0) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }
        if (operationArgs.size() == 1) {
            int ok = 0;
            for (Branch a : vcs.getBranches()) {
                if (a.getName().equals(operationArgs.get(0))) {
                    ok = 1;
                    vcs.setActiveBranch(a);
                }
            }
            if (ok == 0) {
               return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        if (operationArgs.size() == 2) {
            int ok = 0;
            int id = Integer.parseInt(operationArgs.get(1));
            for (Branch branch : vcs.getBranches()) {
                for (Commit commit : branch.getCommits()) {
                    if (commit.getId() == id) {
                        ok = 1;
                        vcs.setActiveBranch(branch);
                        vcs.setActiveCommit(commit);
                        vcs.setActiveSnapshot(commit.getSnapshot());
                        deleteObsoleteCommits(vcs);
                    }
                }
            }
            if (ok == 0) {
                return ErrorCodeManager.VCS_BAD_PATH_CODE;
            }
        }
        return 0;
    }

    private void deleteObsoleteCommits(Vcs vcs) {
        ArrayList commits = vcs.getActiveBranch().getCommits();
        int id = commits.indexOf(vcs.getActiveCommit());
        int i = id + 1;
        while (i < commits.size()) {
            commits.remove(i);
        }
    }
}
