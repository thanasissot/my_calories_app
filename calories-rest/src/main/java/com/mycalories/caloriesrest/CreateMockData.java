package com.mycalories.caloriesrest;

import com.mycalories.repo.services.FoodService;
import com.mycalories.repo.services.ItemService;
import com.mycalories.repo.services.MarketplaceService;
import com.mycalories.repo.services.MealService;
import com.mycalories.model2.food.Food;
import com.mycalories.model2.item.Item;
import com.mycalories.model2.marketplace.Marketplace;
import com.mycalories.model2.meal.Meal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.mycalories.common.Constants.AB_BASILOPOULOS;

@Component
@RequiredArgsConstructor
@Log4j2
public class CreateMockData {
    private final FoodService foodService;
    private final MealService mealService;
    private final MarketplaceService marketplaceService;
    private final ItemService itemService;


    public List<Food> createFoods() {
        Random random = new Random();
        List<Food> foodList = new ArrayList<>();
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(1);
        formatter.setMinimumFractionDigits(1);
        formatter.setRoundingMode(RoundingMode.HALF_UP);

        for (int i = 1; i < 6; i++) {
            String name  = String.join("", "food", String.valueOf(i));
            Food food;
            if ((food = foodService.findByName(name)) == null) {
                food = new Food(name, random.nextInt(500) + 1,
                        Float.parseFloat(formatter.format(random.nextFloat(100)).replace(",", ".")),
                        Float.parseFloat(formatter.format(random.nextFloat(100)).replace(",", ".")),
                        Float.parseFloat(formatter.format(random.nextFloat(100)).replace(",", ".")));
                food = foodService.createFood(food);
            }
            foodList.add(food);

        }

        return foodList;
    }

    public void createTotals(List<Food> foodList) {
        Random random = new Random();
        LocalDateTime ldt = LocalDateTime.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
        if (!mealService.getAllTotalsByDate(date).isEmpty())
            return;

        for (Food food : foodList) {
            int rand = random.nextInt(500);
            Meal total = new Meal(null, date, rand, food.getName(), (food.getCalories() * rand) * 0.01);
            total = mealService.createTotal(total);
        }
    }

    public void createMarketplacesAndSomeItems() {
        if (this.marketplaceService.getAllMarketplaces().isEmpty()) {
            List<String> listOfMarkets = List.of(AB_BASILOPOULOS, "MASOUTIS", "SKLAVENITIS");
            listOfMarkets.forEach(market -> marketplaceService.createMarketplace(new Marketplace(market)));

            // createItems
            Marketplace marketplace = this.marketplaceService.getMarketplaceByName(listOfMarkets.get(0));
            Item item = new Item();
            item.setName("TOTAL YOGURT 5% 1kg");
            item.setMarketplace(marketplace);
            item.setUrl("https://api.ab.gr/?operationName=ProductDetails&variables=%7B%22productCode%22%3A%227078618%22%2C%22lang%22%3A%22gr%22%7D&extensions=%7B%22persistedQuery%22%3A%7B%22version%22%3A1%2C%22sha256Hash%22%3A%220b316057a08467ce64715e01a00dd19fe091aa1a642926b6438810610ecbbcb9%22%7D%7D");
            item = itemService.createItem(item);

        }
    }



    @PostConstruct
    public void run() {
        List<Food> foodlist = createFoods();
        createTotals(foodlist);
        createMarketplacesAndSomeItems();
    }
}
