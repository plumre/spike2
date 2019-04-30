package com.plumre.common;

/*
 * Created by renhongjiang on 2019/3/28.
 */

import java.io.*;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/28 16:15
 */
public class SerialDemo implements Serializable {


    private static final long serialVersionUID = -6568150735733089305L;

    public int num = 1390;

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("D:\\serial.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        SerialDemo serialDemo = new SerialDemo();
        oos.writeObject(serialDemo);
        oos.flush();
        oos.close();
    }
}