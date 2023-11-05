package com.jvm.classfile;

import cn.hutool.log.Log;
import com.jvm.classfile.attributes.AttributeInfo;

/**
 * @Author zal
 * @Date 2023/10/28 14:58
 * @Description class文件的整体结构
 * @Version 1.0
 * ClassFile文件结构如下：
 * ClassFile {
 * u4 magic;	//魔数
 * u2 minor_version;	//次版本号
 * u2 major_version;	//主版本号
 * u2 constant_pool_count;	//常量池大小
 * cp_info constant_pool[constant_pool_count-1]; //常量池
 * u2 access_flags;	//类访问标，表明class文件定义的是类还是接口，访问级别是public还是private，等
 * u2 this_class;	// 当前类
 * u2 super_class;	// 超类，除了java.lang.Object没有超类，其他都有
 * u2 interfaces_count;	//本类实现的接口数量
 * u2 interfaces[interfaces_count];	//实现的接口，存放在数组中
 * u2 fields_count;		//本来中含有字段数
 * field_info fields[fields_count];	//数组中存放这各个字段
 * u2 methods_count;		//本类中含有的方法数
 * method_info methods[methods_count];	//数组中存放着各个方法
 * u2 attributes_count;			//本类中含有的属性数量
 * attribute_info attributes[attributes_count];	//数组中存放着各个属性
 * }
 */
public class ClassFile {
    /**
     * 次版本号
     */
    private int minorVersion;
    /**
     * 主版本号
     */
    private int majorVersion;
    /**
     * 常量池
     */
    private ConstantPool constantPool;
    /**
     * 类访问标志，表明class文件定义的类还是接口，访问级别是public还是private等
     */
    private int accessFlags;
    /**
     * 当前类
     */
    private int thisClass;

    /**
     * 超类，除了java.lang.Object外其他类都有超类
     */
    private int superClass;
    /**
     * 接口索引表
     */
    private int[] interfaces;
    /**
     * 字段表
     */
    private MemberInfo[] fields;
    /**
     * 方法表
     */
    private MemberInfo[] methods;
    /**
     * 属性表
     */
    AttributeInfo[] attributes;


    private static final Log log = Log.get();

    /**
     * 构造方法
     *
     * @param classData 类数据
     */
    public ClassFile(byte[] classData) {
        ClassReader reader = new ClassReader(classData);
        this.readAndCheckMagic(reader);
        this.readAndCheckVersion(reader);
        this.constantPool = new ConstantPool(reader);
        this.accessFlags = reader.readUint16();
        this.thisClass = reader.readUint16();
        this.superClass = reader.readUint16();
        this.interfaces = reader.readUint16s();
        this.fields = MemberInfo.readMembers(reader, constantPool);
        this.methods = MemberInfo.readMembers(reader, constantPool);
        this.attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    /**
     * 获取并检查魔数，魔数起标识作用；
     * class文件的魔数是 0xCAFEBABE
     *
     * @param reader 类读取器
     */
    private void readAndCheckMagic(ClassReader reader) {
        long magic = reader.readUint32ToLong();
        if (magic != (0xCAFEBABEL & 0x0FFFFFFFFL)) {
            throw new ClassFormatError("magic!");
        }
        log.info("【检查魔数】校验通过！");
    }

    /**
     * 获取并检查版本号
     *
     * @param reader 类读取器
     * @description 主版本号是M，次版本号是m，完整的版本号是M.m的形式
     * 1、主版本号再J2SE1.2之前是45，之后每次大版本发布，版本号都会加1
     * 2、   Java版本    class文件版本号
     * JDK1.0.2    45.0-45.3
     * JDK1.1      45.0-45.65535
     * J2SE1.2     46.0
     * J2SE1.3     47.0
     * J2SE1.4     48.0
     * JavaSE5.0   49.0
     * JavaSE6     50.0
     * JavaSE7     51.0
     * JavaSE8     52.0
     */
    private void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();
        switch (this.majorVersion) {
            case 45:
                log.info("【检查版本号】当前版本号为 {}", this.majorVersion);
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (this.minorVersion == 0) {
                    log.info("【检查版本号】当前版本号为 {}", this.majorVersion);
                    return;
                }
        }
        throw new UnsupportedClassVersionError();
    }

    /**
     * 从常量池查找类名
     *
     * @return 类名
     */
    public String getClassName() {
        return constantPool.getClassName(thisClass);
    }

    /**
     * 从常量池查找超类名
     *
     * @return 超类名
     */
    public String getSuperClassName() {
        if (superClass > 0) {
            return constantPool.getClassName(superClass);
        }
        // 只有java.lang.Object没有超类
        return "";
    }

    public String[] getInterfaceNames() {
        int count = interfaces.length;
        String[] interfaceNames = new String[count];
        for (int i = 0; i < count; i++) {
            interfaceNames[i] = constantPool.getClassName(interfaces[i]);
        }
        return interfaceNames;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public void setThisClass(int thisClass) {
        this.thisClass = thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public void setSuperClass(int superClass) {
        this.superClass = superClass;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(int[] interfaces) {
        this.interfaces = interfaces;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public void setFields(MemberInfo[] fields) {
        this.fields = fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public void setMethods(MemberInfo[] methods) {
        this.methods = methods;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }
}
