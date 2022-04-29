package com.javaNetworking;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {
        try {
          Socket socket =new Socket("localhost",9000);
            SocketAddress localhost;
            socket.connect(localhost);
          OutputStream outToServer =socket.getOutputStream();
          DataOutputStream request =new DataOutputStream(outToServer);
                     request.writeUTF("Hello Server!");
          InputStream inFromServer =socket.getInputStream();
          DataInputStream response =new DataInputStream(inFromServer);
            System.out.println(response.readUTF());
            socket.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }
}