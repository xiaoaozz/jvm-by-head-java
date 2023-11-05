package com.jvm.classfile;

import com.jvm.classfile.attributes.AttributeInfo;
import lombok.Data;

/**
 * @Author zal
 * @Date 2023/10/28 14:58
 * @Description 存储字段和方法的信息
 * @Version 1.0
 * field_info {
 * u2 access_flags;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 */
@Data
public class MemberInfo {

    /**
     * 常量池
     */
    private ConstantPool constantPool;

    /**
     * 访问标志
     */
    private int accessFlags;
    /**
     * 常量池索引，给出字段或者方法名
     */
    private int nameIndex;
    /**
     * 常量池索引，给出字段或者方法的描述符
     */
    private int descriptorIdx;
    /**
     * 属性表
     */
    private AttributeInfo[] attributes;

    private MemberInfo(ClassReader reader, ConstantPool constantPool) {
        this.constantPool = constantPool;
        this.accessFlags = reader.readUint16();
        this.nameIndex = reader.readUint16();
        this.descriptorIdx = reader.readUint16();
        this.attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    /**
     * 用于读取字段表或者方法表
     *
     * @param reader       类读取器
     * @param constantPool 常量池
     * @return 字段或者方法数组
     */
    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool) {
        int count = reader.readUint16();
        MemberInfo[] memberInfos = new MemberInfo[count];
        for (int i = 0; i < count; i++) {
            memberInfos[i] = new MemberInfo(reader, constantPool);
        }
        return memberInfos;
    }

    /**
     * 从常量池查找字段或者方法名
     *
     * @return 字段名或者方法名
     */
    public String getName() {
        return constantPool.getUtf8(nameIndex);
    }

    /**
     * 从常量池查找字段或者方法描述符
     *
     * @return 字段或者方法描述符
     */
    public String descriptor() {
        return constantPool.getUtf8(descriptorIdx);
    }

}
