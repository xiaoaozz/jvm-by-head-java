package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;
import com.jvm.classfile.ConstantPool;
import lombok.Data;

import java.util.Map;

/**
 * @Author zal
 * @Date 2023/10/28 19:17
 * @Description 常量池引用类
 * @Version 1.0
 * 因为CONSTANT_FieldRef_info字段符号引用、CONSTANT_MethodRef_info普通（非接口）方法符号引用、
 * CONSTANT_InterfaceMethodRef接口方法符号引用 这三种类型结构一样，所以给出统一的类结构
 */
@Data
public class ConstantMemberRefInfo extends ConstantInfo{

    /**
     * 常量池
     */
    private ConstantPool constantPool;

    /**
     * 常量池索引，指向CONSTANT_Class_info
     */
    private int classIndex;

    /**
     * 常量池索引，指向CONSTANT_NameAndType_info
     */
    private int nameAndTypeIndex;

    /**
     * 构造方法
     * @param constantPool 常量池
     */
    public ConstantMemberRefInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader reader) {
        classIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }

    public String getClassName() {
        return constantPool.getClassName(classIndex);
    }

    public Map<String, String> getNameAndDescriptor() {
        return constantPool.getNameAndType(nameAndTypeIndex);
    }
}
