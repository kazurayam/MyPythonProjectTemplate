package flaskrtest.pages.auth

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class LogInPage {

	static final By USERNAME_INPUT  = By.id('username')
	static final By PASSWORD_INPUT  = By.id('password')
	static final By LOGIN_BUTTON = By.xpath('//input[@type="submit" and @value="Log In"]')

	private WebDriver browser

	LogInPage(WebDriver browser) {
		this.browser = browser
	}

	Boolean login_button_exists() {
		WebElement login_button = browser.findWebElement(LOGIN_BUTTON)
		return login_button != null
	}

	void type_username(String username) {
		browser.sendKeys(USERNAME_INPUT, username)
	}

	void type_password(String password) {
		browser.sendKeys(PASSWORD_INPUT, password)
	}

	void do_login() {
		browser.click(LOGIN_BUTTON)
	}

	URL get_url() {
		String url = browser.getCurrentUrl()
		return new URL(url)
	}
}
