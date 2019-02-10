package com.biblioteca;

import com.biblioteca.exception.InvalidItemNameException;
import com.biblioteca.items.LibraryItems;

import java.util.ArrayList;
import java.util.List;

// Represents a place which have all items in it
public class Library {

    private List<LibraryItems> availableItems;
    private List<LibraryItems> checkedOutItems;

    public Library(List<LibraryItems> listOfItems) {
        this.availableItems = new ArrayList<>(listOfItems);
        this.checkedOutItems = new ArrayList<>();
    }

    public List<LibraryItems> listOfAvailableItems() {
        return availableItems;
    }

    public void checkOut(String itemName) throws InvalidItemNameException {
        LibraryItems searchedItem = searchItem(itemName, availableItems);
        availableItems.remove(searchedItem);
        checkedOutItems.add(searchedItem);
    }

    public void checkIn(String itemName) throws InvalidItemNameException {
        LibraryItems searchedItem = searchItem(itemName, checkedOutItems);
        checkedOutItems.remove(searchedItem);
        availableItems.add(searchedItem);
    }

    public boolean isEmpty() {
        return availableItems.size() == 0;
    }

    private LibraryItems searchItem(String itemName, List<LibraryItems> someGroupOfItem) throws InvalidItemNameException {
        for (LibraryItems eachItem : someGroupOfItem) {
            String eachItemTitle = eachItem.title();
            if (eachItemTitle.equalsIgnoreCase(itemName)) {
                return eachItem;
            }
        }
        throw new InvalidItemNameException();
    }
}
