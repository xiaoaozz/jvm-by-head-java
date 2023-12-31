package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 18:40
 * @Description 字段或者方法的名称和描述符
 * @Version 1.0
 * CONSTANT_NameAndType_info {
 *     u1 tag;
 *     u2 name_index;
 *     u2 descriptor_index;
 * }
 */
public class ConstantNameAndTypeInfo extends ConstantInfo {

    /**
     * 字段或方法名索引
     */
    private int nameIndex;

    /**
     * 字符或方法描述符索引
     */
    private int descriptorIndex;

    @Override
    void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }
}
