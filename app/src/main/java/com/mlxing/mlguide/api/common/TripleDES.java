package com.mlxing.mlguide.api.common;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class TripleDES {

	/**
	 * 加密用的Key 可以用A B C D E F字母和数字组成，每两个字节表示一个字符
	 */
	public String SKEY = "FC0EF13DA5545ED86E019D07806DBDCA6ACDCA1AB083761D";

	// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
	private static byte[] IVBYTES = { 1, 3, 2, 8, 5, 6, 4, 7 };

	// 加解密统一使用的编码方式
	private final static String encoding = "UTF-8";

	private static byte[] hexStr2ByteArr( String strIn ) throws NumberFormatException {
		byte[] arrB;
		try {
			arrB = strIn.getBytes( encoding );
		}
		catch( UnsupportedEncodingException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			arrB = strIn.getBytes();
		}
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for( int i = 0; i < iLen; i = i + 2 ) {
			String strTmp = new String( arrB, i, 2 );
			arrOut[i / 2] = (byte) Integer.parseInt( strTmp, 16 );
		}
		return arrOut;
	}

	// 加密
	public String Encrypt(String sSrc ) throws Exception {
		DESedeKeySpec dks = new DESedeKeySpec( hexStr2ByteArr( SKEY ) );
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "DESede" );
		SecretKey securekey = keyFactory.generateSecret( dks );
		IvParameterSpec iv = new IvParameterSpec( IVBYTES );
		Cipher cipher = Cipher.getInstance( "DESede/CBC/PKCS5Padding" );
		cipher.init( Cipher.ENCRYPT_MODE, securekey, iv );
		byte[] encrypted = cipher.doFinal( sSrc.getBytes( encoding ) );
		// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		return Base64.encode( encrypted );
	}

	// 解密
	public String Decrypt(String sSrc ) throws Exception {
		DESedeKeySpec dks = new DESedeKeySpec( hexStr2ByteArr( SKEY ) );
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "DESede" );
		SecretKey securekey = keyFactory.generateSecret( dks );
		IvParameterSpec iv = new IvParameterSpec( IVBYTES );
		Cipher cipher = Cipher.getInstance( "DESede/CBC/PKCS5Padding" );
		cipher.init( Cipher.DECRYPT_MODE, securekey, iv );
		// 先用base64解密
		byte[] encrypted1 = Base64.decode( sSrc );
		try {
			byte[] original = cipher.doFinal( encrypted1 );
			String originalString = new String( original );
			return originalString;
		}
		catch( Exception e ) {
			System.out.println( e.toString() );
			return null;
		}
	}

	public String getSKEY() {
		return SKEY;
	}

	public void setSKEY( String sKEY ) {
		SKEY = sKEY;
	}

	public static void main( String[] args ) throws Exception {
		String content = "970627&&R^Y=werasdfjoiqewtoi";
		TripleDES tripleDes = new TripleDES();
		String result = tripleDes.Encrypt( content );
	}

}
