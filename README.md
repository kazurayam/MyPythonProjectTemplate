# Mutiple Modules Project Template

- @author kazurayam
- @date Feb 2021

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
<details>
<summary>Table of Contents</summary>

- [これは何か](#%E3%81%93%E3%82%8C%E3%81%AF%E4%BD%95%E3%81%8B)
- [レポジトリのなかにサブプロジェクトを配置](#%E3%83%AC%E3%83%9D%E3%82%B8%E3%83%88%E3%83%AA%E3%81%AE%E3%81%AA%E3%81%8B%E3%81%AB%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%82%92%E9%85%8D%E7%BD%AE)
- [複数モジュールから成るプロジェクトを作った](#%E8%A4%87%E6%95%B0%E3%83%A2%E3%82%B8%E3%83%A5%E3%83%BC%E3%83%AB%E3%81%8B%E3%82%89%E6%88%90%E3%82%8B%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%9F)
  - [IntelliJ IDEAでマルチモジュールなプロジェクトを作った](#intellij-idea%E3%81%A7%E3%83%9E%E3%83%AB%E3%83%81%E3%83%A2%E3%82%B8%E3%83%A5%E3%83%BC%E3%83%AB%E3%81%AA%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%9F)
  - [pycliappサブプロジェクトの概要](#pycliapp%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E6%A6%82%E8%A6%81)
  - [pywebappサブプロジェクトの概要](#pywebapp%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E6%A6%82%E8%A6%81)
  - [pywebuitestサブプロジェクトの概要](#pywebuitest%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E6%A6%82%E8%A6%81)
- [前提条件](#%E5%89%8D%E6%8F%90%E6%9D%A1%E4%BB%B6)
- [pycliappサブプロジェクトの説明](#pycliapp%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E8%AA%AC%E6%98%8E)
  - [Python処理系をOSにインストールする --- pyenvとAnacodna](#python%E5%87%A6%E7%90%86%E7%B3%BB%E3%82%92os%E3%81%AB%E3%82%A4%E3%83%B3%E3%82%B9%E3%83%88%E3%83%BC%E3%83%AB%E3%81%99%E3%82%8B-----pyenv%E3%81%A8anacodna)
  - [pyclipappプロジェクトのためのディレクトリを作る](#pyclipapp%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E3%81%9F%E3%82%81%E3%81%AE%E3%83%87%E3%82%A3%E3%83%AC%E3%82%AF%E3%83%88%E3%83%AA%E3%82%92%E4%BD%9C%E3%82%8B)
  - [アプリケーションのコードを書く](#%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E3%81%AE%E3%82%B3%E3%83%BC%E3%83%89%E3%82%92%E6%9B%B8%E3%81%8F)
  - [Python仮想環境を作る --- pipenv](#python%E4%BB%AE%E6%83%B3%E7%92%B0%E5%A2%83%E3%82%92%E4%BD%9C%E3%82%8B-----pipenv)
  - [別マシンでPython仮想環境を再現する手順](#%E5%88%A5%E3%83%9E%E3%82%B7%E3%83%B3%E3%81%A7python%E4%BB%AE%E6%83%B3%E7%92%B0%E5%A2%83%E3%82%92%E5%86%8D%E7%8F%BE%E3%81%99%E3%82%8B%E6%89%8B%E9%A0%86)
  - [IntelliJ IDEAでモジュールの設定をする](#intellij-idea%E3%81%A7%E3%83%A2%E3%82%B8%E3%83%A5%E3%83%BC%E3%83%AB%E3%81%AE%E8%A8%AD%E5%AE%9A%E3%82%92%E3%81%99%E3%82%8B)
    - [IDEAにPlatform SDKを追加する](#idea%E3%81%ABplatform-sdk%E3%82%92%E8%BF%BD%E5%8A%A0%E3%81%99%E3%82%8B)
    - [IDEAのプロジェクトにProject SDKを設定する](#idea%E3%81%AE%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%ABproject-sdk%E3%82%92%E8%A8%AD%E5%AE%9A%E3%81%99%E3%82%8B)
  - [ユニットテストを書いて実行する](#%E3%83%A6%E3%83%8B%E3%83%83%E3%83%88%E3%83%86%E3%82%B9%E3%83%88%E3%82%92%E6%9B%B8%E3%81%84%E3%81%A6%E5%AE%9F%E8%A1%8C%E3%81%99%E3%82%8B)
    - [IDEAのプロジェクトの設定：ソースのディレクトリに印をつける](#idea%E3%81%AE%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E8%A8%AD%E5%AE%9A%E3%82%BD%E3%83%BC%E3%82%B9%E3%81%AE%E3%83%87%E3%82%A3%E3%83%AC%E3%82%AF%E3%83%88%E3%83%AA%E3%81%AB%E5%8D%B0%E3%82%92%E3%81%A4%E3%81%91%E3%82%8B)
- [pywebappサブプロジェクトの説明](#pywebapp%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E8%AA%AC%E6%98%8E)
- [pywebuitestサブプロジェクトの説明](#pywebuitest%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E8%AA%AC%E6%98%8E)
- [補足説明](#%E8%A3%9C%E8%B6%B3%E8%AA%AC%E6%98%8E)
  - [READMEに目次をつけた](#readme%E3%81%AB%E7%9B%AE%E6%AC%A1%E3%82%92%E3%81%A4%E3%81%91%E3%81%9F)

</details>
<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## これは何か

Pythonはプログラミング言語としてはとても習得しやすい言語だ。 ところがPython言語でプログラムを自作しようと仕事し始めたとたん、さまざまな疑問に遭遇した。Python処理系それ自体をどうインストールするか、特に複数バージョンのPythonを使い分けるにはどうするか、プロジェクトごとさまざまな外部パッケージをどう管理するか、自作したコードをどうやってライブラリ化するか、自作したコードを本番マシンでどうやって配備するか、といった問題だ。開発環境をどう作るか、開発ツールをどう使うか。こうしたノウハウを習得するには努力がいる。

Pythonによる開発環境をいかに構築し開発ツールを使うかのお手本となるコード一式を仕込んで、このレポジトリに格納しGit Hubにアップしようと思う。今後自分がさまざまな仕事をPythonで解決しようとするときGit HubのTemplate Repositoryを使って環境の構築をさっさとやれるように準備しよう。


## 複数モジュールから成るプロジェクトを作った

一つのシステムを構築するのに複数の技術要素を組み合わせたくなる場合がよくある。その場合、複数のモジュールを一つのGitレポジトリにまとめてバージョン管理したい。

そこで本プロジェクトでは手本として、下記のようにこのレポジトリの中に３つのサブプロジェクトを作って、ひとまとめにバージョン管理することにした。

```
$ tree $ROOTPROJ
.
├── pycliapp
├── pywebapp
└── pywebuitest
```

>なお上記で `$ROOTPROJ` という記号を用いていた。これは本レポジトリをわたしのPCのローカルディスクにcloneすることによって作られたディレクトリ（たとえば `~/github/MultipleModulesProjectTemplate`）を表します。

>サブプロジェクトの数は３つとかぎらない、Python言語だけとはかぎらない。サブプロジェクトをJavaやNodeで作ることもあるだろう。

### IntelliJ IDEAでマルチモジュールなプロジェクトを作った

わたしはJavaによる開発のため 統合開発環境 IntelliJ IDEA をずっと使ってきた。だからIDEAでPythonの開発もやることにした。IDEAでひとつの親プロジェクトのなかに複数のサブプロジェクトを持たせたかった。下記の記事を参考にした。

- [IntelliJ IDEAで複数モジュールのプロジェクトを作成する手順のメモ](https://qiita.com/rubytomato@github/items/e534e4f1187801b7e159)

この記事から下記のことを習った。

1. IDEAで$ROOTPROJディレクトリにNew Projectを作るとき **Empty Project** を選択する。
1. IDEAで MultipleModulesProjectTemplate プロジェクトが開いたら、*File > Project Structure* でプロジェクトの構造を設定するダイアログを開く。メニューから *Project Settings > Modules* を選択する。Moduleを＋（追加）する。`pycliapp`モジュールを＋し、`pywebapp`モジュールを＋し、`pywebuitest`モジュールを＋する。これでサブプロジェクトが３つできる。SDKを登録するなどの作業はあとですればいい。
   
>ここで「モジュール」という言葉を使ったがこれはIDEAのドキュメントが定義する意味での「モジュール」である。Python言語が定義する「モジュール」ではない。Python言語でモジュールとは `myapp.py` のように名前の末尾が `.py` のファイルに他ならないが、IDEAの用語法はまったく違う。
   
3つのサブプロジェクトはそれぞれどういう内容のプロジェクトか？以下に概要を述べる。


### pycliappサブプロジェクトの概要

このサブプロジェクトではcommandlineで実行するapplicationをPython言語で開発します。

1. macOSにPython処理系をインストールする。[pyenv](https://github.com/pyenv/pyenv) を使って [Anaconda](https://www.anaconda.com/) をインストールする。複数のバージョンのPython処理系をインストールして、切り替えられるようにする。
1. Pythonプロジェクトの標準的なディレクトリ構造を導入します
1. このサブプロジェクトに専用のPython仮想環境を作ります。[pipenv](https://pypi.org/project/pipenv/) を利用します
1. IntelliJ IDEAに適切な設定を加えます。この`pycliapp` プロジェクトの開発作業をIDEAのなかですべてできるようにします
1. コンソールに *Hello, World!* と表示する素朴なアプリケーションをPython言語で作ります
1. アプリケーションをユニットテストします。そのために[pytest](https://docs.pytest.org/en/stable/)を利用します


### pywebappサブプロジェクトの概要

このサブプロジェクトでは WebサーバアプリケーションをPython言語で開発します。

1. フレームワーク [Flask](https://palletsprojects.com/p/flask/) の [チュートリアル](https://flask.palletsprojects.com/en/1.1.x/tutorial/) を隅々まで写経して、ちゃんと動作するWebアプリケーションを作ります。
1. 自作したPythonアプリケーションを [pip](https://pypi.org/project/pip/) でライブラリ化します。[PyPI](https://test.pypi.org/) に自分のためのアカウントを作って、PyPIにライブラリをアップロードして共有可能にします。
1. 自作したPythonアプリケーションを組み込んだ[Docker](https://www.docker.com/) イメージを作り、Dockerコンテナを立ち上げて自作アプリを動かします。[Docker Hub](https://hub.docker.com/) に自分のためのアカウントを作って、Docker Hubにイメージをアップロードして共有可能にします。
1. Flaskチュートリアルのサンプルコードが動くDockerコンテナを自分のPCで立ち上げて、ブラウザから http://localhost:80/ としてアクセスできるようにします。

### pywebuitestサブプロジェクトの概要

このサブプロジェクトでは Webサーバアプリケーションのユーザー・インターフェースをテストするためのコード一式を開発します。

1. `pywebapp`サブプロジェクトが構築したWebアプリを対象として自動化テストを実行します。
1. [Selenium](https://selenium-python.readthedocs.io/) を用いたPythonプログラムを開発してWebページをテストします。
1. Page Object Model のデザインを用いてテストコードを設計します。

## 前提条件

1. 使うマシンはMac Book Air、OSはmaxOS 11.1 Big Sur
1. MacにHomebrewをインストール済み、説明は省略する
1. MacにGitをインストール済み。Git Hubに自分のアカウントを持っている。Gitの操作に熟達していると前提するのでGitに関する説明は省略する。
1. MacでIntelliJ IDEAを統合開発環境として使う。IDEAのライセンスを持っていて、IDEAにPythonプラグインをインストール済み。

## pycliappサブプロジェクトの説明

### Python処理系をOSにインストールする --- pyenvとAnacodna

### pyclipappプロジェクトのためのディレクトリを作る

### アプリケーションのコードを書く

### Python仮想環境を作る --- pipenv

### 別マシンでPython仮想環境を再現する手順

### IntelliJ IDEAでモジュールの設定をする

#### IDEAにPlatform SDKを追加する

#### IDEAのプロジェクトにProject SDKを設定する

### ユニットテストを書いて実行する

#### IDEAのプロジェクトの設定：ソースのディレクトリに印をつける

## pywebappサブプロジェクトの説明

TODO

## pywebuitestサブプロジェクトの説明


TODO

## 補足説明

### READMEに目次をつけた

[GitHubの本プロジェクトのREADME](https://github.com/kazurayam/MultipleModulesProjectTemplate) に目次をつけた。下記のページを参考にした。

- [[GitHub]README.mdの目次生成をAction「toc-generator」による自動化で楽しよう](https://dev.classmethod.jp/articles/auto-generate-toc-on-readme-by-actions/)
