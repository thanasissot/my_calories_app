package com.mycalories.market.prices;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {

    public static InputStream getInputStream(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        return con.getInputStream();
    }



    public static void main(String[] args) throws IOException {
        InputStream is = getInputStream("https://api.ab.gr/?operationName=ProductDetails&variables=%7B%22productCode%22%3A%227078618%22%2C%22lang%22%3A%22gr%22%7D&extensions=%7B%22persistedQuery%22%3A%7B%22version%22%3A1%2C%22sha256Hash%22%3A%220b316057a08467ce64715e01a00dd19fe091aa1a642926b6438810610ecbbcb9%22%7D%7D");


        String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        String formattedValue = jsonNode.get("data").get("productDetails").get("price").get("formattedValue").textValue();
        String value = jsonNode.get("data").get("productDetails").get("price").get("value").textValue();
        System.out.println();



    }
}
