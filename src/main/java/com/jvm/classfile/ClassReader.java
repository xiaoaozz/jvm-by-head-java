package com.jvm.classfile;

import cn.hutool.core.util.ByteUtil;

/**
 * @Author zal
 * @Date 2023/10/28 14:58
 * @Description class数据读取类 u1、u2、u4分别表示1、2、4字节无符号整数
 * @Version 1.0
 */
public class ClassReader {

    /**
     * 类数据
     */
    private byte[] data;

    /**
     * 当前读取到的索引
     */
    private int index = 0;

    /**
     * 构造函数
     *
     * @param data 数据
     */
    public ClassReader(byte[] data) {
        this.data = data;
    }

    /**
     * 读取u1类型数据
     * @return 1个字节
     */
    public byte readUint8() {
        return data[index++];
    }

    /**
     * 读取u2类型数据
     * @return 1个int整数
     */
    public int readUint16() {
        return ByteUtil.bytesToInt(readBytes(2));
    }

    /**
     * 读取u4类型数据
     * @return 1个int整数
     */
    public int readUint32ToInteger() {
        return ByteUtil.bytesToInt(readBytes(4));
    }

    /**
     * 读取u4类型数据
     * @return 1个long整数
     */
    public long readUint32() {
        return ByteUtil.bytesToLong(readBytes(4));
    }

    /**
     * 读取u8类型数据（JAVA虚拟机并未定义）
     * @return 1个单精度浮点数
     */
    public float readUint64ToFloat() {
        return ByteUtil.bytesToFloat(readBytes(8));
    }

    /**
     * 读取u8类型数据（JAVA虚拟机并未定义）
     * @return 1个双精度浮点数
     */
    public double readUint64ToDouble() {
        return ByteUtil.bytesToDouble(readBytes(8));
    }

    /**
     * 读取u8类型数据（JAVA虚拟机并未定义）
     * @return 1个long数据
     */
    public long readUint64ToLong() {
        return ByteUtil.bytesToLong(readBytes(8));
    }

    /**
     * 读取uint16表
     * @return 1个int数组
     */
    public int[] readUint16s() {
        int n = readUint16();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = readUint16();
        }
        return data;
    }

    /**
     * 用于读取指定数量的字节数据
     * @param n 指定数量
     * @return 字节数组
     */
    public byte[] readBytes(int n) {
        byte[] bytes = new byte[n];
        for (int i = 0; i < n; i++) {
            bytes[i] = data[index++];
        }
        return bytes;
    }
}
