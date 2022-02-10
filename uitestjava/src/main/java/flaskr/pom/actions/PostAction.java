package flaskr.pom.actions;

import flaskr.pom.pages.blog.CreatePostPage;
import flaskr.pom.pages.blog.IndexPage;
import flaskr.pom.data.Song;
import flaskr.pom.data.User;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.Objects;

public class PostAction {

	/**
	 * @param browser
	 * @param startAt
	 * @param user
	 * @param song
	 * @return
	 */
	public static void new_post(WebDriver browser, URL startAt, User user, Song song) {
		Objects.requireNonNull(browser);
		Objects.requireNonNull(startAt);
		Objects.requireNonNull(song);

		// let's start from the index page
		IndexPage indexPage = new IndexPage(browser);
		indexPage.load(startAt);

		// we want to navigate to the CreatePost page
		indexPage.open_create_post_page();

		// now we are on the CreatePost page
		CreatePostPage createPage = new CreatePostPage(browser);
		assert createPage.save_button_exists();

		// type in the title
		String title = song.getTitle() + " --- " + song.getBy();
		createPage.type_title(title);

		// type in the body
		createPage.type_body(song.getLyric());

		// save the post
		createPage.do_save();

		// now we are on the index page
		// make sure that the 1st article is the song just posted by the user
		assert indexPage.get_post_latest().get_title().equals(title);
		assert indexPage.get_post_latest().get_about().contains(user.toString());
		assert indexPage.get_post_latest().get_body().equals(song.getLyric());
	}

}
