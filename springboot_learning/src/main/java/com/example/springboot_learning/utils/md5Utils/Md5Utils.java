//package com.example.springboot_learning.utils.md5Utils;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.security.MessageDigest;
//import java.util.logging.Logger;
//
///**
// * @ProjectName: Md5Utils.java
// * @Package: net.zlr.fengine.utils
// * @ClassName Md5Utils
// * @Author pengguo
// * @Description MD5加密
// * @Date 11:36 2020/1/2
// * @version v1.0.0
// */
//public class Md5Utils implements PasswordEncoder {
////    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);
//
//    private static byte[] md5(String s)
//    {
//        MessageDigest algorithm;
//        try
//        {
//            algorithm = MessageDigest.getInstance("MD5");
//            algorithm.reset();
//            algorithm.update(s.getBytes("UTF-8"));
//            byte[] messageDigest = algorithm.digest();
//            return messageDigest;
//        }
//        catch (Exception e)
//        {
////            log.error("MD5 Error...", e);
//        }
//        return null;
//    }
//
//    private static final String toHex(byte hash[])
//    {
//        if (hash == null)
//        {
//            return null;
//        }
//        StringBuffer buf = new StringBuffer(hash.length * 2);
//        int i;
//
//        for (i = 0; i < hash.length; i++)
//        {
//            if ((hash[i] & 0xff) < 0x10)
//            {
//                buf.append("0");
//            }
//            buf.append(Long.toString(hash[i] & 0xff, 16));
//        }
//        return buf.toString();
//    }
//
//    public static String hash(String s)
//    {
//        try
//        {
//            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
//        }
//        catch (Exception e)
//        {
////            log.error("not supported charset...{}", e);
//            return s;
//        }
//    }
//
//    public String encode(CharSequence rawPassword) {
//        //将rawPassword转换成MD5加密后的字符串
//        return hash(rawPassword.toString());
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        //将rawPasswords是需要加密的密码和encodePassword是存入数据库的密码
//        String input = hash(rawPassword.toString());
//        return input.equals(encodedPassword.toString());
//    }
//}
