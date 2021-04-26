package com.wjy.my.java.explore.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 使用缓冲区读写文件测试</br>
 * BufferedInputStream的默认缓冲区大小是8192字节。当每次读取数据量接近或远超这个值时，两者效率就没有明显差别了。</br>
 */
public class BufferTest {

    public static void main(String[] args) throws Exception {
        String from = "F:\\gc.log";
        String[] writeTo = new String[] { "F:\\\\gc.log1", "F:\\\\gc.log2" };

        long startTime1 = System.currentTimeMillis();
        readWrite(from, writeTo[0]);
        long endTime1 = System.currentTimeMillis();
        System.out.println("直接读写耗时：" + (endTime1 - startTime1) + "ms");

        long startTime2 = System.currentTimeMillis();
        readWriteWithBuffer(from, writeTo[1]);
        long endTime2 = System.currentTimeMillis();
        System.out.println("使用缓冲区读写耗时：" + (endTime2 - startTime2) + "ms");

        for (String filePath : writeTo) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
                System.out.println(filePath + " 删除成功");
            }
        }
    }

    /***************************************************************************
     * 直接读取文件
     *
     * @param from
     * @param to
     * @throws IOException
     */
    public static void readWrite(String from, String to) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(from);
            out = new FileOutputStream(to);
            while (true) {
                int data = in.read();
                if (data == -1) {
                    break;
                }
                out.write(data);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    /***************************************************************************
     * 使用缓存区读写文件
     *
     * @param from
     * @param to
     * @throws IOException
     */
    public static void readWriteWithBuffer(String from, String to)
            throws IOException {
        InputStream inBuffer = null;
        OutputStream outBuffer = null;
        try {
            inBuffer = new BufferedInputStream(new FileInputStream(from));
            outBuffer = new BufferedOutputStream(new FileOutputStream(to));
            while (true) {
                int data = inBuffer.read();
                if (data == -1) {
                    break;
                }
                outBuffer.write(data);
            }
        } finally {
            if (inBuffer != null) {
                inBuffer.close();
            }
            if (outBuffer != null) {
                outBuffer.close();
            }
        }
    }

}
