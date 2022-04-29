package com.javaNetworking;

import static java.lang.Integer.parseInt;

public class CatPrep {
    public static void main(String[] args) {
        System.out.println(args);
        int port = Integer.parseInt(args[0]);
        System.out.println("Parsed = "+port);
    }
}
