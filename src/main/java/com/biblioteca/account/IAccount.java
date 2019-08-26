package com.biblioteca.account;

public interface IAccount {
    boolean validate(String _libraryNumber, String _password);

    AccountType getAccountType();

    String getName();
}
