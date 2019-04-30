package com.plumre.util;

/*
 * Created by renhongjiang on 2019/3/25.
 */

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/25 20:01
 */
@Component
public class MD5Utils {


    public static String encodeByMD5(String s) throws NoSuchAlgorithmException {

        /**
         * Algorithm
         */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder encoder = new BASE64Encoder();

        /**
         * encrypt
         */
        return encoder.encode(md5.digest(s.getBytes(StandardCharsets.UTF_8)));

    }
}