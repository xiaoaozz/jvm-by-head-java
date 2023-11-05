package com.jvm.cmd;

import com.jvm.constant.CommandConstants;
import com.jvm.cmd.strategy.CommandStrategy;
import com.jvm.cmd.strategy.impl.ClasspathCommandStrategy;
import com.jvm.cmd.strategy.impl.HelpCommandStrategy;
import com.jvm.cmd.strategy.impl.JreCommandStrategy;
import com.jvm.cmd.strategy.impl.VersionCommandStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @Author zal
 * @Date 2023/9/14 20:22
 * @Description 控制台实体类
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cmd {

    /**
     * classpath的路径
     */
    private String cpOption = "";

    /**
     * 包含Main方法的class文件
     */
    private String clazz;

    /**
     * 执行clazz文件需要的参数
     */
    private String[] args;

    /**
     * 使用-Xjre的选项，用来指定jre的路径
     */
    private String xJreOption;

    /**
     * 构造方法
     *
     * @param args 运行命令的参数
     */
    public Cmd(String[] args) {
        parseCmd(args);
    }


    /**
     * 解析参数，并执行相应操作
     *
     * @param args 运行命令参数
     */
    private void parseCmd(String[] args) {
        if (!validateArgs(args) || !args[0].equals(CommandConstants.COMMEND_JAVA)) {
            // 如果命令小于2个单词，说明命令格式错误
            printUsage();
            return;
        }
        // 类名称下标
        int classNameIndex = 1;
        // 获取命令类型
        String commandType = args[1];
        // 初始化不同的策略
        CommandStrategy strategy = null;

        switch (commandType) {
            case CommandConstants.COMMEND_HELP:
            case CommandConstants.COMMEND_SHORT_HELP:
                // 如果是帮助命令，则执行获取帮助命令策略
                strategy = new HelpCommandStrategy();
                break;
            case CommandConstants.COMMEND_VERSION:
                // 如果是版本命令，则执行版本命令策略
                strategy = new VersionCommandStrategy();
                break;
            case CommandConstants.COMMEND_CLASSPATH:
            case CommandConstants.COMMEND_SHORT_CLASSPATH:
                // 如果是classpath命令，则执行指定的classpath处理策略
                classNameIndex = 3;
                strategy = new ClasspathCommandStrategy();
                break;
            case CommandConstants.COMMEND_JRE:
                // 如果是-XJre命令，则执行指定的jre运行处理策略
                classNameIndex = 3;
                strategy = new JreCommandStrategy();
                break;
            default:
        }

        if (strategy != null) {
            // 如果找到了匹配的策略，执行策略
            strategy.execute(this, args);
        } else {
            // 否则输出提示信息
            printError();
        }
        // 类路径
        this.clazz = args[classNameIndex];
        // 参数值
        int numArgs = args.length - classNameIndex - 1;
        if (numArgs > 0) {
            this.args = new String[numArgs];
            System.arraycopy(args, classNameIndex + 1, this.args, 0, numArgs);
        } else {
            this.args = new String[0];
        }
    }

    /**
     * 校验命令行参数个数
     *
     * @param args 参数列表
     * @return 参数个数是否正确
     */
    private boolean validateArgs(String[] args) {
        if (args.length < CommandConstants.ERROR_LENGTH || !args[0].equals(CommandConstants.COMMEND_JAVA)) {
            return false;
        }
        return true;
    }

    /**
     * 命令行格式错误，输出提示信息
     */
    public void printUsage() {
        System.out.println("Usage: java [-options] class [args...]\n");
    }

    /**
     * 未识别的命令行，输出错误信息
     */
    public void printError() {
        System.out.println("Unrecognized command!");
    }

    /**
     * 解析成功但未加入处理策略的命令行，输出参数信息
     *
     * @param cmd 命令行类
     */
    public void printSuccess(Cmd cmd) {
        System.out.println("cmd pared successful!");
        for (int i = 0; i < cmd.getArgs().length; i++) {
            System.out.println(cmd.getArgs()[i]);
        }
    }
}
