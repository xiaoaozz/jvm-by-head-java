package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 22:36
 * @Description 用于表示常量表达式的值，定长属性，只会出现在field_info结构中
 * @Version 1.0
 * ConstantValue_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length; // 定长，值必须为2
 *     u2 constant_value_index;
 * }
 * constant_value_index是常量池索引，但是具体指向哪种常量因字段类型而异，下面是类型匹配表：
 *      字段类型    常量类型
 *      long       CONSTANT_Long_info
 *      float       CONSTANT_Float_info
 *      double     CONSTANT_Double_info
 *      int/short/char/byte/boolean     CONSTANT_Integer_info
 *      String     CONSTANT_String_info
 */
public class ConstantValueAttribute extends AttributeInfo{

    /**
     * 常量池索引
     */
    private int constantValueIndex;

    @Override
    void readInfo(ClassReader reader) {
        this.constantValueIndex = reader.readUint16();
    }

    /**
     * 获取常量池索引
     * @return 索引值
     */
    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
