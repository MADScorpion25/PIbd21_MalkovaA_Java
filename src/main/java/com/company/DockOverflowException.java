package com.company;

public class DockOverflowException extends Exception{
    public DockOverflowException() {
        super("Dock hasn't got free places.");
    }
}
