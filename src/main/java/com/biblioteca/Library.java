package com.biblioteca;

import com.biblioteca.account.IAccount;
import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.exception.UserDoesNotMatchException;
import com.biblioteca.items.LibraryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Represents a place which have all items in it
public class Library {

    private final List<LibraryItem> availableItems;
    private final Map<LibraryItem, IAccount> checkedOutItems;

    public Library(List<LibraryItem> listOfItems) {
        this.availableItems = new ArrayList<>(listOfItems);
        this.checkedOutItems = new HashMap<>();
    }

    public List<LibraryItem> availableItems() {
        return new ArrayList<>(availableItems);
    }

    public Map<LibraryItem, IAccount> checkedOutItems() {
        return new HashMap<>(checkedOutItems);
    }


    public void checkOut(String itemName, IAccount accountSession) throws InvalidItemNameException {
        LibraryItem searchedItem = searchAvailableItem(itemName);
        availableItems.remove(searchedItem);
        checkedOutItems.put(searchedItem, accountSession);
    }

    public void checkIn(String itemName, IAccount accountSession) throws InvalidItemNameException, UserDoesNotMatchException {
        LibraryItem searchedItem = searchCheckedOutItem(itemName, accountSession);
        checkedOutItems.remove(searchedItem);
        availableItems.add(searchedItem);
    }

    public boolean isEmpty() {
        return availableItems.isEmpty();
    }

    public boolean isFull() {
        return checkedOutItems.isEmpty();
    }

    private LibraryItem searchAvailableItem(String itemName) throws InvalidItemNameException {
        for (LibraryItem eachItem : availableItems) {
            String eachItemTitle = eachItem.title();
            if (eachItemTitle.equalsIgnoreCase(itemName)) {
                return eachItem;
            }
        }
        throw new InvalidItemNameException();
    }

    private LibraryItem searchCheckedOutItem(String itemName, IAccount accountSession) throws InvalidItemNameException, UserDoesNotMatchException {
        for (LibraryItem eachItem : checkedOutItems.keySet()) {
            String eachItemTitle = eachItem.title();
            if (eachItemTitle.equalsIgnoreCase(itemName)) {
                IAccount account = checkedOutItems.get(eachItem);
                if (account.equals(accountSession)) {
                    return eachItem;
                }
                throw new UserDoesNotMatchException();
            }
        }
        throw new InvalidItemNameException();
    }
}
