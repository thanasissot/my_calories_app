package com.mycalories.market.prices;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycalories.model2.item.Item;
import com.mycalories.model2.marketplace.Marketplace;
import com.mycalories.repo.services.ItemService;
import com.mycalories.repo.services.MarketplaceService;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mycalories.common.Constants.AB_BASILOPOULOS;

@Service
@RequiredArgsConstructor
public class GetPricesService {

    private final MarketplaceService marketplaceService;
    private final ItemService itemService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @PostConstruct
    public void callPostConstruct() throws Exception {
        pricesUpdate(getItemsMappedWithMarketplace());
    }

    public void pricesUpdate(Map<Marketplace, List<Item>> map) throws Exception {
        for (Marketplace marketplace : map.keySet()) {
            for (Item item : map.get(marketplace)) {
                InputStream is = getInputStream(item.getUrl());
                String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                JsonNode jsonNode = objectMapper.readTree(json);
                switch (marketplace.getName()) {
                    case AB_BASILOPOULOS :
                       ABItem abItem = getABItem(jsonNode);
                        item.setFormattedValue(abItem.getFormattedValue());
                        item.setValue(abItem.getValue());
                }
            }

            // persist new Prices
            this.itemService.createItems(map.get(marketplace));
        }
    }

    private Map<Marketplace, List<Item>> getItemsMappedWithMarketplace() {
        List<Marketplace> marketplaces = this.marketplaceService.getAllMarketplaces();
        Map<Marketplace, List<Item>> map = new HashMap<>();
        marketplaces.forEach(marketplace -> {
            List<Item> items = this.itemService.getItemsByMarketplace(marketplace);
            if (items != null) {
                map.put(marketplace, items);
            }
        });

        return map;
    }

    private static InputStream getInputStream(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        return con.getInputStream();
    }



    private static ABItem getABItem(JsonNode jsonNode) {
        String formattedValue = jsonNode.get("data").get("productDetails").get("price").get("formattedValue").textValue();
        double value = jsonNode.get("data").get("productDetails").get("price").get("value").asDouble();
        return ABItem.builder().formattedValue(formattedValue).value(value).build();
    }

    @Builder
    @Getter
    @Setter
    static class ABItem {
        private String formattedValue;
        private double value;
    }
}
