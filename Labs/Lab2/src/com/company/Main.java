package com.company;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File input = new File("p1err.in");
        File tokensFile = new File("token.in");
        File PIFfile = new File("PIFfile.out");
        File STout = new File("ST.out");
        SymbolTable symbolTable = new SymbolTable(null);
        LexicalScanner ls = new LexicalScanner(input, tokensFile, PIFfile, STout, symbolTable);
        ls.parse();

    }
}
