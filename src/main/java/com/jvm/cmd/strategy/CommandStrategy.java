package com.jvm.cmd.strategy;

import com.jvm.cmd.Cmd;

/**
 * @Author zal
 * @Date 2023/9/15 21:55
 * @Description 命令处理策略接口
 * @Version 1.0
 */
public interface CommandStrategy {

    /**
     * 处理策略
     *
     * @Param cmd 命令行工具
     * @param args 命令行参数
     */
    void execute(Cmd cmd, String[] args);
}
