package com.example.gestiondents.image;

import java.util.Base64;

public class Base64Utils {

    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}