UtilityGirl
===========

# UtilityGirl とは

あとで書く。


# 開発環境

ImageMagickWrapper は以下の環境で開発しています。

```sh
java -version
java version "1.6.0_37"
Java(TM) SE Runtime Environment (build 1.6.0_37-b06)
Java HotSpot(TM) 64-Bit Server VM (build 20.12-b01, mixed mode)
```

JVM のバージョンが 1.6 なのは仕事都合です。。。


```sh
mvn -v
:
Apache Maven 3.0.4 (r1232337; 2012-01-17 17:44:56+0900)
:
:
```

プロジェクトは [Apache Maven](http://maven.apache.org/) を使ってビルドしています。バージョンは 3.0.4 です。


# お知らせ

## 2013/12/31

### バージョン 1.2 リリース

`FileUtil`, `DirectoryUtil` の追加により、バージョンを 1.2 としました。

```xml
<groupId>net.tomoyamkung.library</groupId>
<artifactId>UtilityGirl</artifactId>
<version>1.2</version>
```


## 2013/12/30


### DirectoryUtil を追加

ディレクトリを再作成する処理が必要になったので単機能しかないのですがこのクラスを作成しました。


## 2013/12/29


### FileUtil を追加

`File` に関するユーティリティメソッドを扱うクラス `net.tomoyamkung.library.util.FileUtil` を追加しました。

画像ファイルの拡張子に関する処理が必要になったのでこのクラスを作成しました。他にも処理が必要になれば追加していきます。


## 2013/12/17


### バージョン 1.1 リリース

`AppProperties` の追加により、バージョンを 1.1 としました。

```xml
<groupId>net.tomoyamkung.library</groupId>
<artifactId>UtilityGirl</artifactId>
<version>1.1</version>
<packaging>jar</packaging>
```


### AppProperties を追加

プロパティファイルを扱うクラス `net.tomoyamkung.library.AppProperties` を追加しました。

クラスパス上に "app.properties" を作成すると、そのプロパティファイルを読み込んで定義されている値を取得することができます。
値は次の3通りで取得できます。

1. 文字列で取得する → `AppProperties#get`
2. int に変換して取得する → `AppProperties#getInt`
3. List<String> で取得する →  `AppProperties#getStringList`

3番目の `AppProperties#getStringList` は値を CSV で定義しておく必要があります。


## 2013/12/16


### バージョン 1.0.1 リリース

次の修正を行いました。

- net.tomoyamkung.library.util.ListUtil#isNullOrEmpty のスコープを public に変更

この修正に伴い、バージョンを 1.0.1 に変更しました。

```xml
<groupId>net.tomoyamkung.library</groupId>
<artifactId>UtilityGirl</artifactId>
<version>1.0.1</version>
<packaging>jar</packaging>
```


### mvn-repo ブランチに JAR をデプロイしました

- [UtilityGirl/net/tomoyamkung/library/UtilityGirl/1.0 at mvn-repo · tomoyamkung/UtilityGirl](https://github.com/tomoyamkung/UtilityGirl/tree/mvn-repo/net/tomoyamkung/library/UtilityGirl/1.0)

[Apache Maven](http://maven.apache.org/) を使われている場合は pom.xml に次を追記いただくことでもご利用いただけます。

リポジトリの追加。

```xml
<repositories>
  <repository>
    <id>tomoyamkung-github</id>
    <url>https://raw.github.com/tomoyamkung/UtilityGirl/mvn-repo/</url>
    <snapshots>
      <enabled>true</enabled>
      <updatePolicy>always</updatePolicy>
    </snapshots>
  </repository>
</repositories>
```

JAR の追加。

```xml
<dependency>
  <groupId>net.tomoyamkung.library</groupId>
  <artifactId>UtilityGirl</artifactId>
  <version>1.0</version>
</dependency>
```

また、"1.0" としてタグ付けしました。
