from Grammar import Grammar


class UI:

    def __init__(self) -> None:
        self.grammar = Grammar()
    
    def run(self):
        self.read_grammar()
        while True:
            print("1. Print set of non-terminals")
            print("2. Print set of terminals")
            print("3. Print set of productions")
            print("4. Print set of production for a given nonterminal")
            print(">>", end="")
            cmd = input()

            if cmd == "1":
                self.print_set_nonterminals()
            if cmd == "2":
                self.print_set_terminals()
            if cmd == "3":
                self.print_set_productions()
            if cmd == "4":
                self.print_production_for_non_terminal()


    def read_grammar(self):
        self.grammar.read_from_file('G1.txt')
        print(self.grammar)

    def print_production_for_non_terminal(self):
        print("Give non-terminal")

        non_terminal = input()
        print(self.grammar.get_productions_for_nonterminal(non_terminal))

    def print_set_nonterminals(self):
        print(self.grammar.N)

    def print_set_terminals(self):
        print(self.grammar.E)

    def print_set_productions(self):
        print(self.grammar.P)
