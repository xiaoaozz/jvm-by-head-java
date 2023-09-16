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
        String[] paths = pathList.split(pathListSeparator);
        this.compositeEntries = new ArrayList<>(paths.length);
        for (int i = 0; i < paths.length; i++) {
            compositeEntries.add(new DirEntry(paths[i]));
        }
    }

    @Override
    byte[] readClass(String className) throws IOException {
        byte[] data;
        for (int i = 0; i < compositeEntries.size(); i++) {
            try {
                data = compositeEntries.get(i).readClass(className);
                if (data != null) {
                    return data;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    String printClassName() {
        return pathList;
    }
}
