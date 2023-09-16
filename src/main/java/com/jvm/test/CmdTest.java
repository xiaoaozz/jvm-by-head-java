package com.jvm.test;

import com.jvm.cmd.Cmd;

import java.util.Scanner;

/**
 * @Author zal
 * @Date 2023/9/15 20:13
 * @Description cmd测试类
 * @Version 1.0
 */
public class CmdTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String cmdLine = in.nextLine();
        String[] argsArr = cmdLine.split("\\s+");
        Cmd cmd = new Cmd(argsArr);
    }
}
