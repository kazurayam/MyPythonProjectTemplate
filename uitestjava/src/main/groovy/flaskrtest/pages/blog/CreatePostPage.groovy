package flaskrtest.pages.blog

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import org.openqa.selenium.By

public class CreatePostPage {

	static final By TITLE_INPUT = By.id('title')
	static final By BODY_INPUT  = By.id('body')
	static final By SAVE_BUTTON = By.xpath('//input[@type="submit" and @value="Save"]')

	private WebDriver browser

	CreatePostPage(WebDriver browser) {
		this.browser = browser
	}

	URL get_url() {
		String url = browser.getCurrentUrl()
		return new URL(url)
	}

	Boolean save_button_exists() {
		WebElement save_button = browser.findElement(SAVE_BUTTON)
		return save_button != null
	}

	void type_title(String title) {
		WebElement e = browser.findElement(TITLE_INPUT)
		e.clear()
		e.sendKeys(title)
	}

	void type_body(String body) {
		WebElement e = browser.findElement(BODY_INPUT)
		e.clear()
		e.sendKeys(body)
	}

	void do_save() {
		WebElement e = browser.findElement(SAVE_BUTTON)
		e.click()
	}
}
