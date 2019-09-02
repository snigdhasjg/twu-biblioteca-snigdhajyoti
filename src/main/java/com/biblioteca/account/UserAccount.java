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
        return this.libraryNumber.equalsIgnoreCase(_libraryNumber) && this.password.equals(_password);
    }

    @Override
    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLibraryNumber() {
        return this.libraryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;
        UserAccount that = (UserAccount) o;
        return libraryNumber.equals(that.libraryNumber) &&
                password.equals(that.password) &&
                name.equals(that.name) &&
                email.equals(that.email) &&
                phoneNumber.equals(that.phoneNumber) &&
                accountType == that.accountType;
    }

    @Override
    public String toString() {
        return String.format("name= %s\nemail= %s\nphoneNumber= %s", name, email, phoneNumber);
    }
}
