import pytest


@pytest.mark.skip(reason="too trivial")
def test_addition():
    assert 1 + 1 == 2


@pytest.mark.skip(reason="わざとfailするように書いたテストだから")
def test_subtraction():
    diff = 1 - 1
    assert diff == 1


@pytest.mark.skip(reason="too trivial")
@pytest.mark.parametrize(
    "a,b,expected",
    [(0, 5, 0), (1, 5, 5), (2, 5, 10), (-3, 5, -15),
     (-4, -5, 20)])
def test_multiplication(a, b, expected):
    assert a * b == expected


@pytest.mark.skip(reason="too trivial")
def test_divide_by_zero():
    with pytest.raises(ZeroDivisionError):
        1 / 0
