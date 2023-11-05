package com.jvm.test.startjvm;

import com.jvm.classfile.ClassFile;
import com.jvm.classfile.MemberInfo;
import com.jvm.classpath.ClassPath;
import com.jvm.cmd.Cmd;

import java.util.Arrays;

/**
 * @Author zal
 * @Date 2023/10/28 23:36
 * @Description 测试解析class文件，版本对应章节功能
 * @Version 3.0
 */
public class StartJvmWithClassFile {

    /**
     * 测试
     * @param cmd 命令行工具
     * @test
     */
    public static void test(Cmd cmd) {
        ClassPath classPath = new ClassPath(cmd.getXJreOption(), cmd.getCpOption());
        System.out.printf("classpath: %s class: %s args: %s\n", classPath, cmd.getClazz(), Arrays.toString(cmd.getArgs()));
        String className = cmd.getClazz();
        ClassFile classFile = loadClass(className, classPath);
        assert classFile != null;
//        printClassInfo(classFile);
    }

    /**
     * 读取并解析class文件
     *
     * @param className class文件名
     * @param classPath Java对应的classPath路径
     * @return class文件
     */
    private static ClassFile loadClass(String className, ClassPath classPath) {
        try {
            byte[] classData = classPath.readClass(className);
            return new ClassFile(classData);
        } catch (Exception e) {
            System.out.println("Can not find or load main class " + className);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 打印class文件的部分重要信息
     *
     * @param cf class文件
     */
    private static void printClassInfo(ClassFile cf) {
        System.out.println("version: " + cf.getMajorVersion() + "." + cf.getMinorVersion());
        System.out.println("constants count：" + cf.getConstantPool().getConstantInfos().length);
        System.out.format("access flags：0x%x\n", cf.getAccessFlags());
        System.out.println("this class：" + cf.getClassName());
        System.out.println("super class：" + cf.getSuperClassName());
        System.out.println("interfaces：" + Arrays.toString(cf.getInterfaceNames()));
        System.out.println("fields count：" + cf.getFields().length);
        for (MemberInfo memberInfo : cf.getFields()) {
            System.out.format("%s \t\t %s\n", memberInfo.getName(), memberInfo.descriptor());
        }

        System.out.println("methods count: " + cf.getMethods().length);
        for (MemberInfo memberInfo : cf.getMethods()) {
            System.out.format("%s \t\t %s\n", memberInfo.getName(), memberInfo.descriptor());
        }
    }
}
