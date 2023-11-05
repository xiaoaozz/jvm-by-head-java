package com.jvm.test;

import cn.hutool.log.Log;
import com.jvm.cmd.Cmd;
import com.jvm.test.startjvm.StartJvmWithClassFile;
import com.jvm.test.startjvm.StartJvmWithClassPath;

import java.util.Arrays;

/**
 * @Author zal
 * @Date 2023/9/15 20:13
 * @Description cmd测试类
 * @Version 1.0
 */
public class CmdTest {
    private final static Log log = Log.get("main主函数");

    public static void main(String[] args) {
        log.info("【启动类参数】{}", Arrays.toString(args));
        Cmd cmd = new Cmd(args);
        log.info("【cmd类】{}", cmd);
        // 测试命令行工具
        // StartJvmWithCmd.test(cmd);
        // 测试搜索class文件
//         StartJvmWithClassPath.test(cmd);
        // 测试解析class文件
        StartJvmWithClassFile.test(cmd);
    }
}
