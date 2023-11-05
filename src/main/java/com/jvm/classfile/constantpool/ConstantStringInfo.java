package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;
import com.jvm.classfile.ConstantPool;


/**
 * @Author zal
 * @Date 2023/10/28 18:40
 * @Description TODO
 * @Version 1.0
 * CONSTANT_String_info {
 *     u1 tag;
 *     u2 string_index;
 * }
 */
public class ConstantStringInfo extends ConstantInfo{

    /**
     * 常量池
     */
    private ConstantPool constantPool;

    /**
     * 常量池索引，指向CONSTANT_Utf8_info常量
     */
    private int stringIndex;

    public ConstantStringInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader reader) {
        stringIndex = reader.readUint16();
    }

    /**
     * 按照索引从常量池中查找字符串
     * @return 字符串的值
     */
    public String toString() {
        return constantPool.getUtf8(stringIndex);
    }
}
