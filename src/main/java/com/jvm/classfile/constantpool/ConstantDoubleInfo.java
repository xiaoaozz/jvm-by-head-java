package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;
import lombok.Data;

/**
 * @Author zal
 * @Date 2023/10/28 18:40
 * @Description 使用8字节存储IEEE754双精度浮点数
 * @Version 1.0
 * CONSTANT_Double_info {
 *     u1 tag;
 *     u4 high_bytes;
 *     u4 low_bytes;
 * }
 */
@Data
public class ConstantDoubleInfo extends ConstantInfo{

    private double val;

    @Override
    void readInfo(ClassReader reader) {
        val = reader.readUint64ToDouble();
    }
}
