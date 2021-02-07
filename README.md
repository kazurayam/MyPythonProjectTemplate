# Mutiple Modules Project Template

- @author kazurayam
- @date Feb 2021

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
<details>
<summary>Table of Contents</summary>

- [これは何か](#%E3%81%93%E3%82%8C%E3%81%AF%E4%BD%95%E3%81%8B)
- [複数モジュールから成るプロジェクトを作った](#%E8%A4%87%E6%95%B0%E3%83%A2%E3%82%B8%E3%83%A5%E3%83%BC%E3%83%AB%E3%81%8B%E3%82%89%E6%88%90%E3%82%8B%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%9F)
  - [IntelliJ IDEAでマルチモジュールなプロジェクトを作った](#intellij-idea%E3%81%A7%E3%83%9E%E3%83%AB%E3%83%81%E3%83%A2%E3%82%B8%E3%83%A5%E3%83%BC%E3%83%AB%E3%81%AA%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%9F)
  - [pycliappの概要](#pycliapp%E3%81%AE%E6%A6%82%E8%A6%81)
  - [pywebappの概要](#pywebapp%E3%81%AE%E6%A6%82%E8%A6%81)
  - [pywebuitestの概要](#pywebuitest%E3%81%AE%E6%A6%82%E8%A6%81)
- [前提条件](#%E5%89%8D%E6%8F%90%E6%9D%A1%E4%BB%B6)
- [pycliappサブプロジェクトの説明](#pycliapp%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E8%AA%AC%E6%98%8E)
  - [Python処理系をmacOSにインストールする --- pyenv と Anaconda](#python%E5%87%A6%E7%90%86%E7%B3%BB%E3%82%92macos%E3%81%AB%E3%82%A4%E3%83%B3%E3%82%B9%E3%83%88%E3%83%BC%E3%83%AB%E3%81%99%E3%82%8B-----pyenv-%E3%81%A8-anaconda)
    - [local](#local)
    - [アンインストール](#%E3%82%A2%E3%83%B3%E3%82%A4%E3%83%B3%E3%82%B9%E3%83%88%E3%83%BC%E3%83%AB)
  - [Python仮想環境を作る --- pipenv](#python%E4%BB%AE%E6%83%B3%E7%92%B0%E5%A2%83%E3%82%92%E4%BD%9C%E3%82%8B-----pipenv)
  - [別マシンでPython仮想環境を再現する手順](#%E5%88%A5%E3%83%9E%E3%82%B7%E3%83%B3%E3%81%A7python%E4%BB%AE%E6%83%B3%E7%92%B0%E5%A2%83%E3%82%92%E5%86%8D%E7%8F%BE%E3%81%99%E3%82%8B%E6%89%8B%E9%A0%86)
  - [IntelliJ IDEAでモジュールの設定をする](#intellij-idea%E3%81%A7%E3%83%A2%E3%82%B8%E3%83%A5%E3%83%BC%E3%83%AB%E3%81%AE%E8%A8%AD%E5%AE%9A%E3%82%92%E3%81%99%E3%82%8B)
    - [IDEAにPlatform SDKを追加する](#idea%E3%81%ABplatform-sdk%E3%82%92%E8%BF%BD%E5%8A%A0%E3%81%99%E3%82%8B)
    - [IDEAのプロジェクトにProject SDKを設定する](#idea%E3%81%AE%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%ABproject-sdk%E3%82%92%E8%A8%AD%E5%AE%9A%E3%81%99%E3%82%8B)
  - [pycliappプロジェクトのディレクトリ構造を決める](#pycliapp%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E3%83%87%E3%82%A3%E3%83%AC%E3%82%AF%E3%83%88%E3%83%AA%E6%A7%8B%E9%80%A0%E3%82%92%E6%B1%BA%E3%82%81%E3%82%8B)
    - [記号　$ROOTPROJ](#%E8%A8%98%E5%8F%B7%E3%80%80rootproj)
    - [srcディレクトリを作ったがうまくいかない件](#src%E3%83%87%E3%82%A3%E3%83%AC%E3%82%AF%E3%83%88%E3%83%AA%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%9F%E3%81%8C%E3%81%86%E3%81%BE%E3%81%8F%E3%81%84%E3%81%8B%E3%81%AA%E3%81%84%E4%BB%B6)
  - [アプリケーションのコードを書く](#%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E3%81%AE%E3%82%B3%E3%83%BC%E3%83%89%E3%82%92%E6%9B%B8%E3%81%8F)
  - [ユニットテストのコードを書く](#%E3%83%A6%E3%83%8B%E3%83%83%E3%83%88%E3%83%86%E3%82%B9%E3%83%88%E3%81%AE%E3%82%B3%E3%83%BC%E3%83%89%E3%82%92%E6%9B%B8%E3%81%8F)
  - [ユニットテストを実行する --- pytest](#%E3%83%A6%E3%83%8B%E3%83%83%E3%83%88%E3%83%86%E3%82%B9%E3%83%88%E3%82%92%E5%AE%9F%E8%A1%8C%E3%81%99%E3%82%8B-----pytest)
    - [IDEAのプロジェクトの設定：ソースのディレクトリに印をつける](#idea%E3%81%AE%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E8%A8%AD%E5%AE%9A%E3%82%BD%E3%83%BC%E3%82%B9%E3%81%AE%E3%83%87%E3%82%A3%E3%83%AC%E3%82%AF%E3%83%88%E3%83%AA%E3%81%AB%E5%8D%B0%E3%82%92%E3%81%A4%E3%81%91%E3%82%8B)
- [pywebappサブプロジェクトの説明](#pywebapp%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E8%AA%AC%E6%98%8E)
- [pywebuitestサブプロジェクトの説明](#pywebuitest%E3%82%B5%E3%83%96%E3%83%97%E3%83%AD%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E3%81%AE%E8%AA%AC%E6%98%8E)
- [補足説明](#%E8%A3%9C%E8%B6%B3%E8%AA%AC%E6%98%8E)
  - [READMEに目次をつけた](#readme%E3%81%AB%E7%9B%AE%E6%AC%A1%E3%82%92%E3%81%A4%E3%81%91%E3%81%9F)

</details>
<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## これは何か

Pythonはプログラミング言語としてはとても習得しやすい言語だ。 ところがPython言語でプログラムを自作しようと仕事し始めたとたん、さまざまな疑問に遭遇した。Python処理系それ自体をどうインストールするか、特に複数バージョンのPythonを使い分けるにはどうするか、プロジェクトごとさまざまな外部パッケージをどう管理するか、自作したコードをどうやってライブラリ化するか、自作したコードを本番マシンでどうやって配備するか、といった問題だ。開発環境をどう作るか、開発ツールをどう使うか。こうした問題はすでに先人の手で解決済みであってネットを調べれば答えが見つかる。しかしこうしたノウハウを習得するのは容易ではなかった。

Pythonによる開発環境をいかに構築し開発ツールを使うかのお手本となるコード一式を仕込んで、このレポジトリに格納しGit Hubにアップしようと思う。今後自分がさまざまな仕事をPythonで解決しようとするときGit HubのTemplate Repository機能を使ってさっさと環境を構築できるように準備しよう。


## 前提条件

1. 使うマシンはMac Book Air、OSはmaxOS 11.1 Big Sur
1. MacにHomebrewをインストール済み、説明は省略する
1. MacにGitをインストール済み。Git Hubに自分のアカウントを持っている。Gitの操作に熟達していると前提するのでGitに関する説明は省略する。
1. MacでIntelliJ IDEAを統合開発環境として使う。IDEAのライセンスを持っていて、IDEAにPythonプラグインをインストール済み。

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
1. IDEAで MultipleModulesProjectTemplate プロジェクトが開いたら、*File > Project Structure* でプロジェクトの構造を設定するダイアログを開く。メニューから *Project Settings > Modules* を選択する。Moduleを＋（追加）する。`pycliapp`モジュールを＋し、`pywebapp`モジュールを＋し、`pywebuitest`モジュールを＋する。これでサブプロジェクトが３つできる。

>ここで「モジュール」という言葉を使ったがこれはIDEAが定義する用語としての「モジュール」である。Python言語が定義する「モジュール」ではない。混同しないよう気をつけよう。Python言語でモジュールとは `myapp.py` のように名前の末尾が `.py` のファイルのことだが、IDEAの用語法はまったく違う。
   
3つのサブプロジェクトはそれぞれどういう内容のプロジェクトか？以下に概要を述べる。

### pycliappの概要

このサブプロジェクトではcommandlineで実行するapplicationをPython言語で開発します。

1. 最初にmacOSにPython処理系をインストールする。[pyenv](https://github.com/pyenv/pyenv) を使って [Anaconda](https://www.anaconda.com/) をインストールする。複数のバージョンのPython処理系をインストールして、切り替えられるようにする。
1. Pythonプロジェクトの標準的なディレクトリ構造を導入します
1. このサブプロジェクトに専用のPython仮想環境を作ります。[pipenv](https://pypi.org/project/pipenv/) を利用します
1. IntelliJ IDEAに適切な設定を加えます。この`pycliapp` プロジェクトの開発作業をIDEAのなかですべてできるようにします
1. コンソールに *Hello, World!* と表示する素朴なアプリケーションをPython言語で作ります
1. アプリケーションをユニットテストします。そのために[pytest](https://docs.pytest.org/en/stable/)を利用します

詳細は[pyclipappの説明](#pycliappの説明)を参照のこと

### pywebappの概要

このサブプロジェクトでは WebサーバアプリケーションをPython言語で開発します。

1. フレームワーク [Flask](https://palletsprojects.com/p/flask/) の [チュートリアル](https://flask.palletsprojects.com/en/1.1.x/tutorial/) を隅々まで写経して、ちゃんと動作するWebアプリケーションを作ります。
1. チュートリアルが提供するユニットテストのコードも写経します。Pythonによる上手なコーディング技法の例がテンコ盛りです。
1. 自作したWebサーバアプリを [pip](https://pypi.org/project/pip/) でライブラリ化します。[PyPI](https://test.pypi.org/) にアップロードして共有可能にします。
1. 自作したPythonアプリケーションを組み込んだ[Docker](https://www.docker.com/) イメージを作り、Dockerコンテナを立ち上げて自作アプリを動かします。[Docker Hub](https://hub.docker.com/) にイメージをアップロードして共有可能にします。
1. Flaskチュートリアルのサンプルコードが動くDockerコンテナを自分のPCで立ち上げて、ブラウザで http://localhost:80/ にアクセスできるようにします。

詳細は[pywebappの説明](#pywebappの説明)を参照のこと

### pywebuitestの概要

このサブプロジェクトでは Webサーバアプリケーションのユーザー・インターフェースをテストするためのコード一式を開発します。

1. `pywebapp`サブプロジェクトが構築したWebアプリを対象として自動化テストを実行します。
1. [Selenium](https://selenium-python.readthedocs.io/) を用いたPythonプログラムを開発してWebページをテストします。
1. Page Object Model のデザインを用いてテストコードを設計します。

詳細は[pywebuitestの説明](#pywebuitestの説明)を参照のこと

### 記号　$ROOTPROJ の定義

本レポジトリの最上位ディレクトリを作ります。Git Hubからcloneしたなら当然ながらもう出来ているはず。例えば `~/github/MultipleModulesProjectTemplate` ディレクトリを作ったとしましょう。このディレクトリのことを以下の説明文で `$ROOTPROJ` と略記します。下記のようにしてBashシェルに変数 `ROOTPROJ` したと想像してもらってもいい。

```
$ cd ~/github/MultipleModulesProjectTemplate
$ export ROOTPROJ=$(pwd)
$ echo $ROOTPROJ
/Users/myname/github/MultipleModulesProjectTemplate
```

--------------------------------------------------------

## pycliappサブプロジェクトの説明

概要は[pycliappの概要](#pycliappの概要)を参照のこと。

### Python処理系をmacOSにインストールする

#### pyenvでAnacondaをインストールする

最初にmacOSにPython処理系をインストールした。この記事を参考にした。

- [【2021年最新版】MacOSで複数のPython/Anacondaバージョンを使い分ける方法【データ分析】](https://www.simpletraveler.jp/2021/01/02/macos-pyenv-python-anaconda-versionmanagement/#pyenvMac)

[pyenv](https://github.com/pyenv/pyenv) はさまざまあるPythonディストリビューションの中から望みのものをインストールしてくれるツール。複数のバージョンのPython処理系をインストールしておいて、切り替えて使うということを可能にしてくれる。

Homebrewを使ってmacOSにpyenvをインストールする。

```
$ cd ~
$ brew install pyenv
```

pyenvのパスをMacの.bash_profileに記述する。

```
export PYENV_ROOT="$HOME/.pyenv"
export PATH="$PYENV_ROOT/bin:$PATH"
eval "$(pyenv init -)"
```

>なおmacOS11ではデフォルトではzshを使うことになっているが、個人的好みでわたしはいまだにbashです。

わたしはDeep Learningに興味があるので Anacondaをインストールしたい。どのバージョンのAnacondaがインストールできるかを調べるにはこうする。

```
$ pyenv install --list | grep anaconda
  ...
  anaconda3-4.4.0
  ...
  anaconda3-5.3.1
  ...
```

たくさんリストアップされた候補の中からanaconda3-4.4.0 と anaconda3-5.3.1 の二つをインストールすることにした。

```
$ pyenv install anaconda3-4.4.0
```

そして

```
$ pyenv install anaconda3-5.3.1
```

>anaconndaディストリビューションは巨大だ。ダウンロードするのに時間がかかる。わたしの環境では10分ぐらいかかった。

コマンドラインで`python`コマンドを投入した時にどのバージョンが使われるかを確認しよう。

```
$ pyenv versions
* system (set by /Users/kazuakiurayama/.pyenv/version)
  anaconda3-4.4.0
  anaconda3-5.3.1
```

これを見ると `system` に `*` がついています。この状態で `python` コマンドを投入すると macOSにプレインストールされたPython2.7が選択され実行されてしまう。これではつまらない。

`pyenv global バージョン`コマンドで設定を切り替えます。今からはanaconda3-4.4.0を使うことにしましょう。

```
$ pyenv global anaconda3-4.4.0
:~
$ pyenv versions
  system
* anaconda3-4.4.0 (set by /Users/kazuakiurayama/.pyenv/version)
  anaconda3-5.3.1
```

#### globalとlocal

なお特定のディレクトリにcdしてから `pyenv local anaconda3-5.3.1` とやればそのディレクトリのしたではglobalに指定したのではない別のPython環境を使うことができます。

#### アンインストール

pyenvでインストールしたバージョンをOSからアンインストールするにはこうします。

```
$ pyenv uninstall バージョン名
```

### 記号 $SUBPROJ　の定義

TODO

### pycliappサブプロジェクトのためにPython仮想環境を作る --- pipenv

$ROOTPROJディレクトリの直下に `pycliapp` ディレクトリを作ります。`pyclipapp`にカレントディレクトリを移します。`pyclipapp`ディレクトリの絶対パスを SUBPROJ という記号で表すことにします。つまり下記の操作をせよ。

```
$ cd $ROOTPROJ
$ mkdir pycliapp
$ export $SUBPROJ=$(pwd)
```

$SUBPROJディレクトリのなかにPython仮想環境を作りましょう。pipenvを使います。[pipenv](https://pypi.org/project/pipenv/) はPython仮想環境を作るツールです。下記の記事を参考にした。

- [Pipenvを使ったPython開発まとめ](https://qiita.com/y-tsutsu/items/54c10e0b2c6b565c887a)

#### pipenvをインストールする

pyenvで選択したバージョンのanacondaにpipenvをインストールします。・・・たぶんもう入っているんですがねんのため。

```
$ cd ~
$ pip install pipenv
```
#### pycliappプロジェクトのために専用のPython仮想環境を作る

$SUBPROJにcdします。そして下記のコマンドを投入する。

```
$ cd $SUBPROJ
$ pipenv --python 3
```

すると仮想環境が作られます。

```
Creating a virtualenv for this project...
Pipfile: /Users/myname/github/MultipleModulesProjectTemplate/pycliapp/Pipfile
Using /usr/local/bin/python3.8 (3.8.5) to create virtualenv...
⠋ Creating virtual environment...created virtual environment CPython3.8.5.final.0-64 in 549ms
  creator CPython3Posix(dest=/Users/myname/.local/share/virtualenvs/pycliapp-TkLJRwmc, clear=False, no_vcs_ignore=False, global=False)
  seeder FromAppData(download=False, pip=bundle, setuptools=bundle, wheel=bundle, via=copy, app_data_dir=/Users/myname/Library/Application Support/virtualenv)
    added seed packages: pip==21.0.1, setuptools==52.0.0, wheel==0.36.2
  activators BashActivator,CShellActivator,FishActivator,PowerShellActivator,PythonActivator,XonshActivator

✔ Successfully created virtual environment! 
Virtualenv location: /Users/myname/.local/share/virtualenvs/pycliapp-TkLJRwmc
Creating a Pipfile for this project...
```


このとき $SUBPROJディレクトリの下に [Pipfile](pycliapp/Pipfile) というファイルが生成されます。Pipfileの中をみると、AnacondaにインストールされていたPython3.xが選ばれて、それを使うように仮想環境が初期化されたことがわかります。


pycliappプロジェクトのために作ったこのPython仮想環境がじっさいにどこのディレクトリに作られたのか？下記のコマンドで調べることができます。

```
$ cd $SUBPROJ
$ pipenv --venv
/Users/myname/.local/share/virtualenvs/pycliapp-TkLJRwmc
```
$SUBPROJディレクトリの下ではなくてぜんぜん別の場所に作られたことに注目しましょう。仮想環境を構成するファイル群はGitによるバージョン管理に含めるべきでありません。だから$SUBPROJディレクトリではない別の場所に仮想環境が作られるのは好都合です。

あとでIntelliJ IDEAにPlatform SDKを追加するとき、`pipenv --env`が教えてくれたパスを指定することになります。

#### 外部パッケージを追加する

pycliappプロジェクトのなかであとでユニットテストをします。pytestを使います。pycliappプロジェクトのPython仮想環境のなかにpytestをインストールしましょう。

```
$ cd $SUBPROJ
$ pipenv install pytest

Installing pytest...
Adding pytest to Pipfile's [packages]...
✔ Installation Succeeded 
Pipfile.lock not found, creating...
Locking [dev-packages] dependencies...
Locking [packages] dependencies...
Building requirements...
Resolving dependencies...
✔ Success! 
Updated Pipfile.lock (a834da)!
Installing dependencies from Pipfile.lock (a834da)...
  🐍   ▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉▉ 0/0 — 00:00:00
To activate this project's virtualenv, run pipenv shell.
Alternatively, run a command inside the virtualenv with pipenv run.
```

[Pipfile](pycliapp/Pipfile)のなかに `[package]`という記述ができて、そのなかに `pipenv` が自動的に追加されます。

```
[packages]
pytest = "*"
```

また $SUBPROJディレクトリの下に `Pipfile.lock` ファイルが自動的に追加されます。

```
$ cd $SUBPROJ
$ ls .
Pipfile		Pipfile.lock	pycliapp.iml
```

#### 開発環境だけで使うパッケージを別枠で管理する

アプリケーションが依存する通常の外部パッケージではなくて、開発環境でだけ使うパッケージを別枠として管理することができあす。たとえば [`autopep8`](https://githubja.com/hhatto/autopep8) をインストールしてみよう。

```
$ cd $SUBPROJ
$ pipenv install --dev autopep8
```

するとPipfileが次のように更新される。

```
[dev-packages]
autopep8 = "*"
```

#### 別マシンでPython仮想環境を再現する手順

`Pipfile` と `Pipfile.lock`　には、`pipenv install`コマンドによって仮想環境に追加されたパッケージに関する詳細情報が記録されています。このふたつのファイルさえあればpycliappプロジェクトの仮想環境とまったく同じものを別のマシンで再現することが簡単にできます。だから`Pipfile`と`Pipfile.lock`ファイルをかならずGitレポジトリに追加して共有します。

別マシンにこのレポジトリをcloneしたあとでPython仮想環境を再現するにはこうします。

Pipfileを入力としてパッケージを再インストールするにはこうする。

```
$ cd $SUBPROJ
$ pipenv install
$ pipenv install --dev
```

`pipenv install`コマンドの場合、外部パッケージのバージョンがきっちり同じになるとはかぎらない。コマンドを実行した時点で最新のバージョンが選ばれてインストールされるかもしれません。

Pipfile.lockを入力として外部パッケージの詳細なバージョンもきっちりあわせて環境を再現するにはこうする。

```
$ cd $SUBPROJ
$ pipenv sync
$ pipenv sync --dev
```

#### Pipfileにスクリプトを登録する


#### 仮想環境に入ってシェルを実行する


#### .envファイル

#### Pipfileからrequirements.txtを生成する


### IntelliJ IDEAでPythonプロジェクトに必要な設定をする

#### IDEAにPlatform SDKを追加する

#### IDEAのプロジェクトにProject SDKを設定する

### pycliappプロジェクトのディレクトリ構造を決める


#### 記号 $SUBPROJ

$SUBPROJの下に下記のようなディレクトリを作ります。



#### srcディレクトリを作ったがうまくいかない件

### アプリケーションのコードを書く

### ユニットテストのコードを書く

### ユニットテストを実行する --- pytest

#### IDEAのプロジェクトの設定：ソースのディレクトリに印をつける


---------------------------------------------------------
## pywebappサブプロジェクトの説明

概要は[pywebappの概要](#pywebappの概要)を参照のこと。

---------------------------------------------------------
## pywebuitestサブプロジェクトの説明

概要は[pywebuitestの概要](#pywebuitestの概要)を参照のこと。


---------------------------------------------------------
## 補足説明

### READMEに目次をつけた

[GitHubの本プロジェクトのREADME](https://github.com/kazurayam/MultipleModulesProjectTemplate) に目次(Table of Contents)をつけた。下記のページを参考にして実現した。

- [[GitHub]README.mdの目次生成をAction「toc-generator」による自動化で楽しよう](https://dev.classmethod.jp/articles/auto-generate-toc-on-readme-by-actions/)
