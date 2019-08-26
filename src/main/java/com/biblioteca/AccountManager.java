package com.biblioteca;

import com.biblioteca.account.IAccount;

import java.util.List;

public class AccountManager {
    private List<IAccount> allAccounts;
    private IAccount currentSession;

    AccountManager(List<IAccount> allAccounts){
        this.allAccounts = allAccounts;
    }

    public void login(String libraryNumber, String password){
        for(IAccount account: allAccounts){
            if(account.validate(libraryNumber,password)){
                currentSession = account;
                break;
            }
        }
    }

    public void logout(){
        currentSession = null;
    }

    public boolean isLoggedIn(){
        return currentSession != null;
    }

    public IAccount currentUser(){
        return currentSession;
    }

}
