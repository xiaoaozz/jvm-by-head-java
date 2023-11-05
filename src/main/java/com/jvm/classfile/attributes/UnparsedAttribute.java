package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 22:43
 * @Description 未知的属性
 * @Version 1.0
 */

public class UnparsedAttribute extends AttributeInfo{

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性长度
     */
    private int length;

    /**
     * 属性数据
     */
    private byte[] info;

    /**
     * 构造函数
     * @param name 属性名
     * @param length 属性长度
     * @param info 属性数据
     */
    public UnparsedAttribute(String name, int length, byte[] info) {
        this.name = name;
        this.length = length;
        this.info = info;
    }

    @Override
    void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length);
    }
}
