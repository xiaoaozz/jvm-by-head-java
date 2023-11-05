package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;
import com.jvm.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.ConstantClass;
import lombok.Data;

/**
 * @Author zal
 * @Date 2023/10/28 18:40
 * @Description 表示类或者接口的符号引用
 * @Version 1.0
 * CONSTANT_Class_info {
 *     u1 tag;
 *     u2 name_index;
 * }
 */
@Data
public class ConstantClassInfo extends ConstantInfo{

    /**
     * 常量池
     */
    private ConstantPool constantPool;

    /**
     * 常量池索引，指向CONSTANT_Utf8_info
     */
    private int nameIndex;

    public ConstantClassInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
    }

    public String Name() {
        return constantPool.getUtf8(nameIndex);
    }


}
