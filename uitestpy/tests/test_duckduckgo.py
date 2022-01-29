"""
See
    https://blog.testproject.io/2019/07/16/develop-page-object-selenium-tests-using-python/
for the original article.

1. Navigate to the DuckDuckGo home page
2. Enter the search phrase
3. Verify that:
    a. Results appear on the result page
    b. The search phrase appears in the search bar
    c. At least one search result contains the search phrase

This uses "Page Object"
"""
import pytest

from duckduckgopages.search import DuckDuckGoSearchPage
from duckduckgopages.result import DuckDuckGoResultPage


@pytest.mark.skip(reason="DuckDuckGoのテストをわざとスキップする")
def test_basic_duckduckgo_search(browser):
    PHRASE = 'panda'

    search_page = DuckDuckGoSearchPage(browser)
    search_page.load()
    search_page.search(PHRASE)

    result_page = DuckDuckGoResultPage(browser)
    assert result_page.link_div_count() > 0
    assert result_page.phrase_result_count(PHRASE) > 0
    assert result_page.search_input_value() == PHRASE

