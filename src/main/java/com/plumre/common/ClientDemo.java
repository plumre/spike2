package com.plumre.common;

/*
 * Created by renhongjiang on 2019/3/28.
 */

import java.io.*;
import java.net.Socket;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/28 16:40
 */
public class ClientDemo {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9929);
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String s = sysin.readLine();
        while (!"bye".equals(s)) {
            printWriter.println(s);
            printWriter.flush();
            System.out.println("Client: " + s);
            System.out.println("Server: " + reader1.readLine());
            s = sysin.readLine();
        }
        printWriter.close();
        reader1.close();
        socket.close();
    }
}