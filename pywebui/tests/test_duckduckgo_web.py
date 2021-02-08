"""
1. Navigate to the DuckDuckGo home page
2. Enter the search phrase
3. Verify that:
    a. Results appear on the result page
    b. The search phrase appears in the search bar
    c. At least one search result contains the search phrase
"""

import pytest

from selenium.webdriver import Chrome
from selenium.webdriver.common.keys import Keys

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
    URL = 'https://www.duckduckgo.com'
    PHRASE = 'panda'

    browser.get(URL)

    search_input = browser.find_element_by_id(
        'search_form_input_homepage')
    search_input.send_keys(PHRASE + Keys.RETURN)

    link_divs = browser.find_elements_by_css_selector(
        '#links > div')
    assert len(link_divs) > 0

    xpath = f"//div[@id='links']//*[contains(text(),'{PHRASE}')]"
    results = browser.find_elements_by_xpath(xpath)
    assert len(results) > 0

    search_input = browser.find_element_by_id(
        'search_form_input')
    assert search_input.get_attribute('value') == PHRASE
