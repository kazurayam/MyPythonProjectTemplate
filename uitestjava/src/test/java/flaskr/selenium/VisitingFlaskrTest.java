package flaskr.selenium;

import com.kazurayam.subprocessj.docker.ContainerFinder;
import com.kazurayam.subprocessj.docker.ContainerFinder.ContainerFindingResult;
import com.kazurayam.subprocessj.docker.ContainerRunner;
import com.kazurayam.subprocessj.docker.ContainerRunner.ContainerRunningResult;
import com.kazurayam.subprocessj.docker.ContainerStopper;
import com.kazurayam.subprocessj.docker.ContainerStopper.ContainerStoppingResult;
import com.kazurayam.subprocessj.docker.model.ContainerId;
import com.kazurayam.subprocessj.docker.model.DockerImage;
import com.kazurayam.subprocessj.docker.model.PublishedPort;
import flaskr.pom.data.User;
import flaskr.pom.pages.auth.RegisterCredentialPage;
import flaskr.pom.pages.blog.IndexPage;
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
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class VisitingFlaskrTest {


    /**
     * This test will verify if the site's name in the page header is "Flaskr".
     */
    @Test
    public void test_page_header() {
        driver.navigate().to(String.format("http://127.0.0.1:%d/", HOST_PORT));
        WebElement siteName = driver.findElement(By.xpath("/html/body/nav/h1"));
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
    public void test_Alice_makes_a_post_with_a_song() {
        driver.navigate().to(String.format("http://127.0.0.1:%d/", HOST_PORT));

        /// create a user "Alice"
        String username = User.Alice.toString();
        String password = User.Alice.getPassword();

        // ensure we are on the index page
        IndexPage indexPage = new IndexPage(driver);
        assertTrue(indexPage.app_header_exists());
        assertTrue(indexPage.register_anchor_exists());
        assertTrue(indexPage.login_anchor_exists());

        // we want to navigate to the Register page
        indexPage.open_register_page();

        // ensure we are on the Register page
        RegisterCredentialPage registerPage = new RegisterCredentialPage(driver);
        assertTrue(registerPage.register_button_exists());

        // we want to register a user
        registerPage.type_username(username);
        registerPage.type_password(password);
        registerPage.do_register();

        // check if the user is already registered
        if (registerPage.flash_exists()) {
            logger.warn(String.format("user %s was already registered", username));
            // we are still on the Register page
            // so we want to navigate to the Log In page
            registerPage.do_login();
        }

        // ensure we are on the Log In page
        LogInPage logInPage = new LogInPage(driver);
    }


    private boolean verifyElementPresent(WebDriver driver, By by) {
        WebElement e = driver.findElement(by);
        if (e != null) {
            return true;
        } else {
            logger.debug(String.format("no element matches %s in %s",
                    by.toString(), driver.getCurrentUrl()));
            return false;
        }
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
        driver = new ChromeDriver();
    }

    /**
     * close the Chrome browser window
     */
    @AfterEach
    public void afterEach() {
        if (driver != null) {
            driver.quit();
            driver = null;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int HOST_PORT = 3080;

    private static final PublishedPort publishedPort = new PublishedPort(HOST_PORT, 8080);
    private static final DockerImage image = new DockerImage("kazurayam/flaskr-kazurayam:1.1.0");

    private WebDriver driver = null;

}
