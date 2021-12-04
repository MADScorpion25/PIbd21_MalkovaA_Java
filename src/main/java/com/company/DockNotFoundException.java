package com.company;

public class DockNotFoundException extends Exception{
    public DockNotFoundException(int i) {
        super("Cannot found cruiser at place with index: "+i);
    }
}
