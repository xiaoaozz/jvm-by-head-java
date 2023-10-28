package com.jvm.classpath.entry;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * @Author zal
 * @Date 2023/9/16 19:30
 * @Description 目录形式的类路径：先判断传入的路径是否存在，如果存在，则拼接className并使用IO读取其中的字节码返回
 * @Version 1.0
 */
public class DirEntry extends Entry {


    /**
     * 方法一：java.nio.file.Path
     */
    // private Path absolutePath;

    /**
     * 方法二：文件的绝对路径
     */
    private String absDir;

    /**
     * 构造方法
     *
     * @param path 文件路径
     */
    public DirEntry(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            absDir = dir.getAbsolutePath();
        }
    }


    @Override
    public byte[] readClass(String className) throws IOException {
        // 1. 方法一：可以使用java.nio.file.Path类，可以直接在路径下找到对应文件，然后再使用Files.readAllBytes()读取字节数组
        // return Files.readAllBytes(absolutePath.resolve(className));
        // 2. 方法二：直接使用java原生的输入、输出流
        // 构造文件对象，指向指定路径下对应文件名的文件
        File file = new File(absDir, className);
        // 创建一个字节数组，用于暂存读取的数据
        byte[] temp = new byte[1024];
        // 输入流，将文件内容读取到缓冲区
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        // 输出流，用于保存读取到的数据
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        int size = 0;
        // 从输入流读取数据，并写入输出流
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        // 关闭输入流和输出流
        in.close();
        out.close();
        // 返回从文件中读取的字符数组
        return out.toByteArray();
    }

    @Override
    public String printClassName() {
        return absDir;
    }
}
