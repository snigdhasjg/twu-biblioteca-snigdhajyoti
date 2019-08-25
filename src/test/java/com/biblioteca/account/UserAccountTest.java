package com.biblioteca.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountTest {

    private UserAccount anAccount;

    @BeforeEach
    void setUp() {
        anAccount = UserAccount.customer("123-ABCD", "password", "John Doe",
                "john.doe@gmail.com", "123567890");
    }

    @Test
    void shouldReturnTrueWhenWhenCredentialsAreCorrect() {
        String correctLibraryNumber = "123-ABCD";
        String correctPassword = "password";
        assertTrue(anAccount.login(correctLibraryNumber, correctPassword));
    }

    @Test
    void shouldReturnFalseWhenWhenLibraryNumberIsCorrectAndPasswordIsWrong() {
        String correctLibraryNumber = "123-ABCD";
        String wrongPassword = "wrong_password";
        assertFalse(anAccount.login(correctLibraryNumber, wrongPassword));
    }

    @Test
    void shouldReturnFalseWhenWhenLibraryNumberIsWrongAndPasswordIsCorrect() {
        String wrongLibraryNumber = "wrong_library_number";
        String correctPassword = "password";
        assertFalse(anAccount.login(wrongLibraryNumber, correctPassword));
    }

    @Test
    void shouldReturnFalseWhenWhenLibraryNumberIsWrongAndPasswordIsWrong() {
        String wrongLibraryNumber = "wrong_library_number";
        String wrongPassword = "wrong_password";
        assertFalse(anAccount.login(wrongLibraryNumber, wrongPassword));
    }

    @Test
    void shouldPrintCustomerInformationWhenLoggedIn() {
        String expectedCustomerInfo = "name= John Doe\n" +
                "email= john.doe@gmail.com\n" +
                "phoneNumber= 123567890";
        assertEquals(expectedCustomerInfo, anAccount.toString());
    }

}