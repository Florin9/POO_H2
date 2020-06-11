package vcs;

import utils.AbstractOperation;
import utils.OperationType;
import utils.OutputWriter;

import java.util.ArrayList;

public class StatusOperation extends VcsOperation {

    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        OutputWriter outputWriter = vcs.getOutputWriter();
        outputWriter.write("On branch: " + vcs.getActiveBranch().getName() + "\n");
        outputWriter.write("Staged changes:\n");
        //vcs.getOutputWriter().write(vcs.staging.get(0));
        for (AbstractOperation a:vcs.getStaging()) {
            outputWriter.write(getOutputString(a));
        }
        return 0;
    }

    private String getOutputString(AbstractOperation operation) {
        String output = "\t";
        switch (operation.getType()) {
            case TOUCH:
                output += "Created file ";
                break;
            case MAKEDIR:
                output += "Created directory ";
                break;
            case REMOVE:
                output += "Removed ";
                break;
            case CHANGEDIR:
                output += "Changed directory to ";
                break;
            case WRITETOFILE:
                output += "Added \"" + operation.getOperationArgs().get(1) + "\" to file "
                        + operation.getOperationArgs().get(0) + "\n";
                return output;
            default:
                return output;

        }
        output += operation.getOperationArgs().get(1) + "\n";
        return output;
    }
}
