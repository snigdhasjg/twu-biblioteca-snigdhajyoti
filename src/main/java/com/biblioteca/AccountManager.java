package com.biblioteca;

import com.biblioteca.account.IAccount;

import java.util.List;

public class AccountManager {
    private List<IAccount> allAccounts;

    AccountManager(List<IAccount> allAccounts){
        this.allAccounts = allAccounts;
    }

    public IAccount login(String libraryNumber, String password){
        for(IAccount account: allAccounts){
            if(account.validate(libraryNumber,password)){
                return account;
            }
        }
        return null;
    }

}
