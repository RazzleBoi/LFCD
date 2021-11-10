package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FiniteAutomaton {
    Scanner myReader;

    public FiniteAutomaton(File inputFA) {
        this.inputFA = inputFA;
    }

    List<String> states = new ArrayList<>();
    List<String> alphabet = new ArrayList<>();
    List<List<String>> transitions = new ArrayList<>();
    List<String> finals = new ArrayList<>();
    String initial = "";
    File inputFA;


    private void printMenu() {
        System.out.println("1: Display the states");
        System.out.println("2: Display the initial");
        System.out.println("3: Display the alphabet");
        System.out.println("4: Display the transitions");
        System.out.println("5: Display the final state(s)");
//        System.out.println("6: Check sequence");
        System.out.println("\n0: Exit");
    }


    private void classify(String probe, String mode) {
        switch (mode) {
            case "~states" -> states.addAll(Arrays.stream(probe.split(", ")).collect(Collectors.toList()));
            case "~initial" -> initial = probe;
            case "~alpha" -> alphabet.addAll(Arrays.stream(probe.split(", ")).collect(Collectors.toList()));
            case "~trans" -> {
                probe = probe.replace(" ", "");
                transitions.add(Arrays.stream(probe.split("~")).collect(Collectors.toList()));
            }
            case "~final" -> finals.addAll(Arrays.stream(probe.split(", ")).collect(Collectors.toList()));
        }
    }


    public Boolean check(String currentState, Character c, String sequence) {
//        System.out.println(currentState + " " + sequence);
        if(sequence.equals("") && c != null) {
            var validTransitions = transitions.stream()
                    .filter(p -> p.get(0).equals(currentState))
                    .filter(p -> p.get(1).contains(c.toString()))
                    .collect(Collectors.toList());
            return !validTransitions.isEmpty();
        }
        else if(sequence.equals("")) {

            return finals.contains(currentState);
        }
        else if(currentState.equals(""))
            return check(initial, sequence.charAt(0), sequence.substring(1));
        else {
            var validTransitions = transitions.stream()
                    .filter(p -> p.get(0).equals(currentState))
                    .filter(p -> p.get(1).contains(c.toString()))
                    .collect(Collectors.toList());
//            System.out.println(validTransitions);
            if (validTransitions.isEmpty())
                return false;
            return check(validTransitions.get(0).get(2), sequence.charAt(0), sequence.substring(1));
        }
    }

    public void load() {
        try {
            myReader = new Scanner(inputFA);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String mode = "";
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (List.of("~states", "~initial", "~alpha", "~trans", "~final").contains(line))
                mode = line;
            else
                classify(line, mode);
        }
        myReader.close();
    }
    public void run() {
        load();
        var console = new Scanner(System.in);
        while(true) {
            printMenu();
            String option = console.next();
            switch (option) {
                case "1" -> System.out.println("States : " +states);
                case "2" -> System.out.println("Initial : " +initial);
                case "3" -> System.out.println("Alphabet : " +alphabet);
                case "4" -> System.out.println("Transition : " +transitions);
                case "5" -> System.out.println("Final : " +finals);
                case "0" -> {
                    return;
                }
            }

        }
    }
}
