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
