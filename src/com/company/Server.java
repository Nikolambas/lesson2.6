package com.company;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            ServerSocket serverSocket = new ServerSocket(8198);
            System.out.println("Ждем подключение клиента");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream wr = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(in.readUTF());
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }).start();
            while (true){
                wr.writeUTF("Сообщение от сервера: "+consoleReader.readLine());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
