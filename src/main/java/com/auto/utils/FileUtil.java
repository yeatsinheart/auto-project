package com.auto.utils;

import com.auto.structure.Base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {


    public static void mkDir(String filePath) {
        File file = new File(Base.basePath + filePath);
        if (file.exists()) {
            // file.mkdir();
        } else {
            mkDirs(file);
            file.mkdir();
        }
    }

    public static void mkDirs(File file) {
        if (file.getParentFile().exists()) {// 判断创建目录是否成功
            return;
        }
        if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
            return;
        }
    }

    public static void WriteStringToFile(String filePath, String content) {
        try {
            FileOutputStream fos = new FileOutputStream(Base.basePath + filePath);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 创建单个文件
    public static boolean createFile(String filePath) {
        File file = new File(Base.basePath + filePath);
        if (file.exists()) {// 判断文件是否存在
            return false;
        }
        if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
            return false;
        }
        if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
                return false;
            }
        }
        try {
            // 创建目标文件
            return file.createNewFile();
        } catch (IOException e) {// 捕获异常
            e.printStackTrace();
            return false;
        }
    }
}
