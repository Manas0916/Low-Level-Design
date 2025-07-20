package VendingMachine;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<String, Product> itemMap = new HashMap<>();
    private final Map<String, Integer> stockMap = new HashMap<>();

    public void addItem(String code, Product item, int quantity){
        itemMap.put(code, item);
        stockMap.put(code, Integer.valueOf(quantity));
    }

    public Product getItem(String code) {
        return itemMap.get(code);
    }

    public boolean isAvailable(String code) {
        return stockMap.getOrDefault(code, 0) > 0;
    }

    public void reduceStock(String code) {
        stockMap.put(code, stockMap.get(code) - 1);
    }
}
