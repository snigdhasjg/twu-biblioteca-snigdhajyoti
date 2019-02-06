package com.biblioteca;

import java.util.Scanner;

// Represents Library Management Engine
public class Main {
    public static void main(String[] args){
        IO aOutPutStream = new ConsoleIO(System.out,new Scanner(System.in));
        new App(aOutPutStream).start();
    }
}
