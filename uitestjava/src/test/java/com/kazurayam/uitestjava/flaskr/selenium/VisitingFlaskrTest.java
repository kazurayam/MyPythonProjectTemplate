package com.kazurayam.uitestjava.flaskr.selenium;

import com.kazurayam.subprocessj.docker.ContainerFinder;
import com.kazurayam.subprocessj.docker.ContainerFinder.ContainerFindingResult;
import com.kazurayam.subprocessj.docker.ContainerRunner;
import com.kazurayam.subprocessj.docker.ContainerRunner.ContainerRunningResult;
import com.kazurayam.subprocessj.docker.ContainerStopper;
import com.kazurayam.subprocessj.docker.ContainerStopper.ContainerStoppingResult;
import com.kazurayam.subprocessj.docker.model.ContainerId;
import com.kazurayam.subprocessj.docker.model.DockerImage;
import com.kazurayam.subprocessj.docker.model.PublishedPort;
import com.kazurayam.uitestjava.flaskr.pom.actions.LoginAction;
import com.kazurayam.uitestjava.flaskr.pom.actions.LogoutAction;
import com.kazurayam.uitestjava.flaskr.pom.actions.PostAction;
import com.kazurayam.uitestjava.flaskr.pom.data.Song;
import com.kazurayam.uitestjava.flaskr.pom.data.Songs;
import com.kazurayam.uitestjava.flaskr.pom.data.User;
import com.kazurayam.uitestjava.flaskr.pom.pages.blog.IndexPage;
import com.kazurayam.uitestjava.flaskr.pom.pages.blog.Post;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VisitingFlaskrTest {


    /**
     * This test will verify if the site's name in the page header is "Flaskr".
     */
    @Test
    public void test_page_header() {
        browser0.navigate().to(String.format("http://127.0.0.1:%d/", HOST_PORT));
        WebElement siteName = browser0.findElement(By.xpath("/html/body/nav/h1"));
        assertNotNull(siteName);
        assertEquals("Flaskr", siteName.getText());
    }

    /**
     * This test will do the following steps
     *
     * 0. targets http://127.0.0.1/
     * 1. creates a user named "Alice"
     * 2. logs-in to the blog site as Alice
     * 3. makes a post with a song's lyric as Alice, and save it
     * 4. makes sure that Alice's new post is displayed in the index page
     */
    @Test
    public void test_Alice_makes_a_post_with_a_song() throws Exception {
        URL indexUrl = new URL(String.format("http://127.0.0.1:%d/", HOST_PORT));

        // Alice logs in
        LoginAction loginAction = new LoginAction();
        loginAction.do_login(browser0, indexUrl, User.Alice);

        Song song_of_miyuki = Songs.get(0);

        // Alice makes a post with a song by Miyuki Nakajima
        PostAction postAction = new PostAction();
        postAction.new_post(browser0, indexUrl, User.Alice, song_of_miyuki);

        // ensure Alice finds the song that she posted
        checkIfPostBySomebodyPresent(browser0, indexUrl, User.Alice, User.Alice, song_of_miyuki);

        // logout
        LogoutAction logoutAction = new LogoutAction();
        logoutAction.do_logout(browser0, indexUrl);
    }

    private void checkIfPostBySomebodyPresent(WebDriver browser, URL url, User me, User somebody, Song song) {
        // let's start from the Index page
        IndexPage indexPage = new IndexPage(browser);
        indexPage.load(url);

        // find a post by somebody
        List<Post> postsBySomebody = indexPage.get_posts_by(somebody);

        assertTrue(postsBySomebody.size() > 0,
                String.format("indexPage.get_posts_by(%s) returned 0", somebody));

        String foundTitle = postsBySomebody.get(0).get_title();
        assertNotNull(foundTitle, "foundTitle is null");
        assertTrue(foundTitle.contains(song.getTitle()),
                String.format("%s expected to find a post" +
                        " by %s with a song \"%s\" but got \"%s\"",
                        me, somebody, song.getTitle(), foundTitle));
    }

    /**
     * start a Docker Container by "docker run" command.
     * In the container, a web server application runs to server a URL http://127.0.0.1:3080/
     *
     * It takes a bit long time; approximately 5 seconds. Just wait!
     */
    @BeforeAll
    public static void beforeAll() throws IOException, InterruptedException {
        File directory = Files.createTempDirectory("VisitingFlaskrTest").toFile();
        ContainerRunner runner =
                new ContainerRunner.Builder(image)
                        .directory(directory)
                        .publishedPort(publishedPort)
                        .build();
        ContainerRunningResult crr = runner.run();
        if (crr.returncode() != 0) {
            throw new IllegalStateException(crr.toString());
        }
        // setup ChromeDriver
        WebDriverManager.chromedriver().setup();
    }

    /**
     * open a Chrome browser window
     */
    @BeforeEach
    public void beforeEach() {
        browser0 = new ChromeDriver();
    }

    /**
     * close the Chrome browser window
     */
    @AfterEach
    public void afterEach() {
        if (browser0 != null) {
            browser0.quit();
            browser0 = null;
        }
    }


    /**
     * Stop the Docker Container gracefully by the "docker stop" command.
     * It will take approximately 10 seconds.
     * Be tolerant. Just wait!
     */
    @AfterAll
    public static void afterAll() throws IOException, InterruptedException {
        PublishedPort publishedPort = new PublishedPort(HOST_PORT, 8080);
        ContainerFindingResult cfr = ContainerFinder.findContainerByHostPort(publishedPort);
        if (cfr.returncode() == 0) {
            ContainerId containerId = cfr.containerId();
            ContainerStoppingResult csr = ContainerStopper.stopContainer(containerId);
            if (csr.returncode() != 0) {
                throw new IllegalStateException(csr.toString());
            }
        } else {
            throw new IllegalStateException(cfr.toString());
        }
    }

    private void printResult(String label, ContainerFindingResult cfr) {
        System.out.println("-------- " + label + " --------");
        System.out.println(cfr.toString());
    }

    private void delay(int millis) {
        try {
            long l = (long)millis;
            Thread.sleep(l);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int HOST_PORT = 3080;

    private static final PublishedPort publishedPort = new PublishedPort(HOST_PORT, 8080);
    private static final DockerImage image = new DockerImage("kazurayam/flaskr-kazurayam:1.1.0");

    private WebDriver browser0 = null;

}
