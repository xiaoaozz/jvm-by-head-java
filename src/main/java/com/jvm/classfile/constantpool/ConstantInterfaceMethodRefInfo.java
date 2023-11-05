package com.jvm.classfile.constantpool;

import com.jvm.classfile.ConstantPool;

/**
 * @Author zal
 * @Date 2023/10/28 18:40
 * @Description 接口方法符号引用
 * @Version 1.0
 */
public class ConstantInterfaceMethodRefInfo extends ConstantMemberRefInfo {

    /**
     * 构造方法
     *
     * @param constantPool 常量池
     */
    public ConstantInterfaceMethodRefInfo(ConstantPool constantPool) {
        super(constantPool);
    }
}
