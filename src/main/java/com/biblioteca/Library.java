package com.biblioteca;

import com.biblioteca.account.IAccount;
import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.items.LibraryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Represents a place which have all items in it
public class Library {

    private List<LibraryItem> availableItems;
    private Map<LibraryItem, IAccount> checkedOutItems;

    public Library(List<LibraryItem> listOfItems) {
        this.availableItems = new ArrayList<>(listOfItems);
        this.checkedOutItems = new HashMap<>();
    }

    public List<LibraryItem> availableItems() {
        return availableItems;
    }

    public void checkOut(String itemName, IAccount accountSession) throws InvalidItemNameException {
        LibraryItem searchedItem = searchAvailableItem(itemName);
        availableItems.remove(searchedItem);
        checkedOutItems.put(searchedItem, accountSession);
    }

    public void checkIn(String itemName, IAccount accountSession) throws InvalidItemNameException {
        LibraryItem searchedItem = searchCheckedOutItem(itemName, accountSession);
        checkedOutItems.remove(searchedItem);
        availableItems.add(searchedItem);
    }

    public boolean isEmpty() {
        return availableItems.size() == 0;
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

    private LibraryItem searchCheckedOutItem(String itemName, IAccount accountSession) throws InvalidItemNameException{
        for(LibraryItem eachItem: checkedOutItems.keySet()){
            String eachItemTitle = eachItem.title();
            if (eachItemTitle.equalsIgnoreCase(itemName)) {
                IAccount account = checkedOutItems.get(eachItem);
                if (account.equals(accountSession)) {
                    return eachItem;
                }
            }
        }
        throw new InvalidItemNameException();
    }
}
