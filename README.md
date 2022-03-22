![](https://github.com/wniemiec-io-java/babel-transpiler/blob/master/docs/img/logo/logo.jpg)

<h1 align='center'>Babel transpiler</h1>
<p align='center'>Babel transcription for JavaScript files.</p>
<p align="center">
    <a href="https://github.com/wniemiec-io-java/babel-transpiler/actions/workflows/windows.yml"><img src="https://github.com/wniemiec-io-java/babel-transpiler/actions/workflows/windows.yml/badge.svg" alt=""></a>
    <a href="https://github.com/wniemiec-io-java/babel-transpiler/actions/workflows/macos.yml"><img src="https://github.com/wniemiec-io-java/babel-transpiler/actions/workflows/macos.yml/badge.svg" alt=""></a>
    <a href="https://github.com/wniemiec-io-java/babel-transpiler/actions/workflows/ubuntu.yml"><img src="https://github.com/wniemiec-io-java/babel-transpiler/actions/workflows/ubuntu.yml/badge.svg" alt=""></a>
    <a href="https://codecov.io/gh/wniemiec-io-java/babel-transpiler"><img src="https://codecov.io/gh/wniemiec-io-java/babel-transpiler/branch/master/graph/badge.svg?token=R2SFS4SP86" alt="Coverage status"></a>
    <a href="http://java.oracle.com"><img src="https://img.shields.io/badge/java-11+-D0008F.svg" alt="Java compatibility"></a>
    <a href="https://mvnrepository.com/artifact/io.github.wniemiec-io-java/babel-transpiler"><img src="https://img.shields.io/maven-central/v/io.github.wniemiec-io-java/babel-transpiler" alt="Maven Central release"></a>
    <a href="https://github.com/wniemiec-io-java/babel-transpiler/blob/master/LICENSE"><img src="https://img.shields.io/github/license/wniemiec-io-java/babel-transpiler" alt="License"></a>
</p>
<hr />

## ❇ Introduction
Babel transpiler lets you to execute Babel transcription for given JavaScript files. 

## ❓ How to use

1. Add one of the options below to the pom.xml file: 

#### Using Maven Central (recomended):
```
<dependency>
  <groupId>io.github.wniemiec-io-java</groupId>
  <artifactId>babel-transpiler</artifactId>
  <version>LATEST</version>
</dependency>
```

2. Run
```
$ mvn install
```

3. Use it
```
[...]

import wniemiec.io.java.BabelTranspiler;

[...]

BabelTranspiler babelTranspiler = new BabelTranspiler(error -> errors.add(error));

List<String> code = List.of(
    "const getMessage = () => \"Hello World\";"
);
List<String> transcription = babelTranspiler.fromCode(code);

for (String line : transcription) {
    System.out.println(line);
}
```

## 📖 Documentation
|        Property        |Type|Description|Default|
|----------------|-------------------------------|-----------------------------|--------|
|fromCode |`(code: List<String>): List<String>`|Transpile JavaScript code from a list of string| - |
|fromFile |`(file: Path): List<String>`|Transpile JavaScript code from a file.| - |


## 🚩 Changelog
Details about each version are documented in the [releases section](https://github.com/williamniemiec/wniemiec-io-java/babel-transpiler/releases).

## 🤝 Contribute!
See the documentation on how you can contribute to the project [here](https://github.com/wniemiec-io-java/babel-transpiler/blob/master/CONTRIBUTING.md).

## 📁 Files

### /
|        Name        |Type|Description|
|----------------|-------------------------------|-----------------------------|
|dist |`Directory`|Released versions|
|docs |`Directory`|Documentation files|
|src     |`Directory`| Source files|
