package com.sc.tradmaster.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.util.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
//import javax.crypto.spec.DESedeKeySpec;
public class TripleDES {
    /**
     * 加密算法
     * @param source
     * @return
     */
    public static String SimpleEncript(String source){
        byte[] sourceBytes = source.getBytes();
        byte[] destBytes = new byte[sourceBytes.length];
        for( int i = 0;i< sourceBytes.length;i++)
        {
            destBytes[i] = (byte)(0xf ^ sourceBytes[i]);
        }
        BASE64Encoder base64 = new BASE64Encoder();
        return base64.encode(destBytes);
    }
    
    /**
     * 解密算法
     * @param source
     * @return
     * @throws IOException 
     */
    public static String SimpleDecript(String source) throws IOException{
        byte[] sourceBytes = new BASE64Decoder().decodeBuffer(source);
        byte[] destBytes = new byte[sourceBytes.length];
        for (int i = 0; i < sourceBytes.length; i++)
        {
            destBytes[i] = (byte)(0xf ^ sourceBytes[i]);
        }
        return new String(destBytes, "UTF-8");
    }

    /**
     * 测试
     * @param args
     * @throws UnsupportedEncodingException
     */
	public static void main(String[] args) {
		String str0 = SimpleEncript("123456");
		System.out.println(str0);
		String str;
		try {
			str = SimpleDecript("Pjo4Pz8+Nzs/PT8=");
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
