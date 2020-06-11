package vcs;

import utils.OperationType;
import utils.OutputWriter;

import java.util.ArrayList;

public class LogOperation extends VcsOperation {
    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public final int execute(Vcs vcs) {
        ArrayList<Commit> commits = vcs.getActiveBranch().getCommits();
        OutputWriter outputWriter = vcs.getOutputWriter();
        for (Commit a : commits) {
            outputWriter.write("Commit id: " + a.getId() + "\n");
            outputWriter.write("Message: " + a.getMessage() + "\n");
            if (commits.indexOf(a) < commits.size() - 1) {
                outputWriter.write("\n");
            }
        }
        return 0;
    }
}
