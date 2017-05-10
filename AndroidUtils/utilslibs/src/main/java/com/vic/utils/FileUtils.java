package com.vic.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;


/**
 * Created by liu song on 2017/1/11.
 */

public class FileUtils {

    /**
     * 复制图片
     *
     * @param oldPath 旧的图片路径
     * @param newPath 新的图片路径
     */
    public static void copyFile(String oldPath, String newPath) {
        InputStream is = null;
        OutputStream os = null;
        try {
            File oldFile = new File(oldPath);
            File newFile = new File(newPath);
            if (oldFile.exists()) {
                if (!newFile.getParentFile().exists()) {
                    newFile.getParentFile().mkdirs();
                }
                if (newFile.exists()) {
                    new File(newPath).delete();
                }
                is = new FileInputStream(oldFile);
                os = new FileOutputStream(newFile);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算常规缓存大小
     *
     * @param context
     * @return
     */
    public static String getCacheSize(Context context) {
        long fileSize = 0;
        String cacheSize = "0KB";

        fileSize += getDirSize(context.getFilesDir()); // /data/data/package_name/files
        fileSize += getDirSize(context.getCacheDir()); // /data/data/package_name/cache
        fileSize += getDirSize(context.getExternalCacheDir()); // /SDCard/Android/data/package_name/cache/

        File externalCacheDir = context.getExternalCacheDir();
        fileSize += getDirSize(externalCacheDir);

        if (fileSize > 0) {
            return formatFileSize(fileSize);
        } else {
            return cacheSize;
        }
    }

    /**
     * 转换文件单位大小
     *
     * @param size
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "KB";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 清除常规缓存文件
     *
     * @param context
     */
    public static void clearCacheFiles(Context context) {
        deleteFile(context.getCacheDir());
        deleteFile(context.getFilesDir());
        deleteFile(context.getExternalCacheDir());
        deleteFile(new File(context.getCacheDir().getParent() + File.separator + "app_webview"));
    }

    /**
     * 删除文件夹和文件夹下的子文件
     *
     * @param dir
     */
    private static void deleteFile(File dir) {
        if (dir == null) return;
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    deleteFile(file);
                }
            }
        }
        //移除空文件夹
        dir.delete();
    }

}
