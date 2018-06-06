package cn.ray.utils;

public class MyEL {

    public static String sub(String str) {
        if (str.length()>10) {
            return str.substring(0, 10)+"......";
        }
        return str;
    }
}
