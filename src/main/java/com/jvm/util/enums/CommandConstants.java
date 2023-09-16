package com.jvm.util.enums;

/**
 * @Author zal
 * @Date 2023/9/15 12:54
 * @Description 命令行常量
 * @Version 1.0
 */
public class CommandConstants {

    /**
     * 命令前缀
     */
    public static final String COMMEND_JAVA = "java";
    /**
     * 帮助命令后缀
     */
    public static final String COMMEND_HELP = "-help";
    public static final String COMMEND_SHORT_HELP = "-?";
    /**
     * 版本命令后缀
     */
    public static final String COMMEND_VERSION = "-version";
    /**
     * classpath路径命令后缀
     */
    public static final String COMMEND_CLASSPATH = "-classpath";
    public static final String COMMEND_SHORT_CLASSPATH = "-cp";
    /**
     * jre命令后缀
     */
    public static final String COMMEND_JRE = "-Xjre";

    /**
     * 固定命令长度
     */
    public static final Integer ERROR_LENGTH = 2;
    public static final Integer MAX_LENGTH = 4;

    /**
     * 路径分割符，在windows下使用;，在Unix/Linux下使用:分割
     */
    public static final String PATH_SEPARATOR = System.getProperty("os.name").contains("Windows") ? ";" : ":";

}
