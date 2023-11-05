package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 22:39
 * @Description 存放方法的行号信息
 * @Version 1.0
 * LineNumberTable_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 line_number_table_length;
 *     {
 *         u2 start_pc;
 *         u2 line_number;
 *     } line_number_table[line_number_table_length];
 * }
 */
public class LineNumberTableAttribute extends AttributeInfo{

    private LineNumberTableEntry[] lineNumberTable;

    @Override
    void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readUint16();
        this.lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(reader.readUint16(), reader.readUint16());
        }
    }

    class LineNumberTableEntry {
        /**
         * 字节码行号
         */
        private int startPc;

        /**
         * Java源码行号，二者执行的关联
         */
        private int lineNumber;

        public LineNumberTableEntry(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }
}
