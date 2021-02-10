from selenium.webdriver.common.by import By


class CreatePostPage:

    TITLE_INPUT = (By.ID, 'title')
    BODY_INPUT = (By.ID, 'body')
    SAVE_INPUT = (By.XPATH, '//input[@type="submit" and @value="Save"]')

    def __init__(self, browser):
        self.browser = browser

    def save_input_exists(self):
        save_input = self.browser.find_element(*self.SAVE_INPUT)
        return save_input is not None

    def type_title(self, title):
        title_input = self.browser.find_element(*self.TITLE_INPUT)
        title_input.send_keys(title)

    def type_body(self, body):
        body_input = self.browser.find_element(*self.BODY_INPUT)
        body_input.send_keys(body)

    def do_save(self):
        save_input = self.browser.find_element(*self.SAVE_INPUT)
        save_input.click()

