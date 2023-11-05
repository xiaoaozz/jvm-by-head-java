package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 18:39
 * @Description 使用4字节存储整数常量
 * @Version 1.0
 * CONSTANT_Integer_info{
 * u1 tag;
 * u4 bytes;
 * }
 */

public class ConstantIntegerInfo extends ConstantInfo {

    private int val;

    @Override
    void readInfo(ClassReader reader) {
        this.val = reader.readUint32ToInt();
    }
}
