package com.company;

public class DockAlreadyHaveException extends Exception{
    public DockAlreadyHaveException() {
        super("This cruiser is already exists");
    }
}
