package com.jvm.classfile.constantpool;

import com.jvm.classfile.ClassReader;
import com.jvm.classfile.ConstantPool;

/**
 * @Author zal
 * @Date 2023/10/28 18:15
 * @Description 常量池的抽象类，实现常量池中所有的常量类型。
 * @Version 1.0
 * 由于常量池中存放的信息各不相同，所以每种常量的格式也不同。
 * 常量数据的第一字节是tag，用来区分常量类型。根据在常量池的字节码的每个tag字节，可以判断下一个常量类型是什么。
 * 每个常量占用多少个字节是可以确定的。
 */
public abstract class ConstantInfo {

    public static final int CONSTANT_Utf8 = 1;
    public static final int CONSTANT_Integer = 3;
    public static final int CONSTANT_Float = 4;
    public static final int CONSTANT_Long = 5;
    public static final int CONSTANT_Double = 6;
    public static final int CONSTANT_Class = 7;
    public static final int CONSTANT_String = 8;
    public static final int CONSTANT_FieldRef = 9;
    public static final int CONSTANT_MethodRef = 10;
    public static final int CONSTANT_InterfaceMethodRef = 11;
    public static final int CONSTANT_NameAndType = 12;
    public static final int CONSTANT_MethodHandle = 15;
    public static final int CONSTANT_MethodType = 16;
    public static final int CONSTANT_InvokeDynamic = 18;


    /**
     * 抽象方法，用来读取信息，需要具体实现类实现
     *
     * @param reader 类读取器
     */
    abstract void readInfo(ClassReader reader);

    /**
     * 表明常量的类型
     */
    protected int tag;

    /**
     * 获取当前常量的类型
     *
     * @return tag
     */
    public int getType() {
        return tag;
    }

    public static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool constantPool) {
        int type = (reader.readUint8() + 256) % 256;
        ConstantInfo info = create(type, constantPool);
        info.readInfo(reader);
        return info;
    }


    /**
     * 根据tag值创建具体的常量
     *
     * @param type         常量类型
     * @param constantPool 常量池
     * @return 具体的常量类型
     */
    private static ConstantInfo create(int type, ConstantPool constantPool) {
        switch (type) {
            case CONSTANT_Utf8:
                return new ConstantUtf8Info();
            case CONSTANT_Integer:
                return new ConstantIntegerInfo();
            case CONSTANT_Float:
                return new ConstantFloatInfo();
            case CONSTANT_Long:
                return new ConstantLongInfo();
            case CONSTANT_Double:
                return new ConstantDoubleInfo();
            case CONSTANT_String:
                return new ConstantStringInfo(constantPool);
            case CONSTANT_Class:
                return new ConstantClassInfo(constantPool);
            case CONSTANT_FieldRef:
                return new ConstantFieldRefInfo(constantPool);
            case CONSTANT_MethodRef:
                return new ConstantMethodRefInfo(constantPool);
            case CONSTANT_InterfaceMethodRef:
                return new ConstantInterfaceMethodRefInfo(constantPool);
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo();
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo();
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamicInfo();
            default:
                throw new ClassFormatError("constant pool tag!");
        }

    }
}