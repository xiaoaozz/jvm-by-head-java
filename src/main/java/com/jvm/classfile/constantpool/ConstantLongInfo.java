package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 18:39
 * @Description 使用8字节存储整数常量
 * @Version 1.0
 * CONSTANT_Long_info {
 *     u1 tag;
 *     u4 high_bytes;
 *     u4 low_bytes;
 * }
 */

public class ConstantLongInfo extends ConstantInfo {

    private long val;

    @Override
    void readInfo(ClassReader reader) {
        this.val = reader.readUint64ToLong();
    }
}
