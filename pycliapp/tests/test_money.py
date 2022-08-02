# https://docs.pytest.org/en/latest/
from mypkg.money import Money


def test_amount():
    m = Money(12, "CHF")
    assert m.amount() == 12


def test_currency():
    m = Money(12, "CHF")
    assert m.currency() == "CHF"


def test_add():
    f12chf = Money(12, "CHF")
    f14chf = Money(14, "CHF")
    result = f12chf.add(f14chf)
    assert result.amount() == 26
    assert result.currency() == "CHF"

