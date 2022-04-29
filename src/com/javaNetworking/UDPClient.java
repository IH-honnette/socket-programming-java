package com.javaNetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main (String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        while (true) {
            String request;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your request:");
            request = reader.readLine();
            System.out.println(request);
            if (request.equals("bye")) {
                System.out.println("Byeeeee");
                socket.close();
                System.exit(0);
            }
            byte[] requestByte = request.getBytes();
            InetAddress serverIp = InetAddress.getLocalHost();
            DatagramPacket dprequest = new DatagramPacket(requestByte, requestByte.length, serverIp, 9000);

            socket.send(dprequest);
            //Receiving response to the server
            byte[] buffer = new byte[500];
            DatagramPacket dpresponse = new DatagramPacket(buffer, buffer.length);//this is like a byte array
            socket.receive(dpresponse);//received response is stored in dpresponse

            String responseStr = new String(dpresponse.getData(), 0, dpresponse.getLength());
            System.out.println(responseStr);

        }

    }
}
