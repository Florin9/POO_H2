package utils;

public enum OperationType {
    CAT,
    CHANGEDIR,
    LIST,
    MAKEDIR,
    REMOVE,
    TOUCH,
    WRITETOFILE,
    PRINT,
    FILESYSTEM_INVALID_OPERATION,
   //VCS CMDS
    STATUS,
    BRANCH,
    COMMIT,
    CHECKOUT,
    LOG,
    ROLLBACK,
    VCS_INVALID_OPERATION
}
