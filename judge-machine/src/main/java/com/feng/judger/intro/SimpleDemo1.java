package com.feng.judger.intro;

import java.io.*;

public class SimpleDemo1 {
    public static void main(String[] args) {
        compileJavaAndRun("D:\\compile_dir", "Simple01.java");
    }
    // 3.测试编译
    public static void compileJavaAndRun( String dir, String javaName ) {
        String[] split = javaName.split("\\.");
        String name = split[0];
        String sufferFix = split[1];

        try {
            // 设置目录
            File file = new File(dir);
            ProcessBuilder builder = new ProcessBuilder("javac", javaName); // 编译命令
            builder.directory(file);
            // 执行命令
            Process process = builder.start();
            int exitCode = process.waitFor();
            if ( exitCode == 0 ) {
                // 运行命令
                // 准备运行命令
                ProcessBuilder runBuilder = new ProcessBuilder("java", name);
                runBuilder.directory(file);
                Process run = runBuilder.start(); // 运行
                int runCode = run.waitFor();
                if ( runCode == 0 ) {
                    System.out.println("错误流：");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(run.getErrorStream()));
                    String s;
                    while ( (s = reader.readLine()) != null ) {
                        System.out.println(s);
                    }
                    System.out.println("===============");
                    System.out.println("标准流：");
                    BufferedReader reader1 = new BufferedReader(new InputStreamReader(run.getInputStream()));
                    while ( (s = reader1.readLine()) != null ) {
                        System.out.println(s);
                    }
                }
            }


        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    // 2.编写代码，写进java文件里面
    public static void writeCodeInJavaFile() {
        writeCodeIn("D:\\compile_dir\\Simple01.java", readCode());
    }
    private static String readCode() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("test.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder str = new StringBuilder();
        String lines;
        try {
            while ( (lines = reader.readLine()) != null) {
                str.append(lines).append("\n");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return str.toString();
    }

    private static void writeCodeIn( String path, String code ) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(code);
            writer.flush(); // 缓存flush一下
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }

    // 1. java调用系统命令
    public static void testSimple() {
        // 1.测试简单的命令执行
        try {
            Process process = new ProcessBuilder("cmd.exe", "/c", "java -version").start();
            // 捕获标准错误流（Java 版本信息输出到 stderr）
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine;
            System.out.println("java信息: ");
            while ((errorLine = errorReader.readLine()) != null) {
                System.out.println(errorLine);
            }

            // 捕获标准输出流（如果命令有 stdout 输出）
            BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String outputLine;
            System.out.println("标准控制台信息: ");
            while ((outputLine = outputReader.readLine()) != null) {
                System.out.println(outputLine);
            }
            // 等待命令执行完成并获取退出码
            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
