package com.jvm.test;

import com.jvm.classpath.ClassPath;
import com.jvm.cmd.Cmd;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author zal
 * @Date 2023/9/15 20:13
 * @Description cmd测试类
 * @Version 1.0
 */
public class CmdTest {
    public static void main(String[] args) {
        System.out.println("【启动类参数】" + Arrays.toString(args));
        Cmd cmd = new Cmd(args);
        System.out.println(cmd);
//        startJVMWithCmd(cmd);
        startJVMWithClassPath(cmd);
    }

    /**
     * V1.0 测试简单Cmd命令行工具
     * @param cmd 命令行实体类
     * @test java -help
     */
    private static void startJVMWithCmd(Cmd cmd) {
        System.out.printf("classpath: %s class: %s args: %s\n", cmd.getCpOption(), cmd.getClazz(), cmd.getArgs());
    }

    /**
     * V2.0 测试搜索classpath文件
     * @param cmd 命令行实体类
     * @test java -Xjre "/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home/jre" "/src/main/resources/HelloWorld"
     */
    private static void startJVMWithClassPath(Cmd cmd) {
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
            System.out.println("Can not find or load main class " + cmd.getClazz());
            e.printStackTrace();
        }
    }
}
