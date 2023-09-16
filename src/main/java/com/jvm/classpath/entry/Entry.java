package com.jvm.classpath.entry;

import com.jvm.util.enums.CommandConstants;

import java.io.IOException;

/**
 * @Author zal
 * @Date 2023/9/16 15:40
 * @Description 处理classpath抽象类
 * @Version 1.0
 */
public abstract class Entry {

    /**
     * 负责寻找和加载class文件
     *
     * @param className class文件的相对路径，路径之间用斜线 / 分隔，文件名有 .class后缀
     * @return 类的二进制流
     * @throws IOException io异常
     */
    abstract byte[] readClass(String className) throws IOException;

    /**
     * 打印className的字符串表示形式
     *
     * @return className字符串
     */
    abstract String printClassName();

    /**
     * 工厂方法，根据传入path的形式不同，选择不同的处理方式
     *
     * @param path
     * @return
     */
    static Entry createEntry(String path) {
        if (path != null) {
            if (path.contains(CommandConstants.PATH_SEPARATOR)) {
                // 如果有系统分隔符

            } else if (path.contains("*")) {
                // 如果有模糊路径
            } else if (path.contains(".jar") || path.contains(".JAR") || path.contains(".zip")) {
                // 如果含有jar或者zip
            }
        }
        return null;
    }
}
