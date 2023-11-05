package com.jvm.classpath;

import cn.hutool.core.util.StrUtil;
import com.jvm.classpath.entry.Entry;
import com.jvm.classpath.entry.WildcardEntry;

import java.io.File;

/**
 * @Author zal
 * @Date 2023/9/16 22:06
 * @Description Classpath实体类
 * @Version 1.0
 */
public class ClassPath {
    /**
     * 启动类加载器
     */
    private Entry bootClasspath;

    /**
     * 扩展类加载器
     */
    private Entry extClasspath;

    /**
     * 用户类加载器
     */
    private Entry userClasspath;

    /**
     * 解析路径
     * 使用-Xjre选项解析启动类路径和扩展类路径，使用-classpath/-cp选项解析用户类路径
     *
     * @param jreOption -Xjre选项
     * @param cpOption  -classpath/-cp选项
     * @return 解析的对应路径
     */
    public ClassPath(String jreOption, String cpOption) {
        parseBootAneExtClasspath(jreOption);
        parseUserClasspath(cpOption);
    }


    /**
     * 搜索class文件
     * 依次从启动类路径、扩展类路径和用户类路径中搜索class文件
     *
     * @param className class文件名
     * @return 文件字节流
     */
    public byte[] readClass(String className) {
        className = className + ".class";
        System.out.println("【搜索class文件】className:" + className);
        byte[] data;
        try {
            data = bootClasspath.readClass(className);
            if (data != null) {
                return data;
            }
            data = extClasspath.readClass(className);
            if (data != null) {
                return data;
            }
            data = userClasspath.readClass(className);
            if (data != null) {
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("can't find class!");
    }

    public String toString() {
        return userClasspath.printClassName();
    }

    /**
     * 获取有效的jre路径
     *
     * @param jreOption -Xjre参数
     * @return 有效的jre路径
     */
    private String getJreDir(String jreOption) {
        System.out.println("【寻找jre路径】参数jreOption:" + jreOption);
        File jreFile;
        // 先按照指定的jre路径寻找jre文件，如果存在，则直接返回路径
        if (StrUtil.isNotEmpty(jreOption)) {
            jreFile = new File(jreOption);
            if (jreFile.exists()) {
                return jreOption;
            }
        }
        System.out.println("【寻找jre路径】参数jreOption路径对应的jre文件不存在");
        // 如果jre选项为空，那么在当前路径下找jre文件
        jreFile = new File("./jre");
        if (jreFile.exists()) {
            return "./jre";
        }
        System.out.println("【寻找jre路径】当前路径下的jre文件不存在");
        // 如果都不存在，那么在JAVA_HOME中寻找
        String javaHome = System.getenv("JAVA_HOME");
        if (javaHome != null) {
            return javaHome + File.separator + "jre";
        }
        System.out.println("【寻找jre路径】JAVA_HOME路径下对应的jre文件不存在");
        throw new RuntimeException("Can not find jre folder!");
    }


    /**
     * 解析启动类加载器和扩展类加载器
     *
     * @param jreOption jre参数
     */
    private void parseBootAneExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        // 启动类加载器在：jre/lib/*
        String jreLibPath = jreDir + File.separatorChar + "lib" + File.separator + "*";
        this.bootClasspath = new WildcardEntry(jreLibPath);
        // 扩展类加载器在：/jre/lib/ext/*
        String jreExtPath = jreDir + File.separator + "lib" + File.separator + "ext" + File.separator + "*";
        this.extClasspath = new WildcardEntry(jreExtPath);
    }

    /**
     * 解析用户类加载器
     *
     * @param cpOption -cp参数
     */
    private void parseUserClasspath(String cpOption) {
        if (StrUtil.isEmpty(cpOption)) {
            cpOption = ".";
        }
        this.userClasspath = Entry.createEntry(cpOption);
    }
}
