package flaskrtest.actions

import org.openqa.selenium.WebDriver


import flaskrtest.pages.blog.IndexPage

public class LogoutAction {

	static void do_logout(WebDriver browser, URL startAt) {
		Objects.requireNonNull(browser)
		Objects.requireNonNull(startAt)

		// now we go to the Index page
		IndexPage indexPage = new IndexPage(browser)
		indexPage.load(startAt)

		assert indexPage.logout_anchor_exists()

		indexPage.click_logout()
	}
}
