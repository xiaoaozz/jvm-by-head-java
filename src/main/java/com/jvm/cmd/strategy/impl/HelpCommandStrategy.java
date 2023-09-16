package com.jvm.cmd.strategy.impl;

import com.jvm.cmd.strategy.CommandStrategy;

/**
 * @Author zal
 * @Date 2023/9/15 21:56
 * @Description 获取帮助命令处理策略
 * @Version 1.0
 */
public class HelpCommandStrategy implements CommandStrategy {
    @Override
    public void execute(String[] args) {
        // 打印帮助信息
    }
}
