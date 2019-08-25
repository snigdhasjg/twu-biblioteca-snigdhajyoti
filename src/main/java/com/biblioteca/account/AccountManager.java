package com.biblioteca.account;

import java.util.List;

public class AccountManager {
    private List<IAccount> allAccounts;

    AccountManager(List<IAccount> allAccounts){
        this.allAccounts = allAccounts;
    }

    public IAccount login(String libraryNumber, String password){
        for(IAccount account: allAccounts){
            if(account.login(libraryNumber,password)){
                return account;
            }
        }
        return null;
    }

}
