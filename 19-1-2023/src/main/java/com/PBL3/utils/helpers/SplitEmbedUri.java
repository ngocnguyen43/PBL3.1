package com.PBL3.utils.helpers;

public class SplitEmbedUri {
    public static String split(String uri) {
        String[] parts = uri.split("/");
        return parts[parts.length - 1];
    }
}
