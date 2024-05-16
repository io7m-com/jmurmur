jmurmur
===

[![Maven Central](https://img.shields.io/maven-central/v/com.io7m.jmurmur/com.io7m.jmurmur.svg?style=flat-square)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.io7m.jmurmur%22)
[![Maven Central (snapshot)](https://img.shields.io/nexus/s/com.io7m.jmurmur/com.io7m.jmurmur?server=https%3A%2F%2Fs01.oss.sonatype.org&style=flat-square)](https://s01.oss.sonatype.org/content/repositories/snapshots/com/io7m/jmurmur/)
[![Codecov](https://img.shields.io/codecov/c/github/io7m-com/jmurmur.svg?style=flat-square)](https://codecov.io/gh/io7m-com/jmurmur)
![Java Version](https://img.shields.io/badge/21-java?label=java&color=e6c35c)

![com.io7m.jmurmur](./src/site/resources/jmurmur.jpg?raw=true)

| JVM | Platform | Status |
|-----|----------|--------|
| OpenJDK (Temurin) Current | Linux | [![Build (OpenJDK (Temurin) Current, Linux)](https://img.shields.io/github/actions/workflow/status/io7m-com/jmurmur/main.linux.temurin.current.yml)](https://www.github.com/io7m-com/jmurmur/actions?query=workflow%3Amain.linux.temurin.current)|
| OpenJDK (Temurin) LTS | Linux | [![Build (OpenJDK (Temurin) LTS, Linux)](https://img.shields.io/github/actions/workflow/status/io7m-com/jmurmur/main.linux.temurin.lts.yml)](https://www.github.com/io7m-com/jmurmur/actions?query=workflow%3Amain.linux.temurin.lts)|
| OpenJDK (Temurin) Current | Windows | [![Build (OpenJDK (Temurin) Current, Windows)](https://img.shields.io/github/actions/workflow/status/io7m-com/jmurmur/main.windows.temurin.current.yml)](https://www.github.com/io7m-com/jmurmur/actions?query=workflow%3Amain.windows.temurin.current)|
| OpenJDK (Temurin) LTS | Windows | [![Build (OpenJDK (Temurin) LTS, Windows)](https://img.shields.io/github/actions/workflow/status/io7m-com/jmurmur/main.windows.temurin.lts.yml)](https://www.github.com/io7m-com/jmurmur/actions?query=workflow%3Amain.windows.temurin.lts)|

## jmurmur

A Java implementation of the [Murmur3](https://en.wikipedia.org/wiki/MurmurHash)
hash function.

## Features

* 32-bit and 64-bit versions of the
  [MurmurHash3](https://en.wikipedia.org/wiki/MurmurHash) non-cryptographic
  hash function.
* High coverage test suite.
* [OSGi-ready](https://www.osgi.org/)
* [JPMS-ready](https://en.wikipedia.org/wiki/Java_Platform_Module_System)
* ISC license.

## Usage

```
var a = Murmur3.hashInt(23);
var b = Murmur3.hashIntWithSeed(300, 81238);
var c = Murmur3.hashLong(23L);
var d = Murmur3.hashLongWithSeed(300L, 81238L);
```

