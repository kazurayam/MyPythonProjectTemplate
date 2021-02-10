"""
Automated Web UI testing for the flaskr-kazurayam application
developed by the pywebapp subproject.

0. We assume the Docker container of pywebapp is running.
1. Navigate to http://localhost:80/
2. Navigate to the Register form page where
    we register a new credential.
    We dynamically generate Username/Password
    based on the current timestamp.
3. Do Login using the newly added credential.
4. Make a new post.
5. Verify if the post is visible in the list.
6. Edit the post text and save it.
7. Verify if the post text is updated in the list.
"""
import time

from flaskrpages.auth.register_page import RegisterPage
from flaskrpages.auth.login_page import LoginPage
from flaskrpages.blog.index_page import IndexPage
from flaskrpages.blog.create_post_page import CreatePostPage


def test_register_login_post(browser, credential):
    print(credential)
    # uncomment the following line to see the output into STDOUT
    # assert False

    index_page = IndexPage(browser)
    index_page.load()

    # assert that the "Posts" header is displayed in the index page
    assert index_page.posts_header_exists()

    # register a new credential into the database
    # - open the Register page
    index_page.open_register_page()
    register_page = RegisterPage(browser)
    assert register_page.register_input_exists()

    # - type username and password, then click the Register button
    register_page.type_username(credential['username'])
    register_page.type_password(credential['password'])
    register_page.do_register()

    # - now we are transferred to the Login page
    login_page = LoginPage(browser)
    assert login_page.login_input_exists()

    time.sleep(1)

    # Login with the added credential
    # - type credentials and do login
    login_page.type_username(credential['username'])
    login_page.type_password(credential['password'])
    login_page.do_login()

    # - make sure we are on the index page
    assert index_page.posts_header_exists()

    # create a new Post
    index_page.open_create_post_page()
    create_post_page = CreatePostPage(browser)

    time.sleep(1)
