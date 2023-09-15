package com.jvm.cmd_tools.parse;

import com.jvm.cmd_tools.enums.CommandConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 输入命令格式是否正确，默认为true
     */
    private boolean isRightFmt = true;

    /**
     * 是否使用help帮助命令
     */
    private boolean helpFlag;

    /**
     * 是否使用version查看版本命令
     */
    private boolean versionFlag;

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
        int index = 1;
        if (validateArgs(args)) {
            // 如果命令小于2个单词，说明命令格式错误
            isRightFmt = false;
            return;
        }
        if (!args[0].equals(CommandConstants.COMMEND_JAVA)) {
            // 如果命令不是以java开头的，则是错误的命令行命令
            isRightFmt = false;
        } else {
            if (args[1].equals(CommandConstants.COMMEND_HELP) || args[1].equals(CommandConstants.COMMEND_SHORT_HELP)) {
                // 如果是帮助命令，则输出帮助文本信息
                helpFlag = true;
            } else if (args[1].equals(CommandConstants.COMMEND_VERSION)) {
                // 如果是版本命令，则输出版本信息
                versionFlag = true;
            } else if (args[1].equals(CommandConstants.COMMEND_CLASSPATH) || args[1].equals(CommandConstants.COMMEND_SHORT_CLASSPATH)) {
                // 如果是classpath命令，则执行指定的classpath路径运行类
                if (validateArgs(args)) {
                    isRightFmt = false;
                }
                index = 4;
                this.cpOption = args[2];
            } else if (args[1].equals(CommandConstants.COMMEND_JRE)) {
                // 如果是-XJre命令，则执行指定的jre运行类
                if (validateArgs(args)) {
                    isRightFmt = false;
                }
                index = 4;
                this.xJreOption = args[2];
            }
            // 类名是参数的最后一个元素
            this.clazz = args[index - 1];
            // 入参是参数的倒数第二个元素
            this.args = new String[args.length - index];
            // 多个参数赋值
            for (int i = index; i < args.length; i++) {
                this.args[i - index] = args[i];
            }
        }
    }

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
     * 打印版本信息
     */
    public void printVersion() {
        System.out.println("java version \"1.8.0_371\"\n" +
                "Java(TM) SE Runtime Environment (build 1.8.0_371-b11)\n" +
                "Java HotSpot(TM) 64-Bit Server VM (build 25.371-b11, mixed mode)");
    }

}
