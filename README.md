# Sample usage of [gradle-manifest-plugin](https://github.com/coditory/gradle-manifest-plugin)

[![Build Status](https://github.com/coditory/gradle-manifest-plugin-sample/workflows/Build/badge.svg?branch=master)](https://github.com/coditory/gradle-manifest-plugin-sample/actions?query=workflow%3ABuild+branch%3Amaster)

This project presents how to use [gradle-manifest-plugin](https://github.com/coditory/gradle-manifest-plugin) to generate MANIFEST.MF file
and how to read it in runtime.

Relevant files:
- [build.gradle](/build.gradle) - add gradle-manifest-plugin to the project
- [ManifestReader.java](/src/main/java/com/coditory/sandbox/ManifestReader.java) - reads project's MANIFEST.MF file by implementation title
- [Application.java](/src/main/java/com/coditory/sandbox/Application.java) - prints out the content of MANIFEST.MF