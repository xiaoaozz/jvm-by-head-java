package com.jvm.classfile.attributes;

import com.jvm.classfile.ClassReader;

/**
 * @Author zal
 * @Date 2023/10/28 22:49
 * @Description 标记属性标识
 * @Version 1.0
 */
public class MarkAttribute extends AttributeInfo{
    @Override
    void readInfo(ClassReader reader) {
        // 这里什么也不做，主要用于标记属性
    }
}
