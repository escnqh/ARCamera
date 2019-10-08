package com.ntanougat.arcamera.util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by ESCNQH on 2018/4/9.
 */

public class ZipFileUtil {

    private ZipFileUtil(){}

    /**
     * @param tag4Log 方便打印Log提供信息
     * @param sourceFilePath 待压缩文件所在目录
     * @param zipFilePath 压缩包生成目录
     * @param fileName 生成压缩包文件名
     * @return 是否成功
     */
    public static boolean fileToZip(String tag4Log, String sourceFilePath, String zipFilePath, String fileName) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if(sourceFile.exists() == false) {
            Log.e(tag4Log,"待压缩的文件目录：" + sourceFilePath + " 不存在");
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                if(zipFile.exists()) {
                    Log.e(tag4Log,zipFilePath + " 目录下存在名字为：" + fileName + ".zip" + " 打包文件.");
                } else {
                    File[] sourceFiles = sourceFile.listFiles();
                    if(null == sourceFiles || sourceFiles.length < 1) {
                        Log.e(tag4Log,"待压缩的文件目录：" + sourceFilePath + " 里面不存在文件,无需压缩");
                    } else {
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[1024*10];
                        for(int i=0;i<sourceFiles.length;i++) {
                            // 创建ZIP实体,并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            // 读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis,1024*10);
                            int read = 0;
                            while((read=bis.read(bufs, 0, 1024*10)) != -1) {
                                zos.write(bufs, 0, read);
                            }
                        }
                        flag = true;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                // 关闭流
                try {
                    if(null != bis) bis.close();
                    if(null != zos) zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

        return flag;
    }
}
