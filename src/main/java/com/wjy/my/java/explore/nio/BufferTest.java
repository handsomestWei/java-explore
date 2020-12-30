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
 * ʹ�û�������д�ļ�����</br>
 * BufferedInputStream��Ĭ�ϻ�������С��8192�ֽڡ���ÿ�ζ�ȡ�������ӽ���Զ�����ֵʱ������Ч�ʾ�û�����Բ���ˡ�</br>
 */
public class BufferTest {

    public static void main(String[] args) throws Exception {
        String from = "F:\\gc.log";
        String[] writeTo = new String[] { "F:\\\\gc.log1", "F:\\\\gc.log2" };

        long startTime1 = System.currentTimeMillis();
        readWrite(from, writeTo[0]);
        long endTime1 = System.currentTimeMillis();
        System.out.println("ֱ�Ӷ�д��ʱ��" + (endTime1 - startTime1) + "ms");

        long startTime2 = System.currentTimeMillis();
        readWriteWithBuffer(from, writeTo[1]);
        long endTime2 = System.currentTimeMillis();
        System.out.println("ʹ�û�������д��ʱ��" + (endTime2 - startTime2) + "ms");

        for (String filePath : writeTo) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
                System.out.println(filePath + " ɾ���ɹ�");
            }
        }
    }

    /***************************************************************************
     * ֱ�Ӷ�ȡ�ļ�
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
     * ʹ�û�������д�ļ�
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
