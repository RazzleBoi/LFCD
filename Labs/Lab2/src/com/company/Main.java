package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File input = new File("p1err.in");
        File tokensFile = new File("token.in");
        File PIFfile = new File("PIFfile.out");
        File STout = new File("ST.out");
        SymbolTable symbolTable = new SymbolTable(null);
        LexicalScanner ls = new LexicalScanner(input, tokensFile, PIFfile, STout, symbolTable);

        FiniteAutomaton famenu = new FiniteAutomaton(new File("FAidentifier.txt"));
        famenu.run();
        famenu.load();
        System.out.println(famenu.check("",'q', "gc_"));
        System.out.println(famenu.check("",'q', "qwerty12"));
        System.out.println(famenu.check("",'q', "1wqw"));
        System.out.println(famenu.check("",'q', "a"));
        System.out.println(famenu.check("",'q', "1"));

//        ls.parse();

    }
}
