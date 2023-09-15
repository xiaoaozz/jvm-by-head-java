package com.jvm.test;

import com.jvm.cmd_tools.parse.Cmd;

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
        String[] cmds = cmdLine.split("\\s+");
        Cmd cmd = new Cmd(cmds);
        if (!cmd.isRightFmt()) {
            System.out.println("Unrecognized command!");
            cmd.printUsage();
        } else {
            if (cmd.isVersionFlag()) {
                cmd.printVersion();
            } else if (cmd.isHelpFlag()) {
                cmd.printUsage();
            } else {
                System.out.println("cmd pared successful!");
                for (int i = 0; i < cmd.getArgs().length; i++) {
                    System.out.println(cmd.getArgs()[i]);
                }
            }
        }
    }
}
