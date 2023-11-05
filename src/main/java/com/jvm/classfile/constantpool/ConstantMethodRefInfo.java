package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;
import com.jvm.classfile.ConstantPool;

/**
 * @Author zal
 * @Date 2023/10/28 18:40
 * @Description 普通（非接口）方法符号引用
 * @Version 1.0
 */
public class ConstantMethodRefInfo extends ConstantMemberRefInfo{

    /**
     * 构造方法
     *
     * @param constantPool 常量池
     */
    public ConstantMethodRefInfo(ConstantPool constantPool) {
        super(constantPool);
    }
}
