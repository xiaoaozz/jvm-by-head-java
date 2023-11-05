package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;
import com.jvm.classfile.ConstantPool;

/**
 * @Author zal
 * @Date 2023/10/28 22:40
 * @Description 可选定厂属性，只会出现在ClassFile结构中，用于指出源文件名。
 * @Version 1.0
 * SourceFile_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length; // 定长，值必须为2
 *     u2 source_file_index;
 * }
 */

public class SourceFileAttribute extends AttributeInfo {

    /**
     * 常量池
     */
    private ConstantPool constantPool;

    /**
     * 常量池索引，指向CONSTANT_Utf8_info常量
     */
    private int sourceFileIndex;

    /**
     * 构造函数
     * @param constantPool 常量池
     */
    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader reader) {
        this.sourceFileIndex = reader.readUint16();
    }

    /**
     * 获取文件名称
     * @return 文件名称
     */
    public String getFileName() {
        return this.constantPool.getUtf8(this.sourceFileIndex);
    }
}
