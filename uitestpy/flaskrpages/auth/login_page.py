from selenium.webdriver.common.by import By


class LoginPage:

    USERNAME_INPUT = (By.ID, 'username')
    PASSWORD_INPUT = (By.ID, 'password')
    LOGIN_BUTTON = (By.XPATH, '//input[@type="submit" and @value="Log In"]')

    def __init__(self, browser):
        self.browser = browser

    def login_button_exists(self):
        login_button = self.browser.find_element(*self.LOGIN_BUTTON)
        return login_button is not None

    def type_username(self, username):
        username_input = self.browser.find_element(*self.USERNAME_INPUT)
        username_input.send_keys(username)

    def type_password(self, password):
        password_input = self.browser.find_element(*self.PASSWORD_INPUT)
        password_input.send_keys(password)

    def do_login(self):
        login_input = self.browser.find_element(*self.LOGIN_BUTTON)
        login_input.click()


