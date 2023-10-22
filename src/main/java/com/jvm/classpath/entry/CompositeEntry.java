package com.jvm.classpath.entry;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author zal
 * @Date 2023/9/16 16:30
 * @Description 包含多个路径的情况，由多个Entry组成
 * @Version 1.0
 */
public class CompositeEntry extends Entry {

    /**
     * 多个Entry
     */
    ArrayList<Entry> compositeEntries;

    /**
     * 路径
     */
    private String pathList;

    public CompositeEntry() {
    }

    public CompositeEntry(String pathList, String pathListSeparator) {
        this.pathList = pathList;
        // 使用系统分隔符将路径分开，然后分别具体化
        String[] paths = pathList.split(pathListSeparator);
        this.compositeEntries = new ArrayList<>(paths.length);
        for (int i = 0; i < paths.length; i++) {
            compositeEntries.add(new DirEntry(paths[i]));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        byte[] data;
        for (int i = 0; i < compositeEntries.size(); i++) {
            try {
                // 读取符合条目中的类数据
                data = compositeEntries.get(i).readClass(className);
                // 成功读取数据时，返回数据
                if (data != null) {
                    return data;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 否则返回null
        return null;
    }

    @Override
    public String printClassName() {
        return pathList;
    }
}
