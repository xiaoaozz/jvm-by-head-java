package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;
import lombok.Data;

/**
 * @Author zal
 * @Date 2023/10/28 18:39
 * @Description 使用4字节存储IEEE754单精度浮点数常量
 * @Version 1.0
 * CONSTANT_Float_info {
 *     u1 tag;
 *     u4 bytes;
 * }
 */
@Data
public class ConstantFloatInfo extends ConstantInfo {

    private float val;

    @Override
    void readInfo(ClassReader reader) {
        val = reader.readUint64ToFloat();
    }
}
