package com.example.thene.bsm;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by thene on 14.11.2016.
 */

public class PassHandler {
    private static String password;
    private static String message;
    private static Random rand = new Random();
    private static byte[] salt = new byte[12];
    //private static String salt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";


    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        PassHandler.message = message;
    }

    public static void setPassword(String password) {
        rand.nextBytes(PassHandler.salt);
        PassHandler.password = md5( password + PassHandler.salt ).toString();
    }

    public static boolean checkPassword(String password){
        password = md5( password + PassHandler.salt ).toString();

        if(password.equals(PassHandler.password)){
            return true;
        }
        else{ return false; }
    }

    private static String md5(String input) {

        String md5 = null;

        if(null == input) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }
}
