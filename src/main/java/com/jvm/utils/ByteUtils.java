package com.jvm.utils;

/**
 * @Author zal
 * @Date 2023/10/29 15:47
 * @Description 字节处理类
 * @Version 1.0
 */
public class ByteUtils {


    public static int byteToInt16(byte[] data) {
        assert data.length == 2;
        return (data[0] + 256) % 256 * 256 + (data[1] + 256) % 256;
    }

    public static int byteToInt32(byte[] data) {
        assert data.length == 4;
        int res = 0;
        for (byte datum : data) {
            res = res << 8 | (datum + 256) % 256;
        }
        return res;
    }

    public static long byteToLong32(byte[] data) {
        assert data.length == 4;
        long res = 0;
        for (byte datum : data) {
            res = res << 8 | (datum + 256) % 256;
        }
        return res;
    }

    public static long byteToLong64(byte[] data) {
        assert data.length == 8;
        long res = 0;
        for (byte datum : data) {
            res = res << 8 | (datum + 256) % 256;
        }
        return res;
    }


    public static float byteToFloat32(byte[] b) {
        int i = byteToInt32(b);
        return Float.intBitsToFloat(i);
    }


    public static double byteToDouble64(byte[] b) {
        long l = byteToLong64(b);
        return Double.longBitsToDouble(l);
    }
}
