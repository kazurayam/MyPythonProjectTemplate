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
4. Make a new post. Type title and body.
5. Verify if the post is visible in the list.
6. Edit the post text and save it.
7. Verify if the latest post text is displayed in the list of posts.
8. then, identify the post by its ID, and update it with a body text slightly changed.
9. take a screenshot of the page and save it into a PNG file
10. then, identify the post by its ID, and delete it
"""
import time

from flaskrpages.auth.register_credential_page import RegisterCredentialPage
from flaskrpages.auth.login_page import LoginPage
from flaskrpages.blog.index_page import IndexPage
from flaskrpages.blog.create_post_page import CreatePostPage
from flaskrpages.blog.update_post_page import UpdatePostPage
from flaskrpages.blog.songs import songs


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
    register_credential_page = RegisterCredentialPage(browser)
    assert register_credential_page.register_button_exists()

    # - type username and password, then click the Register button
    register_credential_page.type_username(credential['username'])
    register_credential_page.type_password(credential['password'])
    register_credential_page.do_register()

    # - now we are transferred to the Login page
    login_page = LoginPage(browser)
    assert login_page.login_button_exists()

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
    assert create_post_page.save_button_exists()

    # type title
    create_post_page.type_title(songs[0]['title'])
    # type body --- 歌詞の最初のところだけ
    body_source = songs[0]['lyric']
    # strip()で改行コードを取る
    body_startswith = body_source[:body_source.index('そんな時代も')].strip()
    create_post_page.type_body(body_startswith)
    # save the post
    create_post_page.do_save()

    # verify if the post is present in the index page
    post = index_page.get_post_latest()
    assert post is not None
    assert post.get_title() == songs[0]['title']
    assert post.get_body() == body_startswith

    # open update page for the latest post which this test has just created
    index_page.open_update_page_of_latest()
    update_post_page = UpdatePostPage(browser)
    assert update_post_page.save_button_exists()

    # type new text into the title and the body
    update_post_page.type_title(songs[0]['title'])
    update_post_page.type_body(songs[0]['lyric'])
    # save the updates
    update_post_page.do_save()

    # verify if the post is present in the index page
    post = index_page.get_post_latest()
    assert post is not None
    assert post.get_title() == songs[0]['title']
    assert post.get_body() == songs[0]['lyric']


    time.sleep(3)
