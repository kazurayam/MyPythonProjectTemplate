package flaskr.pom.actions;

import flaskr.pom.pages.blog.IndexPage;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.Objects;

public class LogoutAction {
	public static void do_logout(WebDriver browser, URL startAt) {
		Objects.requireNonNull(browser);
		Objects.requireNonNull(startAt);

		// now we go to the Index page
		IndexPage indexPage = new IndexPage(browser);
		indexPage.load(startAt);

		assert indexPage.logout_anchor_exists();

		indexPage.click_logout();
	}

}
