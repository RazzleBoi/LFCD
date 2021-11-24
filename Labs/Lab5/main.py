from Parser import Parser, runParser, runG2
from Grammar import Grammar

if __name__ == "__main__":
    grammar = Grammar("G2.txt")
    # grammar.printAll()
    # grammar.printOneNonTerminal()
    parser = Parser("G2.txt", "out2.txt")
    # parser.runAlgorithm(['a', 'a', 'c', 'b', 'c'])
    runG2()
