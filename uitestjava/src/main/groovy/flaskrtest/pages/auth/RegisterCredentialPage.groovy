package flaskrtest.pages.auth

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import org.openqa.selenium.By

public class RegisterCredentialPage {

	static final By USERNAME_INPUT  = By.id('username')
	static final By PASSWORD_INPUT  = By.id('password')
	static final By REGISTER_BUTTON = By.xpath('//input[@type="submit" and @value="Register"]')
	static final By LOGIN_ANCHOR    = By.xpath('//a[contains(text(), "Log In")]')
	static final By DIV_FLASH       = By.xpath('//div[contains(@class, "flash")]')

	private WebDriver browser

	RegisterCredentialPage(WebDriver browser) {
		this.browser = browser
	}

	public Boolean register_button_exists() {
		WebElement register_button = browser.findElement(REGISTER_BUTTON)
		return register_button != null
	}

	Boolean flash_exists() {
		List<WebElement> flashList = browser.findElements(DIV_FLASH)
		return flashList.size() > 0
	}

	void type_username(String username) {
		WebElement e = browser.findElement(USERNAME_INPUT)
		e.sendKeys(username)
	}

	void type_password(String password) {
		WebElement e = browser.findElement(PASSWORD_INPUT)
		e.sendKeys(password)
	}

	void do_register() {
		WebElement e = browser.findElement(REGISTER_BUTTON)
		e.click()
	}

	void do_login() {
		WebElement e = browser.findElement(LOGIN_ANCHOR)
		e.click()
	}

	URL get_url() {
		String url = browser.getCurrentUrl()
		return new URL(url)
	}
}
