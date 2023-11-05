package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 22:42
 * @Description 用于标记源文件中不存在、由编译器生成的类成员。引入改属性主要是为了支持嵌套类和嵌套接口。
 * @Version 1.0
 * Synthetic_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 * }
 */
public class SyntheticAttribute extends MarkAttribute{
    @Override
    void readInfo(ClassReader reader) {
        super.readInfo(reader);
    }
}
