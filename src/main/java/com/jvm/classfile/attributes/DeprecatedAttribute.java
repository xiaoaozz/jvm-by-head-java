package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 22:37
 * @Description 用于指出类、接口、字段或方法已经不建议使用
 * @Version 1.0
 * DeprecatedAttribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 * }
 */
public class DeprecatedAttribute extends MarkAttribute{
    @Override
    void readInfo(ClassReader reader) {
        super.readInfo(reader);
    }
}
