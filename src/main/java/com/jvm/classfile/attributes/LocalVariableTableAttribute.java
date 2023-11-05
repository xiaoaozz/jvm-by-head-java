package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 22:39
 * @Description 存放方法的局部变量信息
 * @Version 1.0
 */
public class LocalVariableTableAttribute extends AttributeInfo {

    /**
     * 局部变量表
     */
    private LocalVariableTableEntry[] localVariableTable;

    @Override
    void readInfo(ClassReader reader) {
        int localVariableTableLength = reader.readUint16();
        this.localVariableTable = new LocalVariableTableEntry[localVariableTableLength];
        for (int i = 0; i < localVariableTableLength; i++) {
            localVariableTable[i] = new LocalVariableTableEntry(reader.readUint16(), reader.readUint16(), reader.readUint16(), reader.readUint16(), reader.readUint16());
        }
    }

    static class LocalVariableTableEntry {

        /**
         * 代表该局部变量的生命周期开始的字节码偏移量
         */
        private int startPc;

        /**
         * 代表该局部变量的作用范围所覆盖的长度
         */
        private int length;

        /**
         * 指向常量池中CONSTANT_Utf8_info型常量的索引，代表局部变量名称
         */
        private int nameIndex;

        /**
         * 指向常量池中CONSTANT_Utf8_info型常量的索引，代表变量描述符
         */
        private int descriptorIndex;

        /**
         * 该局部变量在栈帧局部变量包中slot的位置
         */
        private int index;

        /**
         * 构造函数
         *
         * @param startPc         开始字节码偏移量
         * @param length          属性长度
         * @param nameIndex       属性名称索引
         * @param descriptorIndex 属性描述索引
         * @param index           属性位置
         */
        public LocalVariableTableEntry(int startPc, int length, int nameIndex, int descriptorIndex, int index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.descriptorIndex = descriptorIndex;
            this.index = index;
        }
    }
}
