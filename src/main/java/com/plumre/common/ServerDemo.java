package com.plumre.common;

/*
 * Created by renhongjiang on 2019/3/28.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/28 16:50
 */
public class ServerDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9929);
        Socket socket = serverSocket.accept();
        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String s = sysin.readLine();
        while (!"bye".equals(s)) {
            printWriter.println(s);
            printWriter.flush();
            System.out.println("Server: " + s);
            System.out.println("Client: " + reader1.readLine());
            s = sysin.readLine();
        }
        printWriter.close();
        reader1.close();
        socket.close();
        serverSocket.close();
    }

}