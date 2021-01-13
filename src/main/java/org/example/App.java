package org.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.example.service.DocumentExtractorService;
import org.example.service.ItemPageParsingService;

public class App
{
    public static void main( String[] args )
    {
        String url = "https://www.amazon.com/GeForce-i7-10750H-Windows-Keyboard-15-dh1020nr/dp/B087YVJ44P";

        System.out.println(ItemPageParsingService.getItemFromPage(url));

    }
}