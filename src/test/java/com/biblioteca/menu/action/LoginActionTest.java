package com.biblioteca.menu.action;

import com.biblioteca.AccountManager;
import com.biblioteca.io.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginActionTest {
    private IO mockIO;
    private AccountManager accountManager;

    @BeforeEach
    void setUp() {
        mockIO = mock(IO.class);
        accountManager = mock(AccountManager.class);
    }

    @Test
    void expectsDisplayNameListAllBook() {
        Actionable loginAction = new LoginAction(mockIO, accountManager);

        assertEquals("Log In", loginAction.displayName());
    }

    @Test
    void shouldPassTheCorrectParameterWhenTheDetailsAreCorrect(){
        Actionable loginAction = new LoginAction(mockIO, accountManager);
        String libraryNumber = "XXX-XXX";
        String password = "password";

        when(mockIO.readInputAsString()).thenReturn(libraryNumber,password);
        when(accountManager.isLoggedIn()).thenReturn(true);

        loginAction.execute();

        verify(accountManager).login(libraryNumber,password);
        verify(mockIO).displayWithNewLine("Welcome");
    }

    @Test
    void shouldShowErrorWrongCredentialWhenLoginFailed(){
        Actionable loginAction = new LoginAction(mockIO, accountManager);

        when(accountManager.isLoggedIn()).thenReturn(false);

        loginAction.execute();

        verify(mockIO,times(3)).displayWithNewLine("Credentials are wrong\nPlease try again");
    }
}