# Sunseeker Solar Car Project - Telemetry

This repository contains the source for the Sunseeker Solar Car Project's main telemetry application for racing

## Requirements

- Java
- Apache Ant
- rxtx

## Installing and Configuring

Once you have cloned the repository onto your machine, you will want to do the following to get the telemetry up and running.

First, install all of the dependencies:

```sh
$ sudo apt install java-default
```

for Apache Ant
1 Download apache-ant-1.10.1-bin.zip from https://ant.apache.org/bindownload.cgi
2 unzip apache-ant-1.10.1-bin.zip
3
```sh
$ sudo gedit /etc/environment
```
add ":${ANT_HOME}/bin" to PATH
add new line ANT_HOME= apache-ant-1.10.1 location ie "/home/USERNAME/Documents/apache-ant-1.10.1"
add new line JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64"

For rxtx
1 Download 	rxtx-2.2pre2-bins.zip from http://rxtx.qbang.org/wiki/index.php/Download
2 unzip rxtx folder
3 Put correct librxtxSerial.so in /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/amd64
4 Put RXTXcomm.jar in /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext

Second, run telemetry:

```sh
$ ant compile jar run
```

note: after the project has been compiled, you can just run
```sh
$ ant run
```
