package org.example.service;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.example.model.Item;

import java.util.List;

public class ItemPageParsingService {

    public static Item getItemFromPage(String url) {
        Document document = DocumentExtractorService.getDocument(url);
        Element element = document.getElementById("ppd");

        String name = extractName(element);
        String code = extractCode(url);
        int price = extractPrice(element);
        int initPrice = extractInitPrice(element) == 0 ? price : extractInitPrice(element);
//        String imageUrl = extractImageUrl(element);
//        String group = extractGroup(document);
//        String seller = extractSeller(element);

        return Item.builder()
                .code(code)
                .name(name)
                .price(price)
                .initPrice(initPrice)
//                .group(group)
                .url(url)
//                .imageUrl(imageUrl)
//                .seller(seller)
                .build();
    }

    private static int extractInitPrice(Element element) {
        List<Element> elementList= element.getElementsByAttributeValueStarting("class", "priceBlockStrikePriceString");
        if (elementList.isEmpty()) {
            return 0;
        }
        return Integer.valueOf(elementList.get(0).text().replaceAll("\\D", ""));
    }

    private static int extractPrice(Element element) {
        String priceRow = element.getElementById("priceblock_ourprice").text();
        return Integer.valueOf(priceRow.replaceAll("\\D", ""));
    }

    private static String extractCode(String url) {
        return StringUtils.substringAfterLast(url, "/");
    }

    private static String extractName(Element element) {
        return element.getElementById("productTitle").text();
    }

}
