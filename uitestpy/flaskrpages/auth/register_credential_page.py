from selenium.webdriver.common.by import By


class RegisterCredentialPage:

    USERNAME_INPUT = (By.ID, 'username')
    PASSWORD_INPUT = (By.ID, 'password')
    REGISTER_BUTTON = (By.XPATH, '//input[@type="submit" and @value="Register"]')

    def __init__(self, browser):
        self.browser = browser

    def register_button_exists(self):
        register_button = self.browser.find_element(*self.REGISTER_BUTTON)
        return register_button is not None

    def type_username(self, username):
        username_input = self.browser.find_element(*self.USERNAME_INPUT)
        username_input.send_keys(username)

    def type_password(self, password):
        password_input = self.browser.find_element(*self.PASSWORD_INPUT)
        password_input.send_keys(password)

    def do_register(self):
        register_button = self.browser.find_element(*self.REGISTER_BUTTON)
        register_button.click()


