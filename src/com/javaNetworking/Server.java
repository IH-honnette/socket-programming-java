package com.javaNetworking;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class Server {

    public static void main(String[] args) throws IOException{
        // write your code
        try {
            ServerSocket serversocket =new ServerSocket(9000);

             while(true) {
                 Socket socket = serversocket.accept();//establishes connection
                 InputStream inFromClient = socket.getInputStream();
                 DataInputStream request = new DataInputStream(inFromClient);
                 System.out.println(request.readUTF());
                 OutputStream outToClient = socket.getOutputStream();
                 DataOutputStream response = new DataOutputStream(outToClient);
                 response.writeUTF("Your request processed" + LocalDateTime.now());
                 serversocket.close();
             }

        }catch(Exception e){
            System.out.println(e);
        }

    }
}
