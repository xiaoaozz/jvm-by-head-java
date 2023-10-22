package com.jvm.classpath.entry;

import com.jvm.constant.CommandConstants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author zal
 * @Date 2023/9/16 16:30
 * @Description 形如aa/bb/*的路径形式，首先把路径末尾的*去掉，得到baseDir，然后遍历该路径下文件，只取以.jar结尾的文件
 * @Version 1.0
 */
public class WildcardEntry extends Entry {

    public CompositeEntry compositeEntry;

    public WildcardEntry(String jreLibPath) {
        // 去掉最后的一个*号
        String baseDir = jreLibPath.substring(0, jreLibPath.length() - 1);
        File dir = new File(baseDir);
        File[] files = dir.listFiles();
        this.compositeEntry = new CompositeEntry();
        compositeEntry.compositeEntries = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(CommandConstants.JAR_SUFFIX_LOWER)) {
                    compositeEntry.compositeEntries.add(new ZipJarEntry(baseDir, file.getName()));
                }
            }
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        return compositeEntry.readClass(className);
    }

    @Override
    public String printClassName() {
        return null;
    }
}
