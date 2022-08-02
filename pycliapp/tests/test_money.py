# https://docs.pytest.org/en/latest/
import pytest

from mypkg.money import Money


class TestMoney:

    @pytest.fixture
    def f12chf(self):
        return Money(12, "CHF")

    @pytest.fixture
    def f14chf(self):
        return Money(14, "CHF")

    def test_amount(self, f12chf):
        m = f12chf
        assert m.amount() == 12

    def test_currency(self, f14chf):
        m = f14chf
        assert m.currency() == "CHF"

    def test_add(self, f12chf, f14chf):
        result = f12chf.add(f14chf)
        assert result.amount() == 26
        assert result.currency() == "CHF"

