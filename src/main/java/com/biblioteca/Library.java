package com.biblioteca;

import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.items.LibraryItem;

import java.util.ArrayList;
import java.util.List;

// Represents a place which have all items in it
public class Library {

    private List<LibraryItem> availableItems;
    private List<LibraryItem> checkedOutItems;

    public Library(List<LibraryItem> listOfItems) {
        this.availableItems = new ArrayList<>(listOfItems);
        this.checkedOutItems = new ArrayList<>();
    }

    public List<LibraryItem> availableItems() {
        return availableItems;
    }

    public void checkOut(String itemName) throws InvalidItemNameException {
        LibraryItem searchedItem = searchItem(itemName, availableItems);
        availableItems.remove(searchedItem);
        checkedOutItems.add(searchedItem);
    }

    public void checkIn(String itemName) throws InvalidItemNameException {
        LibraryItem searchedItem = searchItem(itemName, checkedOutItems);
        checkedOutItems.remove(searchedItem);
        availableItems.add(searchedItem);
    }

    public boolean isEmpty() {
        return availableItems.size() == 0;
    }

    private LibraryItem searchItem(String itemName, List<LibraryItem> someGroupOfItem) throws InvalidItemNameException {
        for (LibraryItem eachItem : someGroupOfItem) {
            String eachItemTitle = eachItem.title();
            if (eachItemTitle.equalsIgnoreCase(itemName)) {
                return eachItem;
            }
        }
        throw new InvalidItemNameException();
    }
}
