package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;
import lombok.Data;

/**
 * @Author zal
 * @Date 2023/10/28 18:39
 * @Description 存放MUTF-8编码的字符串
 * @Version 1.0
 * CONSTANT_Utf8_info {
 *     u1 tag;
 *     u2 length;
 *     u1 bytes[length];
 * }
 */
@Data
public class ConstantUtf8Info extends ConstantInfo{

    String str;

    @Override
    void readInfo(ClassReader reader) {
        int len = reader.readUint16();
        byte[] bytes = reader.readBytes(len);
        str = new String(bytes);
    }
}
