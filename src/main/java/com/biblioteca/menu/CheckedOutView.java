package com.biblioteca.menu;

import com.biblioteca.account.IAccount;
import com.biblioteca.items.LibraryItem;

import java.util.Map;

public interface CheckedOutView {
    String header();

    String details(LibraryItem item, IAccount anAccount);

    Integer length();

    void display(Map<LibraryItem, IAccount> items);
}
