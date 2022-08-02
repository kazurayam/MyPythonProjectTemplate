class Money:

    def __init__(self, amount, currency):
        self.fAmount = amount
        self.fCurrency = currency

    def amount(self):
        return self.fAmount

    def currency(self):
        return self.fCurrency

    def add(self, m):
        return Money(self.amount() + m.amount(), self.currency())

