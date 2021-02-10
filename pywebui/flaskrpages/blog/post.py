from selenium.webdriver.common.by import By


class Post:
    """
    Flaskrのブログ投稿一本を表すWebElementをラップして、
    タイトルや本文を読み取るメソッドを提供するヘルパ

    :param article: a WebElement
    <article class="post">
      <header>
        <div>
          <h1>東京はもうすぐ春</h1>
          <div class="about">by kazurayam on 2021-02-10</div>
        </div>
        <a class="action" href="/2/update">Edit</a>
      </header>
      <p class="body">どじょうが泳いでいます</p>
    </article>
    """
    TITLE = (By.XPATH, '//header/div/h1')
    BODY = (By.XPATH, '//p[1]')

    def __init__(self, article):
        self.article = article

    def get_title(self):
        title_h1 = self.article.find_element(*self.TITLE)
        return title_h1.text

    def get_body(self):
        body_p = self.article.find_element(*self.BODY)
        return body_p.text
