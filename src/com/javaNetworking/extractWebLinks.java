package com.javaNetworking;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

//extract web links by recursion.
public class extractWebLinks{
    private void addLinksToFiles(String stringUrl, int index) throws IOException {
       URL url =new URL(stringUrl);
       File extractedLinks =new File("extractedLinks");
       if(!extractedLinks.exists()) extractedLinks.mkdir();
       File linkFile =new File("./extractedLinks/"+index+"__"+url.getHost()+".html");
       if(linkFile.createNewFile()) {
           FileWriter writer = null;
           try {
               URLConnection connectRemote =url.openConnection();

               BufferedReader bufferIn = new BufferedReader(new InputStreamReader
                   (connectRemote.getInputStream()));
               String line;
               writer = new FileWriter("./extractedLinks/" + index + "__" + url.getHost() + ".html");
               while ((line = bufferIn.readLine()) != null) {
                   writer.write(line);
               }
           } catch (Exception e) {
               System.out.println("unable to find valid certification path to requested target");
               writer.write("unable to find valid certification path to requested target ");
           }finally {
               writer.close();
                }
           }else {
           System.out.println("File Already exists!");
           System.exit(0);
       }
    }
    private void get_links(URL url) {
        try {
            Document doc = Jsoup.connect(url.toString()).get();
            Elements links = doc.getElementsByTag("a");
            if (links.isEmpty()) {
                System.out.println("Web has no links");
                System.exit(0);
              }
                int numberOfLinks = 0;
                for(Element link: links) {
                    numberOfLinks++;
                    String absoluteUrl = link.attr("abs:href");
                    System.out.println("Link " + numberOfLinks + "  " + absoluteUrl);
                    addLinksToFiles(absoluteUrl,numberOfLinks);
                    continue;
                }
                System.out.print("NUMBER OF LINKS FOUND: " + numberOfLinks);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        System.out.println("Program That Extracts all Links In A WEB");
        extractWebLinks obj = new extractWebLinks();
        try {
            Scanner scanner =new Scanner(System.in);
            System.out.print("Enter valid URL of any homepage: ");
            URL url =new URL(scanner.next());
            obj.get_links(url);
        }catch(MalformedURLException e) {
            System.out.println("You entered an invalid URL! ->Enter http(s) links only");
            System.exit(0);
        }
    }

}
