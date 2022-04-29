package com.javaNetworking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;

public class UDPServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket =new DatagramSocket(9000);
//        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket dprequest = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(dprequest);

            String requestStr = new String(dprequest.getData(), 0, dprequest.getLength());

            //capture the ip address fo the client (server)
            //sending response
            InetAddress clientIp = dprequest.getAddress();
            int clientPort = dprequest.getPort();

            String toclientResponseStr = "Your request was processed!";
            byte[] toclientResponse = toclientResponseStr.getBytes();

            DatagramPacket dpResponseToclient = new DatagramPacket(toclientResponse,
                    toclientResponse.length, clientIp, clientPort);
            serverSocket.send(dpResponseToclient);
//        }
        serverSocket.close();
    }
}
