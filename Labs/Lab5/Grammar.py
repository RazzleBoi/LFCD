
class Grammar:
    
    def __init__(self) -> None:
        self.N = None
        self.E = None
        self.P = None
        self.S = None
        self.is_cfg = True

    def read_from_file(self, filename):
        with open(filename) as file:
            self.N = self.parse_line(file.readline())
            self.E = self.parse_line(file.readline())
            self.S = file.readline().split('=')[1].strip()
            self.P = self.parse_productions(self.parse_line(''.join([line for line in file])))

    def parse_line(self, line):
        return [value.strip() for value in line.strip().split('=')[1].strip()[1:-1].strip().split(',')]
    

    def parse_productions(self, rules):

        result = []

        for rule in rules:
            
            left_hand_side, right_hand_side = rule.split('->')
            left_hand_side = left_hand_side.strip()
            right_hand_side = [value.strip() for value in right_hand_side.split('|')]

            for value in right_hand_side:
                result.append((left_hand_side, value))

        return result

    def get_productions_for_nonterminal(self, non_terminal):
        if not self.is_non_terminal(non_terminal):
            self.is_cfg = False
            raise Exception('It is non-terminal, so no CFG')

        return [production for production in self.P if production[0] == non_terminal]

    def is_non_terminal(self, value):
        return value in self.N

    def checkCFG(self):
        for prod in self.P:
            s = prod[0]
            if not self.is_non_terminal(s):
                self.is_cfg = False

    def __str__(self):
        return 'N = { ' + ', '.join(self.N) + ' }\n' \
        + 'E = { ' + ', '.join(self.E) + ' }\n' \
        + 'P = { ' + ', '.join([' => '.join(prod) for prod in self.P]) + ' }\n' \
        + 'S = ' + str(self.S) + '\n'