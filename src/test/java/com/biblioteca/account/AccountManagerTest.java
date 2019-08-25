package com.biblioteca.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountManagerTest {

    private AccountManager accountManager;
    private IAccount mockAccount1;
    private IAccount mockAccount2;
    private final String CREDENTIAL ="Credential";

    @BeforeEach
    void setUp() {
        mockAccount1 = mock(IAccount.class);
        mockAccount2 = mock(IAccount.class);
        accountManager = new AccountManager(Arrays.asList(mockAccount1, mockAccount2));
    }

    @Test
    void shouldReturnMockAccount1WhenMockAccountCredentialsAreCorrect(){
        when(mockAccount1.login(CREDENTIAL, CREDENTIAL)).thenReturn(true);

        assertEquals(mockAccount1,accountManager.login(CREDENTIAL, CREDENTIAL));
    }

    @Test
    void shouldReturnMockAccount2WhenAccount2CredentialsAreUsed(){
        when(mockAccount1.login(CREDENTIAL, CREDENTIAL)).thenReturn(false);
        when(mockAccount2.login(CREDENTIAL, CREDENTIAL)).thenReturn(true);

        assertEquals(mockAccount2,accountManager.login(CREDENTIAL, CREDENTIAL));
    }

    @Test
    void shouldReturnNullWhenNoAccountHasThatCredential(){
        when(mockAccount1.login(CREDENTIAL, CREDENTIAL)).thenReturn(false);
        when(mockAccount2.login(CREDENTIAL, CREDENTIAL)).thenReturn(false);

        assertNull(accountManager.login(CREDENTIAL, CREDENTIAL));
    }


}