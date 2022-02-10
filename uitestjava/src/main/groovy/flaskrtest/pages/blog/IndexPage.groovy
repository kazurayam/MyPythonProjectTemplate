package flaskrtest.pages.blog

import org.openqa.selenium.support.ui.WebDriverWait

import java.util.stream.Collectors

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions


import flaskrtest.data.User

class IndexPage {

	static final By APP_HEADER      = By.xpath('//h1[contains(text(),"Flaskr")]')
	static final By REGISTER_ANCHOR = By.xpath('//a[contains(text(), "Register")]')
	static final By LOGIN_ANCHOR    = By.xpath('//a[contains(text(), "Log In")]')
	static final By LOGOUT_ANCHOR   = By.xpath('//a[contains(text(), "Log Out")]')
	static final By POSTS_HEADER    = By.xpath('//h1[contains(text(), "Posts")]')
	static final By NEW_ANCHOR      = By.xpath('//a[contains(text(), "New")]')
	static final By POSTS           = By.xpath('//article[@class="post"]')
	static final int TIMEOUT = 3

	/**
	 * @param index 1,2,3, ...
	 */
	static final By POST_BY_INDEX(int index) {
		return By.xpath("//article[@class='post' and position()=${index}]")
	}

	static final By POST_BY_POSTID(String id) {
		return By.xpath("//article[@class='post']/header/a[starts-with(@href, '/${id}')]/ancestor::article")
	}

	private static WebDriver browser = null

	IndexPage(WebDriver browser) {
		this.browser = browser
	}

	void load(URL url) {
		browser.navigate().to(url.toExternalForm())
	}

	void open_register_page() {
		WebElement e = browser.findElement(REGISTER_ANCHOR)
		e.click()
	}

	void open_login_page() {
		WebElement e = browser.findElement(LOGIN_ANCHOR)
		e.click()
	}

	void click_logout() {
		WebElement e = browser.findElement(LOGOUT_ANCHOR)
		e.click()
	}

	Boolean app_header_exists() {
		WebDriverWait wait= new WebDriverWait(browser, TIMEOUT);
		return wait.until(ExpectedConditions.elementToBeClickable(APP_HEADER));
	}

	Boolean register_anchor_exists() {
		WebDriverWait wait= new WebDriverWait(browser, TIMEOUT);
		return wait.until(ExpectedConditions.elementToBeClickable(REGISTER_ANCHOR));
	}

	Boolean login_anchor_exists() {
		WebDriverWait wait = new WebDriverWait(browser, TIMEOUT);
		return wait.until(ExpectedConditions.elementToBeClickable(LOGIN_ANCHOR));
	}

	Boolean logout_anchor_exists() {
		WebDriverWait wait = new WebDriverWait(browser, TIMEOUT);
		return wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_ANCHOR));
	}

	Boolean posts_header_exists() {
		WebDriverWait wait = new WebDriverWait(browser, TIMEOUT);
		return wait.until(ExpectedConditions.elementToBeClickable(POSTS_HEADER));
	}

	Boolean nav_span_username_exists(String username) {
		WebDriverWait wait = new WebDriverWait(browser, TIMEOUT);
		By by = By.xpath("//nav/ul/li/span[text()='${username}']")
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	void open_create_post_page() {
		WebElement e = browser.findElement(NEW_ANCHOR)
		e.click()
	}

	int get_posts_count() {
		List<WebElement> posts = browser.findElements(POSTS)
		return posts.size()
	}

	void open_update_page_of_latest() {
		open_update_page_by_index(1)
	}

	void open_update_page_by_index(int index) {
		WebElement article = browser.findElement(POST_BY_INDEX(index))
		if (article != null) {
			open_update_page(article)
		} else {
			throw new IllegalArgumentException("no <article> found; index: ${index}")
		}
	}

	void open_update_page_by_postid(String postid) {
		WebElement article = browser.findElement(POST_BY_POSTID(postid))
		if (article != null) {
			open_update_page(article)
		} else {
			throw new IllegalArgumentException("no <article> found; postid: ${postid}")
		}
	}

	static void open_update_page(WebElement article) {
		Objects.requireNonNull(browser)
		WebElement anchor = article.findElement(
				By.xpath("//a[contains(text(), 'Edit')]"))
		if (anchor != null) {
			anchor.click()
		} else {
			throw new IllegalArgumentException("no <a>Edit</a> found")
		}
	}

	Post get_post_latest() {
		return get_post_by_index(1)
	}

	Post get_post_by_index(int index) {
		WebElement article = browser.findElement(POST_BY_INDEX(index))
		if (article != null) {
			return new Post(article)
		} else {
			return null
		}
	}

	Post get_post_by_postid(String postid) {
		Objects.requireNonNull(postid)
		WebElement article = browser.findElement(POST_BY_POSTID(postid))
		if (article != null) {
			return new Post(article)
		} else {
			return null
		}
	}

	List<Post> get_posts() {
		List<WebElement> articleElementList = browser.findElements(POSTS)
		return articleElementList.stream()
				.map({ webElement ->
					new Post(webElement)
				})
				.collect(Collectors.toList())
	}

	List<Post> get_posts_by(User user) {
		Objects.requireNonNull(user)
		List<WebElement> posts = browser.findElements(POSTS)
		return posts.stream()
				.map({ webElement ->
					new Post(webElement)
				})
				.filter({ post ->
					post.get_about().contains(user.toString())
				})
				.collect(Collectors.toList())
	}

	URL get_url() {
		String url = browser.getCurrentUrl()
		return new URL(url)
	}

}
