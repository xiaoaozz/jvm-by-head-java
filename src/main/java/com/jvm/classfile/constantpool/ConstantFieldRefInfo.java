package com.jvm.classfile.constantpool;

import com.jvm.classfile.ConstantPool;
import lombok.Data;

/**
 * @Author zal
 * @Date 2023/10/28 18:40
 * @Description 字段符号引用
 * @Version 1.0
 * CONSTANT_FieldRef_info {
 *     u1 tag;
 *     u2 class_index;
 *     u2 name_and_type_index;
 * }
 */
@Data
public class ConstantFieldRefInfo extends ConstantMemberRefInfo {


    /**
     * 构造方法
     *
     * @param constantPool 常量池
     */
    public ConstantFieldRefInfo(ConstantPool constantPool) {
        super(constantPool);
    }
}
