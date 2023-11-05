package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;
import com.jvm.classfile.ConstantPool;
import lombok.Data;

/**
 * @Author zal
 * @Date 2023/10/28 15:34
 * @Description 属性信息
 * @Version 1.0
 * 在虚拟机规范中，每个属性都定义了
 * name_index，用来从常量池中拿到属性名
 * attr_len，用来定义属性的长度
 * <p>
 * attribute_info {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u1 info[attribute_length];
 * }
 */
public abstract class AttributeInfo {

    public static final String CODE = "code";
    public static final String CONSTANT_VALUE = "ConstantValue";
    public static final String DEPRECATED = "Deprecated";
    public static final String EXCEPTIONS = "Exceptions";
    public static final String LINE_NUMBER_TABLE = "LineNumberTable";
    public static final String LOCAL_VARIABLE_TABLE = "LocalVariableTable";
    public static final String SOURCE_FILE = "SourceFile";
    public static final String SYNTHETIC = "Synthetic";


    /**
     * 抽象方法，由各属性自己读取对应的属性信息
     *
     * @param reader 类读取器
     */
    abstract void readInfo(ClassReader reader);

    /**
     * 读取属性表
     *
     * @param reader       类读取器
     * @param constantPool 常量池
     * @return 属性数组
     */
    public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool) {
        int attributesCount = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = readAttribute(reader, constantPool);
        }
        return attributes;
    }

    /**
     * 读取单个属性
     *
     * @param reader       类读取器
     * @param constantPool 常量池
     * @return 属性
     */
    public static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        int attrNameIndex = reader.readUint16();
        String attrName = constantPool.getUtf8(attrNameIndex);
        int attrLen = reader.readUint32ToInteger();
        AttributeInfo attributeInfo = newAttributeInfo(attrName, attrLen, constantPool);
        attributeInfo.readInfo(reader);
        return attributeInfo;
    }

    /**
     * 创建具体的属性实例
     *
     * @param attrName     属性名
     * @param attrLen      属性长度
     * @param constantPool 常量池
     * @return 具体的属性
     */
    private static AttributeInfo newAttributeInfo(String attrName, int attrLen, ConstantPool constantPool) {
        // Java虚拟机规范预定义了23种属性，先解析其中的8种
        switch (attrName) {
            case CODE:
                return new CodeAttribute(constantPool);
            case CONSTANT_VALUE:
                return new ConstantValueAttribute();
            case DEPRECATED:
                return new DeprecatedAttribute();
            case EXCEPTIONS:
                return new ExceptionsAttribute();
            case LINE_NUMBER_TABLE:
                return new LineNumberTableAttribute();
            case LOCAL_VARIABLE_TABLE:
                return new LocalVariableTableAttribute();
            case SOURCE_FILE:
                return new SourceFileAttribute(constantPool);
            case SYNTHETIC:
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen, null);
        }
    }

}
