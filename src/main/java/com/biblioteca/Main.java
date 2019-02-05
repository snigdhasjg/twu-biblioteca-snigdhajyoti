package com.biblioteca;

// Represents Library Management Engine
public class Main {
    public static void main(String[] args){
        IO aOutPutStream = new ConsoleIO(System.out);
        new App(aOutPutStream).welcome();
    }
}
