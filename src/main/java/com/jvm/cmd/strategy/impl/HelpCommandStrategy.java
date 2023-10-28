package com.jvm.cmd.strategy.impl;

import com.jvm.cmd.Cmd;
import com.jvm.cmd.strategy.CommandStrategy;

/**
 * @Author zal
 * @Date 2023/9/15 21:56
 * @Description 获取帮助命令处理策略
 * @Version 1.0
 */
public class HelpCommandStrategy implements CommandStrategy {
    @Override
    public void execute(Cmd cmd, String[] args) {
        // 打印帮助信息
        System.out.println("用法: java [-options] class [args...]\n" +
                "           (执行类)\n" +
                "   或  java [-options] -jar jarfile [args...]\n" +
                "           (执行 jar 文件)\n" +
                "其中选项包括:\n" +
                "    -d32\t  使用 32 位数据模型 (如果可用)\n" +
                "    -d64\t  使用 64 位数据模型 (如果可用)\n" +
                "    -server\t  选择 \"server\" VM\n" +
                "                  默认 VM 是 server,\n" +
                "                  因为您是在服务器类计算机上运行。\n\n" +
                "    -cp <目录和 zip/jar 文件的类搜索路径>\n" +
                "    -classpath <目录和 zip/jar 文件的类搜索路径>\n" +
                "                  用 : 分隔的目录, JAR 档案\n" +
                "                  和 ZIP 档案列表, 用于搜索类文件。\n" +
                "    -D<名称>=<值>\n" +
                "                  设置系统属性\n" +
                "    -verbose:[class|gc|jni]\n" +
                "                  启用详细输出\n" +
                "    -version      输出产品版本并退出\n" +
                "    -version:<值>\n" +
                "                  警告: 此功能已过时, 将在\n" +
                "                  未来发行版中删除。\n" +
                "                  需要指定的版本才能运行\n" +
                "    -showversion  输出产品版本并继续\n" +
                "    -jre-restrict-search | -no-jre-restrict-search\n" +
                "                  警告: 此功能已过时, 将在\n" +
                "                  未来发行版中删除。\n" +
                "                  在版本搜索中包括/排除用户专用 JRE\n" +
                "    -? -help      输出此帮助消息\n" +
                "    -X            输出非标准选项的帮助\n" +
                "    -ea[:<packagename>...|:<classname>]\n" +
                "    -enableassertions[:<packagename>...|:<classname>]\n" +
                "                  按指定的粒度启用断言\n" +
                "    -da[:<packagename>...|:<classname>]\n" +
                "    -disableassertions[:<packagename>...|:<classname>]\n" +
                "                  禁用具有指定粒度的断言\n" +
                "    -esa | -enablesystemassertions\n" +
                "                  启用系统断言\n" +
                "    -dsa | -disablesystemassertions\n" +
                "                  禁用系统断言\n" +
                "    -agentlib:<libname>[=<选项>]\n" +
                "                  加载本机代理库 <libname>, 例如 -agentlib:hprof\n" +
                "                  另请参阅 -agentlib:jdwp=help 和 -agentlib:hprof=help\n" +
                "    -agentpath:<pathname>[=<选项>]\n" +
                "                  按完整路径名加载本机代理库\n" +
                "    -javaagent:<jarpath>[=<选项>]\n" +
                "                  加载 Java 编程语言代理, 请参阅 java.lang.instrument\n" +
                "    -splash:<imagepath>\n" +
                "                  使用指定的图像显示启动屏幕\n" +
                "有关详细信息, 请参阅 http://www.oracle.com/technetwork/java/javase/documentation/index.html。");

    }
}
