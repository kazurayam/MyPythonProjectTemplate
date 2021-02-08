"""
1. Navigate to the DuckDuckGo home page
2. Enter the search phrase
3. Verify that:
    a. Results appear on the result page
    b. The search phrase appears in the search bar
    c. At least one search result contains the search phrase

This uses "Page Object"
"""

import pytest

from selenium.webdriver import Chrome
from selenium.webdriver.common.keys import Keys

from duckduckgopages.search import DuckDuckGoSearchPage
from duckduckgopages.result import DuckDuckGoResultPage


@pytest.fixture
def browser():
    # Initialize ChromeDriver
    driver = Chrome()

    # Wait implicitly for elements to be ready before attempting interactions
    driver.implicitly_wait(10)

    # Return the driver object at the end of setup
    yield driver

    # For clean up, quit the driver
    driver.quit()


def test_basic_duckduckgo_search(browser):
    PHRASE = 'panda'

    search_page = DuckDuckGoSearchPage(browser)
    search_page.load()
    search_page.search(PHRASE)

    result_page = DuckDuckGoResultPage(browser)
    assert result_page.link_div_count() > 0
    assert result_page.phrase_result_count(PHRASE) > 0
    assert result_page.search_input_value() == PHRASE

