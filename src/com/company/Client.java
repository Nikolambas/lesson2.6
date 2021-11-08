package com.company;

import java.io.*;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {
        Client client = new Client();
    }

    public Client() {
        startConnect();
    }

    private void startConnect() {
        try (Socket socket = new Socket("localhost", 8198);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream wr = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());) {
            System.out.println("Вы подключились к серверу");
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        while (true) {
//
//                        }
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
            while (true){
                System.out.println(in.readUTF());
                wr.writeUTF("Сообщение от клиента: "+consoleReader.readLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
