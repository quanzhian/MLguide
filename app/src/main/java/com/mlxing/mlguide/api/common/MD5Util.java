package com.mlxing.mlguide.api.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 使用java.security.MessageDigest类写的一个工具类用来获取MD5码
 * 
 * @author Talen
 * 
 * @see java.security.MessageDigest
 */

public class MD5Util {

    /**
     * 
     * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static char hexDigits[] =
                    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            // System.err.println(MD5Util.class.getName()
            // + "初始化失败，MessageDigest不支持MD5Util。");
            nsaex.printStackTrace();
        }
    }

    /**
     * 
     * 向getMD5方法传入一个你需要转换的原始字符串，将返回字符串的MD5码
     * 
     * @param code 原始字符串
     * 
     * @return 返回字符串的MD5码
     */

    public static String getMD5(String code) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = code.getBytes();
        byte[] results = messageDigest.digest(bytes);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte result: results) {
            // 将byte数组转化为16进制字符存入stringbuilder中
            stringBuilder.append(String.format("%02x", result));
        }
        return stringBuilder.toString();
    }

    /**
     * 
     * 适用于上G大的文件
     * 
     * @param file
     * 
     * @return
     * 
     * @throws IOException
     */

    @SuppressWarnings("resource")
	public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {

        String s = getMD5String(password);

        return s.equals(md5PwdStr);

    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());

    }

    /** */

    /**
     * 
     * main方法用于测试
     */

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 字符'a'的MD5码是0cc175b9c0f1b6a831c399e269772661,如果是，则成功。
        try {
            System.out.println(MD5Util.getMD5("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
