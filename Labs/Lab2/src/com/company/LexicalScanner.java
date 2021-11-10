package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LexicalScanner {
    Scanner myReader;
    List<String> reservedtokens;
    Pattern identifierPattern;
    Pattern stringPattern;
    Pattern intPattern;
    File input;
    File tokensFile;
    File PIFfile;
    File STout;
    SymbolTable symbolTable;

    public LexicalScanner(File input, File tokensFile, File PIFfile, File STout, SymbolTable symbolTable) {
        this.input = input;
        this.tokensFile = tokensFile;
        this.PIFfile = PIFfile;
        this.STout = STout;
        this.symbolTable = symbolTable;

        this.reservedtokens = new LinkedList<>();
        this.identifierPattern = Pattern.compile("^[A-Za-z]+[0-9]*$");
        this.stringPattern = Pattern.compile("^\"[A-Za-z]+\"$");
        this.intPattern = Pattern.compile("^[1-9]+[0-9]*$");
    }

    public void parse() {
        try {
            myReader = new Scanner(tokensFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        while (myReader.hasNextLine()) {
            reservedtokens.add(myReader.nextLine());
        }
        System.out.println(reservedtokens);
        myReader.close();

        try {
            myReader = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        FiniteAutomaton identifierFA = new FiniteAutomaton(new File("FAidentifier.txt"));
        identifierFA.load();
        FiniteAutomaton integerFA = new FiniteAutomaton(new File("FAinteger.txt"));
        integerFA.load();
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine().replaceAll("(<=|==|!=|>=|&&|\\|\\||\\[|\\(|\\{|}|]|\\)|\\+|-|/|&|=|\\*|\\$|\\||<|>|;|,)", " $1 ");
            List<String> tokens = Arrays.asList(line.split("\\s")).stream().filter(p -> p != "").collect(Collectors.toList());
            System.out.println(tokens);
            for(String token : tokens) {
                if (reservedtokens.contains(token))
                    genPIF(token, 0);
                else if (identifierFA.check("", null, token)) {  //identifierPattern.matcher(token).matches()
                    Integer index = symbolTable.insert(token);
                    genPIF("identifier", index);
                } else if (stringPattern.matcher(token).matches() || integerFA.check("", null, token)) {  //intPattern.matcher(token).matches()
                    System.out.println(integerFA.check("", null, token));
                    Integer index = symbolTable.insert(token);
                    genPIF("const", index);
                } else
                    System.out.println("Lexical error");
            }
        }
        try {
            FileWriter myWriter = new FileWriter(STout.getName(), true);
            myWriter.append(symbolTable.toString());
            myWriter.close();
            System.out.println("Successfully wrote to " +  STout.getName()+"\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void genPIF(String token, int i) {
        try {
            FileWriter myWriter = new FileWriter(PIFfile.getName(), true);
            myWriter.append("(" + token + "," + i + ")\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file. (" + token + "," + i + ")");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
