package com.jvm.test;

import com.jvm.classfile.ClassFile;
import com.jvm.classpath.ClassPath;
import com.jvm.cmd.Cmd;
import com.jvm.test.startjvm.StartJvmWithClassFile;
import com.jvm.test.startjvm.startJvmWithClassPath;

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
        // 测试命令行工具
        // startJVMWithCmd(cmd);
        // 测试搜索class文件
        // startJvmWithClassPath.test(cmd);
        // 测试解析class文件
        StartJvmWithClassFile.test(cmd);
    }

    /**
     * V1.0 测试简单Cmd命令行工具
     * @param cmd 命令行工具
     * @test java -help
     */
    private static void startJVMWithCmd(Cmd cmd) {
        System.out.printf("classpath: %s class: %s args: %s\n", cmd.getCpOption(), cmd.getClazz(), cmd.getArgs());
    }

}
