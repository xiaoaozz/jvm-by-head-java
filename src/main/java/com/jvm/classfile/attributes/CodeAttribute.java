package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;
import com.jvm.classfile.ConstantPool;

/**
 * @Author zal
 * @Date 2023/10/28 22:35
 * @Description 存放字节码等方法相关信息，变长属性，只存在于method_info结构中
 * @Version 1.0
 * Code_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 max_stack; // 操作数栈最大深度
 *     u2 max_locals; // 局部变量表大小
 *     u4 code_length;
 *     u1 code[code_length] // 字节码
 *     u2 exception_table_length;
 *     {
 *         u2 start_pc;
 *         u2 end_pc;
 *         u2 handler_pc;
 *         u2 catch_type;
 *     } exception_table[exception_table_length]; // 异常处理表
 *     u2 attributes_count;
 *     attribute_info attributes[attributes_count]; // 属性表
 * }
 */
public class CodeAttribute extends AttributeInfo {

    /**
     * 常量池
     */
    private ConstantPool constantPool;

    /**
     * 操作数栈最大深度
     */
    private int maxStack;

    /**
     * 局部变量表大小
     */
    private int maxLocals;

    /**
     * 字节码
     */
    private byte[] code;

    /**
     * 异常处理表
     */
    private ExceptionTableEntry[] exceptionTable;

    /**
     * 属性表
     */
    private AttributeInfo[] attributes;

    /**
     * 构造方法
     * @param constantPool 常量池
     */
    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        int codeLength = reader.readUint32ToInt();
        this.code = reader.readBytes(codeLength);
        this.exceptionTable = readExceptionTable(reader);
        this.attributes = readAttributes(reader, this.constantPool);
    }

    /**
     * 读取异常表
     * @param reader 类读取器
     * @return 异常表
     */
    private ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
        int exceptionTableLength = reader.readUint16();
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionTableEntry(reader.readUint16(), reader.readUint16(),
                    reader.readUint16(), reader.readUint16());
        }
        return exceptionTable;
    }


    class ExceptionTableEntry {
        /**
         * 开始
         */
        private int startPc;

        /**
         * 结束
         */
        private int endPc;

        /**
         *
         */
        private int handlerPC;

        /**
         * 捕获类型
         */
        private int catchType;

        public ExceptionTableEntry(int startPc, int endPc, int handlerPC, int catchType) {
            this.startPc = startPc;
            this.endPc = endPc;
            this.handlerPC = handlerPC;
            this.catchType = catchType;
        }
    }
}
