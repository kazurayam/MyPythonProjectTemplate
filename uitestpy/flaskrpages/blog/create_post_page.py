from selenium.webdriver.common.by import By


class CreatePostPage:

    TITLE_INPUT = (By.ID, 'title')
    BODY_INPUT = (By.ID, 'body')
    SAVE_BUTTON = (By.XPATH, '//input[@type="submit" and @value="Save"]')

    def __init__(self, browser):
        self.browser = browser

    def save_button_exists(self):
        save_button = self.browser.find_element(*self.SAVE_BUTTON)
        return save_button is not None

    def type_title(self, title):
        title_input = self.browser.find_element(*self.TITLE_INPUT)
        title_input.clear()
        title_input.send_keys(title)

    def type_body(self, body):
        body_input = self.browser.find_element(*self.BODY_INPUT)
        body_input.clear()
        body_input.send_keys(body)

    def do_save(self):
        save_input = self.browser.find_element(*self.SAVE_BUTTON)
        save_input.click()

