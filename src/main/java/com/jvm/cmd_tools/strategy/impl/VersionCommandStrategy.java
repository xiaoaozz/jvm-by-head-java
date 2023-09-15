package com.jvm.cmd_tools.strategy.impl;

import com.jvm.cmd_tools.strategy.CommandStrategy;

/**
 * @Author zal
 * @Date 2023/9/15 21:58
 * @Description 版本信息命令处理策略
 * @Version 1.0
 */
public class VersionCommandStrategy implements CommandStrategy {
    @Override
    public void execute(String[] args) {
        // 打印版本信息
        System.out.println("java version \"1.8.0_371\"\n" +
                "Java(TM) SE Runtime Environment (build 1.8.0_371-b11)\n" +
                "Java HotSpot(TM) 64-Bit Server VM (build 25.371-b11, mixed mode)");
    }
}
