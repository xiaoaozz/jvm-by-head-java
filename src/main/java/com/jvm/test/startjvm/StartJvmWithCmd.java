package com.jvm.test.startjvm;

import com.jvm.cmd.Cmd;

import java.util.Arrays;

/**
 * @Author zal
 * @Date 2023/11/05  19:39
 * @Description: 测试简单Cmd命令行工具
 * @Version: 1.0
 */
public class StartJvmWithCmd {
    public static void test(Cmd cmd) {
        System.out.printf("classpath: %s class: %s args: %s\n", cmd.getCpOption(), cmd.getClazz(), Arrays.toString(cmd.getArgs()));
    }
}
