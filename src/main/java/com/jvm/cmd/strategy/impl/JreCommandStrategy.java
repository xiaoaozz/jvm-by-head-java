package com.jvm.cmd.strategy.impl;

import com.jvm.cmd.Cmd;
import com.jvm.cmd.strategy.CommandStrategy;

/**
 * @Author zal
 * @Date 2023/9/15 22:05
 * @Description -Xjre命令处理策略
 * @Version 1.0
 */
public class JreCommandStrategy implements CommandStrategy {
    @Override
    public void execute(Cmd cmd, String[] args) {
        cmd.setxJreOption(args[2]);
    }
}
