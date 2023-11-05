package com.jvm.classfile;

import cn.hutool.log.Log;
import com.jvm.classfile.constantpool.ConstantClassInfo;
import com.jvm.classfile.constantpool.ConstantInfo;
import com.jvm.classfile.constantpool.ConstantNameAndTypeInfo;
import com.jvm.classfile.constantpool.ConstantUtf8Info;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zal
 * @Date 2023/10/28 15:32
 * @Description 常量池
 * @Version 1.0
 */
public class ConstantPool {

    /**
     * 常量池
     */
    private ConstantInfo[] constantInfos;

    private static final Log log = Log.get();

    /**
     * 构造函数
     *
     * @param reader 类读取器
     */
    public ConstantPool(ClassReader reader) {
        int count = reader.readUint16();
        this.constantInfos = new ConstantInfo[count];
        for (int i = 1; i < count; i++) {
            constantInfos[i] = ConstantInfo.readConstantInfo(reader, this);
            switch (constantInfos[i].getTag()) {
                // 这两种类型占用两个位置
                case ConstantInfo.CONSTANT_Double:
                case ConstantInfo.CONSTANT_Long:
                    i++;
                    break;
            }
        }
    }

    /**
     * 按索引查找常量，如果 没有可以抛出异常
     *
     * @param index 索引
     * @return 指定常量
     */
    public ConstantInfo getConstantInfo(int index) {
        if (index > 0 && index < constantInfos.length) {
            ConstantInfo constantInfo = constantInfos[index];
            if (constantInfo != null) {
                return constantInfo;
            }
        }
        throw new NullPointerException("Invalid constant pool index!");
    }

    public Map<String, String> getNameAndType(int index) {
        ConstantNameAndTypeInfo constantNameAndTypeInfo = (ConstantNameAndTypeInfo) constantInfos[index];
        Map<String, String> map = new HashMap<>();
        map.put("name", this.getUtf8(constantNameAndTypeInfo.getNameIndex()));
        map.put("_type", this.getUtf8(constantNameAndTypeInfo.getDescriptorIndex()));
        return map;
    }

    public String getClassName(int index) {
        ConstantClassInfo info = (ConstantClassInfo) getConstantInfo(index);
        return getUtf8(info.getNameIndex());
    }

    /**
     * 从常量池查找UTF-8字符串，只要是调用这个方法，一定是读取字符串常量
     *
     * @param index 索引
     * @return 字符串
     */
    public String getUtf8(int index) {
        return ((ConstantUtf8Info) getConstantInfo(index)).getStr();
    }

    public ConstantInfo[] getConstantInfos() {
        return constantInfos;
    }

    public void setConstantInfos(ConstantInfo[] constantInfos) {
        this.constantInfos = constantInfos;
    }
}
