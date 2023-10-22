package com.jvm.classpath.entry;

import java.io.*;


/**
 * @Author zal
 * @Date 2023/9/16 19:30
 * @Description 目录形式的类路径：先判断传入的路径是否存在，如果存在，则拼接className并使用IO读取其中的字节码返回
 * @Version 1.0
 */
public class DirEntry extends Entry {

    /**
     * 文件的绝对路径
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
