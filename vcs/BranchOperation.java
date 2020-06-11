package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public class BranchOperation extends VcsOperation {
    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        for (Branch a : vcs.getBranches()) {
            if (a.getName().equals(this.operationArgs.get(0))) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        vcs.getBranches().add(new Branch(vcs.getActiveCommit(), operationArgs.get(0)));
        return 0;
    }
}
