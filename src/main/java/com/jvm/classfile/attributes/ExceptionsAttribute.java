package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 22:38
 * @Description 记录方法抛出的异常表，变长属性
 * @Version 1.0
 * Exceptions_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 number_of_exceptions;
 *     u2 exception_index_table[number_of_exceptions];
 * }
 */
public class ExceptionsAttribute extends AttributeInfo{

    /**
     * 异常表
     */
    private int[] exceptionIndexTable;

    @Override
    void readInfo(ClassReader reader) {
        this.exceptionIndexTable = reader.readUint16s();
    }

    /**
     * 获取异常表
     * @return 异常表
     */
    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }
}
