package com.jvm.cmd_tools.strategy;

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
     * @param args 命令行参数
     */
    void execute(String[] args);
}
