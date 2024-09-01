package com.feng.log.strategy;

import com.feng.log.config.YmlConfig;
import com.feng.log.myenum.LogType;
import com.feng.log.pojo.R;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class LogStrategyFactory {
    private static Map<LogType, LogStrategy> strategy = new HashMap<>();

    public static R solve(YmlConfig ymlConfig) {
        LogStrategy logStrategy = strategy.get(ymlConfig.getLogType());
        return logStrategy.tackle(ymlConfig);
    }

    public static void init() throws ClassNotFoundException {
        // 找到同包下所有实现了LogStrategy接口的类
        Class<LogStrategyFactory> factoryClass = LogStrategyFactory.class;
        String packageName = factoryClass.getPackage().getName();

        ClassLoader classLoader = factoryClass.getClassLoader();
        ArrayList<Class<?>> classes = new ArrayList<>();
        try {
            String path = packageName.replaceAll("\\.", "/");
            Enumeration<URL> resources = classLoader.getResources(path); // 生成的target里面，寻找资源
            while ( resources.hasMoreElements() ) {
                URL resource = resources.nextElement();
                if ( resource.getProtocol().startsWith("file") ) { // 文件
                    File directory = new File(URLDecoder.decode(resource.getFile(), "UTF-8"));
                    File[] files = directory.listFiles(); // 列出这个com/feng/log/strategy目录下面的所有文件
                    if ( files != null ) {
                        for (File file : files) {
                            if (file.isDirectory()) continue; // 如果是目录就跳过
                            if (file.getName().endsWith(".class")) {
                                String className = file.getName().substring(0, file.getName().length() - 6);
                                classes.add(Class.forName(packageName + '.' + className, true, classLoader));
                            }
                        }
                    }
                }
            }
            // 加载到了所有类，初始化这些类，并放入策略工厂里面
            initClass(classes);
        } catch (IOException e) {
            System.err.println("找不到该包下的任何类");
            e.printStackTrace();
        }
    }

    private static void initClass(List<Class<?>> classes) {
        for (Class<?> aClass : classes) {
            if (!aClass.isInterface()) { // 如果不是接口
                Method[] methods = aClass.getDeclaredMethods();
                boolean sign = true;
                for (int i = 0; i < methods.length; i++) {
                    // System.err.println(methods[i].getName());
                    if ( !methods[i].getName().equals("tackle")) {
                        sign = false;
                    }
                }
                if ( sign ) {
                    Constructor<?>[] constructors = aClass.getConstructors();
                    try {
                        LogType type = getType(aClass.getSimpleName());// 获取的是类名！getName是全类名
                        LogStrategy st = (LogStrategy) constructors[0].newInstance();
                        strategy.put(type, st);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        System.err.println("反射构造对象失败！！");
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static LogType getType(String type) {
        LogType type1 = null;
        switch ( type ) {
            case "DebugLog":
                type1 = LogType.DEBUG;
                break;
            case "InfoLog":
                type1 = LogType.INFO;
                break;
            case "WarnLog":
                type1 = LogType.WARN;
                break;
            default:
                type1 = LogType.INFO;
        }
        return type1;
    }
}
