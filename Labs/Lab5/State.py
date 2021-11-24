class State:
    def __init__(self, word):
        self.type = StateType.normal
        self.index = 0
        self.work_stack = []
        self.input_stack = [word]

    def __str__(self):
        return "STATE: {} {} {} {}".format(self.type, self.index, self.work_stack, self.input_stack)


class StateType:
    normal = 'q'
    back = 'b'
    error = 'e'
    final = 'f'
