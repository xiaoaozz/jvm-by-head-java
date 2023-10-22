package com.jvm.classpath.entry;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.jvm.constant.CommandConstants;

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
    public abstract byte[] readClass(String className) throws IOException;

    /**
     * 打印className的字符串表示形式
     *
     * @return className字符串
     */
    public abstract String printClassName();

    /**
     * 工厂方法，根据传入path的形式不同，选择不同的处理方式
     *
     * @param path class文件路径
     * @return Entry
     */
    public static Entry createEntry(String path) {
        if (StrUtil.isNotEmpty(path)) {
            if (path.contains(CommandConstants.PATH_SEPARATOR)) {
                // 如果有系统分隔符
                return new CompositeEntry(path, CommandConstants.PATH_SEPARATOR);
            } else if (path.contains(CommandConstants.ASTERISK)) {
                // 如果有模糊路径
                return new WildcardEntry(path);
            } else if (path.contains(CommandConstants.JAR_SUFFIX_LOWER) || path.contains(CommandConstants.JAR_SUFFIX_UPPER) || path.contains(CommandConstants.ZIP_SUFFIX)) {
                // 如果含有jar或者zip
                return new ZipJarEntry(path);
            }
            return new DirEntry(path);
        }
        return null;
    }
}
