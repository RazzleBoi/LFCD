package com.company;

public class Main {

    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(null);
        System.out.println(symbolTable.insert("a"));
        System.out.println(symbolTable.insert("b"));
        System.out.println(symbolTable.insert("c"));
        System.out.println(symbolTable.insert("a"));
        System.out.println(symbolTable.insert("a"));
        System.out.println(symbolTable.insert("b"));

    }
}
