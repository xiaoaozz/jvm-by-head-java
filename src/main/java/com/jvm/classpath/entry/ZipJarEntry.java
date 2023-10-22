package com.jvm.classpath.entry;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @Author zal
 * @Date 2023/9/16 16:28
 * @Description zip、jar文件形式的类路径
 * @Version 1.0
 */
public class ZipJarEntry extends Entry {

    /**
     * 文件绝对路径
     */
    public String absPath;

    /**
     * 压缩文件名，不带后缀zip或jar
     */
    public String zipName;

    /**
     * 构造方法
     *
     * @param path 文件路径
     */
    public ZipJarEntry(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            absPath = dir.getParentFile().getAbsolutePath();
            zipName = dir.getName();
            // 去掉结尾的zip或者jar
            zipName = zipName.substring(0, zipName.length() - 4);
        }
    }

    /**
     * 构造方法
     *
     * @param path    文件路径
     * @param zipName 压缩包名称，注意，这里带zip或者jar后缀
     */
    public ZipJarEntry(String path, String zipName) {
        File dir = new File(path, zipName);
        if (dir.exists()) {
            absPath = dir.getAbsolutePath();
            this.zipName = zipName.substring(0, zipName.length() - 4);
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        File file = new File(absPath);
        ZipFile zf = new ZipFile(file);
        ZipEntry ze = zf.getEntry(className);
        if (ze == null) {
            return null;
        }
        BufferedInputStream in = new BufferedInputStream(zf.getInputStream(ze));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        int size = 0;
        byte[] temp = new byte[1024];
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        out.close();
        return out.toByteArray();
    }

    @Override
    public String printClassName() {
        return absPath;
    }
}
