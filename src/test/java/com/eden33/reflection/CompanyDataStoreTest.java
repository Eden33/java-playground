package com.eden33.reflection;

import com.eden33.reflection.model.PermissionException;
import com.eden33.reflection.model.User;
import com.eden33.reflection.model.PermissionAnnotations.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CompanyDataStoreTest {
    
    private User clerkUser;
    private User managerUser;
    private User supportEngineerUser;
    private CompanyDataStore clerkDataStore;
    private CompanyDataStore managerDataStore;
    private CompanyDataStore supportEngineerDataStore;
    
    @BeforeEach
    public void setup() {
        // Create users with different roles
        clerkUser = new User("John Doe", Role.CLERK);
        managerUser = new User("Jane Smith", Role.MANAGER);
        supportEngineerUser = new User("Mike Johnson", Role.SUPPORT_ENGINEER);
        
        // Create CompanyDataStore instances with different user roles
        clerkDataStore = new CompanyDataStore(clerkUser);
        managerDataStore = new CompanyDataStore(managerUser);
        supportEngineerDataStore = new CompanyDataStore(supportEngineerUser);
    }
    
    /**
     * Helper method to validate account records match the expected account IDs
     * 
     * @param records The account records returned from readAccounts method
     * @param expectedAccountIds The expected account IDs
     */
    private void validateAccountRecords(CompanyDataStore.AccountRecord[] records, String[] expectedAccountIds) {
        assertEquals(expectedAccountIds.length, records.length, "Number of records should match number of account IDs");
        
        for (int i = 0; i < records.length; i++) {
            String expectedId = expectedAccountIds[i];
            assertEquals(expectedId, records[i].getAccountId(), 
                    "Account ID at position " + i + " should match expected value");
            assertEquals("Account Name " + expectedId, records[i].getAccountName(),
                    "Account name at position " + i + " should be properly formatted");
        }
    }

    @Test
    @DisplayName("Clerk should be able to read accounts")
    public void testClerkCanReadAccounts() throws Throwable {
        String[] accountIds = {"ACC001", "ACC002"};
        
        CompanyDataStore.AccountRecord[] records = clerkDataStore.readAccounts(accountIds);
        
        validateAccountRecords(records, accountIds);
    }
    
    @Test
    @DisplayName("Manager should be able to read accounts")
    public void testManagerCanReadAccounts() throws Throwable {
        String[] accountIds = {"ACC003"};
        
        CompanyDataStore.AccountRecord[] records = managerDataStore.readAccounts(accountIds);
        
        validateAccountRecords(records, accountIds);
    }
    
    @Test
    @DisplayName("Support Engineer should be able to read accounts")
    public void testSupportEngineerCanReadAccounts() throws Throwable {
        String[] accountIds = {"ACC004", "ACC005", "ACC006"};
        
        CompanyDataStore.AccountRecord[] records = supportEngineerDataStore.readAccounts(accountIds);
        
        validateAccountRecords(records, accountIds);
    }

        
    @Test
    @DisplayName("Clerk should not be able to update accounts")
    public void testClerkCannotUpdateAccount() throws Throwable {
        String accountId = "ACC001";
        CompanyDataStore.AccountRecord record = new CompanyDataStore.AccountRecord(accountId, "Account Name for: " + accountId);
        
        assertThrows(
            PermissionException.class,
            () -> clerkDataStore.updateAccount(accountId, record),
            "Clerk should not have permission to update accounts"
        );
    }
    
    @Test
    @DisplayName("Manager should be able to update accounts")
    public void testManagerCanUpdateAccount() throws Throwable {
        String accountId = "ACC001";
        CompanyDataStore.AccountRecord record = new CompanyDataStore.AccountRecord(accountId, "Account Name for: " + accountId);
        
        managerDataStore.updateAccount(accountId, record);
    }
    
    @Test
    @DisplayName("Support Engineer should be able to update accounts")
    public void testSupportEngineerCanUpdateAccount() throws Throwable {
        String accountId = "ACC001";
        CompanyDataStore.AccountRecord record = new CompanyDataStore.AccountRecord(accountId, "Account Name for: " + accountId);
        
        supportEngineerDataStore.updateAccount(accountId, record);
    }    
    
    @Test
    @DisplayName("Clerk should not be able to delete accounts")
    public void testClerkCannotDeleteAccount() {
        String accountId = "ACC001";
        
        assertThrows(
            PermissionException.class,
            () -> clerkDataStore.deleteAccount(accountId),
            "Clerk should not have permission to delete accounts"
        );
    }

    @Test
    @DisplayName("Manager should not be able to delete accounts")
    public void testManagerCannotDeleteAccount() {
        String accountId = "ACC001";
        
        assertThrows(
            PermissionException.class,
            () -> managerDataStore.deleteAccount(accountId),
            "Manager should not have permission to delete accounts"
        );
    }

    @Test
    @DisplayName("Support Engineer should be able to delete accounts")
    public void testSupportEngineerCanDeleteAccount() throws Throwable {
        String accountId = "ACC001";
        
        assertDoesNotThrow(
            () -> supportEngineerDataStore.deleteAccount(accountId),
            "Support Engineer should have permission to delete accounts"
        );
    }
}