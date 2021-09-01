package com.biblioteca.menu.action;

import com.biblioteca.AccountManager;
import com.biblioteca.account.IAccount;
import com.biblioteca.io.IO;

public class ProfileOption implements Actionable {
    private static final String PROFILE = "Profile";
    private final IO anIOStream;
    private final AccountManager accountManager;

    public ProfileOption(IO anIOStream, AccountManager accountManager) {
        this.anIOStream = anIOStream;
        this.accountManager = accountManager;
    }

    @Override
    public void execute() {
        IAccount userAccount = accountManager.currentUser();
        anIOStream.displayWithNewLine(userAccount.toString());
    }

    @Override
    public String displayName() {
        return PROFILE;
    }
}
