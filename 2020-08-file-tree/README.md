# File Tree
新しい言語を試す時に用いるお題。

仕様を本ページ上部に、学習の観点と計画を本ページ下部に記載する。

## 仕様
引数で渡されたディレクトリの中身を表示する。

### 実行
```
$ file-tree /tmp/work

.
  conf
    backups
    subs
      options.conf
  server
    src
      script.sh
  user
    readme
    src
      script.sh
```

の様な形で実行する。

コマンド名は細かくは何でも良い。

### 基本仕様
+ 再帰的に抽出し、インデントして出力する
+ ルートディレクトリは`.`と出力する
+ `-type d`を指定した場合は、ディレクトリのみを出力する
+ `-type a`を指定もしくは未指定の場合は、全てを出力する
+ 不正オプションの場合は、エラーとする
+ 引数で指定したパスが存在しない場合は、エラーとする
+ 引数で指定したパスがディレクトリでない場合は、エラーとする

### 他実行例
```
$ /tmp/work -type d

.
  conf
    backups
    subs
  server
    src
  user
    src
```

```
$ /tmp/work -type f

invalid input.
```

```
$ /tmp/woook

/tmp/woook is not exists.
```

```
$ /tmp/work/conf/subs/options.conf

/tmp/work/conf/subs/options.conf is not directory.
```

### 動作確認例
以下のコマンドで上記出力となるサンプルデータが作成可能

```
mkdir work
cd work

mkdir -p {server,user}/src
touch {server,user}/{readme,src/sciprt.sh}
rm server/readme
mkdir -p conf/{subs,backups}
touch conf/subs/options.conf
```

## 学習計画・設計方針
+ 新たなプロジェクトを作成し、github のリポジトリも新たに作る
+ モジュール分離がどうできるか確認するため、io は分離すること
+ 極力型安全に実装すること
+ エラーの発生箇所と発覚箇所は遠ざけないこと
+ 改行やパスセパレータは任意の os に限定して良い
+ 長くとも 1 週間かつ 10 時間程度に目標を定め、動くものを作り上げるのを目指すのが望ましい
+ 学習終了時にふりかえりをし、経過時間 / うまくいった点 / うまくいかなかった点等を残す

## 学習観点
+ 文字列加工
+ ループ / 条件分岐
+ 型自作
+ 再帰
+ read 分離
+ write 分離
+ モジュール設計
+ エラーハンドリング

