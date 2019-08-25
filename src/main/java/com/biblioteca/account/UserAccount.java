package com.biblioteca.account;

public class UserAccount implements IAccount {
    private final String libraryNumber;
    private final String password;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final AccountType accountType;

    private UserAccount(String libraryNumber, String password, String name, String email, String phoneNumber, AccountType accountType) {
        this.libraryNumber = libraryNumber; //xxx-xxxx
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountType = accountType;
    }

    public static UserAccount customer(String libraryNumber, String password, String name, String email, String phoneNumber){
        return new UserAccount(libraryNumber,password,name,email,phoneNumber, AccountType.customer);
    }

    public static UserAccount librarian(String libraryNumber, String password, String name, String email, String phoneNumber){
        return new UserAccount(libraryNumber,password,name,email,phoneNumber, AccountType.librarian);
    }

    @Override
    public boolean validate(String _libraryNumber, String _password) {
        return this.libraryNumber.equals(_libraryNumber) && this.password.equals(_password);
    }

    @Override
    public String toString() {
        return String.format("name= %s\nemail= %s\nphoneNumber= %s", name, email, phoneNumber);
    }
}
