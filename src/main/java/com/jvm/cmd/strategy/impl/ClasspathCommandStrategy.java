package com.jvm.cmd.strategy.impl;

import com.jvm.cmd.Cmd;
import com.jvm.cmd.strategy.CommandStrategy;

/**
 * @Author zal
 * @Date 2023/9/15 21:58
 * @Description classpath命令处理策略
 * @Version 1.0
 */
public class ClasspathCommandStrategy implements CommandStrategy {
    @Override
    public void execute(Cmd cmd, String[] args) {
        cmd.setCpOption(args[2]);
    }
}
