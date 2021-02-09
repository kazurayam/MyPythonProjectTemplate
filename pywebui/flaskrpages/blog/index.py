from selenium.webdriver.common.by import By


class FlaskrIndexPage:
    URL = 'http://localhost:80/'

    def __init__(self, browser):
         self.browser = browser

    def load(self):
        self.browser.get(self.URL)

    def register(self):
        pass

    def login(self):
        pass
