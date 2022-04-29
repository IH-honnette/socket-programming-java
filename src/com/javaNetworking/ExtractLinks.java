package com.javaNetworking;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ExtractLinks{
    public static void main(String[] args) throws Exception {
        System.out.println("LINK EXTRACTOR PROGRAM IN JAVA\n------------------------------");
        System.out.println("N.B: Downloads will be placed in downloads folder \n");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter valid URL of any homepage: ");
            URL myURL = new URL(scanner.next());
            extractLinks(myURL);
        }
        catch(MalformedURLException e) {
            System.out.println("You entered an invalid URL! --Enter http(s) links--");
            System.exit(0);
        }
    }
    public static void extractLinks(URL customURL) {
        System.out.println("---------" + customURL + "----------");
        try {
            String url = customURL.toString();
            Document document = Jsoup.connect(url).get();
            Elements allLinks = document.getElementsByTag("a");
            int numberOfLinks = 0;
            for(Element link: allLinks) {
                numberOfLinks++;
                String absoluteUrl = link.attr("abs:href");
                // Write to files
                writeToFiles(absoluteUrl, numberOfLinks);
                System.out.println("Link " + numberOfLinks + "  " + absoluteUrl);
            }
            System.out.print("NUMBER OF LINKS FOUND: " + numberOfLinks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeToFiles(String absoluteURL, int index) throws IOException {
        URL url = new URL(absoluteURL);
        File downloads = new File("downloads");
        if(!downloads.exists()) {
            downloads.mkdir();
        }
        File myFile = new File("./downloads/" + index + "_" + url.getHost() + ".html");
        if(myFile.createNewFile()) {
            URLConnection myURLConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            String inputLine;
            FileWriter myWriter = new FileWriter("./downloads/" + index + "_" + url.getHost() + ".html");
            while ((inputLine = in.readLine()) != null) {
                myWriter.write(inputLine);
            }
            myWriter.close();
        }else {
            System.out.println("You have already downloaded, check downloads folder");
            System.exit(0);
        }
    }
}

