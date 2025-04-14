package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//public class GroceryReporter {
//    private final String originalFileText;
//    private List<Item> itemList;
//
//    public GroceryReporter(String jerksonFileName) {
//        this.originalFileText = FileReader.readFile("RawInput.JerkSON");
//
//        if(this.originalFileText==null){
//            throw new IllegalArgumentException("Unable to read file: RawInput.JerkSON");
//        }
//        parseItems();
//    }

//    private void parseItems(){
//        ItemParser itemParser=new ItemParser();
//        try {
//            itemList = itemParser.parseItemList(this.originalFileText);
//        } catch (ItemParseException e) {
//            e.printStackTrace();
//            itemList = new ArrayList<>();
//        }
//        }
//    public List<Item> getItemList(){
//        return itemList;
//    }

//    @Override
//    public String toString() {
//        //creates map for item
//        Map<String, Integer> nameCounts = new HashMap<>();
//        //creates a nested array to store count of multiple of same item price
//        Map<String, Map<String, Integer>> priceCounts = new HashMap<>();
//        int errorCount = 0;
//
//        for (Item item : itemList) {
//            if (item == null || item.getName() == null || item.getPrice() == null) {
//                errorCount = 0;
//            }
//            //capitalize comes from the JSL
//            String name = item.getName().toUpperCase();
//            //turns the double to a string
//            String price = String.valueOf(item.getPrice());
//            //keeps track of how many times a given name appears
//            nameCounts.put(name, nameCounts.getOrDefault(name, 0) + 1);
//            //keeps track of prices of each item
//            priceCounts.putIfAbsent(price, new HashMap<>());
//            Map<String, Integer> priceMap = nameCounts;
//            priceMap.put(price, priceMap.getOrDefault(price, 0) + 1);
//        }
//            StringBuilder outPut = new StringBuilder();
//        for (String name : nameCounts.keySet()) {
//            outPut.append(String.format("name%-9s\t seen: %d times\n", name, nameCounts.get(name)));
//            outPut.append("=============\t =============\n");
//
//            Map<String, Integer> prices = priceCounts.get(name);
//            for (String price : prices.keySet()) {
//                outPut.append(String.format("name%-7s\t seen: %d times\n", price, priceCounts.get(price)));
//                outPut.append("=============\t =============\n");
//                outPut.append("\n");
//                outPut.append(String.format("Errors\t\t\t seen: %d times\n", errorCount));
//            }
//            return outPut.toString();
//        }
//
////    }
//}
