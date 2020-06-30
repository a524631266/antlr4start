package com.zhangll.state.version2;

import java.io.*;

public class SeriUtil {
    public static String path = "data/";

    public static void serilize(Object ob) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(
                new FileOutputStream(path+ ob.getClass().getName())
        );
        obj.writeObject(ob);
        obj.flush();
        obj.close();
    }

    public static Object deSerilize(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(path + filename)
        );
        Object o = objectInputStream.readObject();
        objectInputStream.close();
        return o;
    }
}
