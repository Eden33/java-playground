package com.eden33.reflection;

import com.eden33.reflection.model.User;
import com.eden33.reflection.model.PermissionAnnotations.MethodOperations;
import com.eden33.reflection.model.PermissionAnnotations.OperationType;
import com.eden33.reflection.model.PermissionAnnotations.Permission;
import com.eden33.reflection.model.PermissionAnnotations.Role;

/** Repeatable annotation example. 
 * See {@link com.eden33.reflection.PermissionsChecker} for more details.
 */
@Permission(role = Role.CLERK, allowed = OperationType.READ)
@Permission(role = Role.MANAGER, allowed = {OperationType.READ, OperationType.WRITE})
@Permission(role = Role.SUPPORT_ENGINEER, allowed = {OperationType.READ, OperationType.WRITE, OperationType.DELETE})
public class CompanyDataStore {

    private User user;

    public CompanyDataStore(User user) {
        this.user = user;
    }

    @MethodOperations(OperationType.READ)
    public AccountRecord[] readAccounts(String[] accountIds) throws Throwable {
        PermissionsChecker.checkPermissions(this, "readAccounts");
        AccountRecord[] records = new AccountRecord[accountIds.length];
        for (int i = 0; i < accountIds.length; i++) {
            records[i] = new AccountRecord(accountIds[i], "Account Name " + accountIds[i]);
        }
        return records;
    }

    @MethodOperations({OperationType.READ, OperationType.WRITE})
    public void updateAccount(String accountId, AccountRecord record) throws Throwable {
        PermissionsChecker.checkPermissions(this, "updateAccount");
    }

    @MethodOperations(OperationType.DELETE)
    public void deleteAccount(String accountId) throws Throwable {
        PermissionsChecker.checkPermissions(this, "deleteAccount");
    }

    static class AccountRecord {
        private String accountId;
        private String accountName;

        public AccountRecord(String accountId, String accountName) {
            this.accountId = accountId;
            this.accountName = accountName;
        }

        public String getAccountId() {
            return accountId;
        }

        public String getAccountName() {
            return accountName;
        }
    }
}
