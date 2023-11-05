package com.jvm.test.startjvm;

import com.jvm.classpath.ClassPath;
import com.jvm.cmd.Cmd;

import java.util.Arrays;

/**
 * @Author zal
 * @Date 2023/10/29 14:45
 * @Description 测试搜索classpath文件
 * @Version 2.0
 */
public class startJvmWithClassPath {

    /**
     * 测试
     * @param cmd 命令行工具
     * @test 搜索指定路径class文件 java -Xjre "/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre" "/src/main/resources/HelloWorld"
     */
    public static void test(Cmd cmd) {
        ClassPath cp = new ClassPath(cmd.getXJreOption(), cmd.getCpOption());
        System.out.printf("classpath: %s class: %s args: %s\n", cmd.getCpOption(), cmd.getClazz(), cmd.getArgs());
        String className = cmd.getClazz();
        try{
            byte[] classByte = cp.readClass(className);
            System.out.println(Arrays.toString(classByte));
            System.out.println("classBytes:");
            for (byte b : classByte) {
                System.out.print(String.format("%02x", b & 0xff) + " ");
            }
        } catch (Exception e) {
            System.out.println("test message: Can not find or load main class " + cmd.getClazz());
            e.printStackTrace();
        }
    }
}
