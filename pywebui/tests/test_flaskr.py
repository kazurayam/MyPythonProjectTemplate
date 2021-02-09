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

from flaskrpages.blog.index import FlaskrIndexPage


def test_basic_flaskr_operation(browser, credential):
    index_page = FlaskrIndexPage(browser)
    index_page.load()

    print(credential)
    #assert False
