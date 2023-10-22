# jvm-by-head-java

## 前言

自己动手手写简易版JVM，提供Java语言版本，用于个人学习以及理解分享。

## 项目介绍

### 组织架构

```
jvm-by-head-go
│  pom.xml 依赖
│  README.md 项目描述
├─src
  └─main
      └─java
          └─com
              └─jvm
                  ├─classpath 寻找class文件
                  │  │  ClassPath.java
                  │  │
                  │  └─entry 组合模式实现 
                  │          CompositeEntry.java
                  │          DirEntry.java
                  │          Entry.java
                  │          WildcardEntry.java
                  │          ZipJarEntry.java
                  │
                  ├─cmd 简单命令行工具
                  │  │  Cmd.java
                  │  │
                  │  └─strategy 策略模式实现
                  │      │  CommandStrategy.java
                  │      │
                  │      └─impl
                  │              ClasspathCommandStrategy.java
                  │              HelpCommandStrategy.java
                  │              JreCommandStrategy.java
                  │              VersionCommandStrategy.java
                  │              
                  ├─test
                  │      CmdTest.java
                  │
                  └─util 工具包
                      └─enums
                              CommandConstants.java


```

### 架构图

#### 系统架构

#### 功能模块

#### 开发进度

#### 版本记录
- 2023-09-15 命令行工具开发完成（简易版）并使用策略模式优化
- 2023-09-16 搜索class文件完成度50%，项目目录结构调整
- 2023-09-22 搜索class文件开发完成，使用组合模式优化
