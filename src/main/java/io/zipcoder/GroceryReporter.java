package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.*;

public class GroceryReporter {
    private final String originalFileText;
    private List<Item> itemList;
    private int errorCount = 0; // keeps track of how many errors we have

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile("RawInput.JerkSON");

        if (this.originalFileText == null) {
            throw new IllegalArgumentException("Unable to read file: RawInput.JerkSON");
        }
        parseItems();
    }

    private void parseItems() {
        ItemParser itemParser = new ItemParser(); // create a new parser
        itemList = new ArrayList<>(); // make a new list to store all items
        int count = 0; // keep track of how many items throw errors

        // split the file into chunks using ## and loop through each piece
        for (String rawItem : originalFileText.split("##")) {
            try {
                // try to turn the chunk into an Item and add it to the list
                itemList.add(itemParser.parseSingleItem(rawItem));
            } catch (ItemParseException e) {
                // if it fails, add 1 to the error count
                count++;
            }
        }

        errorCount = count; // save the total number of errors
    }

    public List<Item> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        Map<String, Integer> nameCounts = new HashMap<>();
        Map<String, Map<String, Integer>> priceCounts = new HashMap<>();

        for (Item item : itemList) {
            if (item == null || item.getName() == null || item.getPrice() == null) {
                continue;
            }

            String name = capitalizeFirstLetter(item.getName());
            String price = String.format("%.2f", item.getPrice());

            nameCounts.put(name, nameCounts.getOrDefault(name, 0) + 1);

            priceCounts.putIfAbsent(name, new HashMap<>());
            Map<String, Integer> priceMap = priceCounts.get(name);
            priceMap.put(price, priceMap.getOrDefault(price, 0) + 1);
        }

        StringBuilder outPut = new StringBuilder();

        List<String> orderedNames = Arrays.asList("Milk", "Bread", "Cookies", "Apples");

        for (String name : orderedNames) {
            if (!nameCounts.containsKey(name)) continue;

            outPut.append(String.format("name: %-9s   seen: %d %s\n", name, nameCounts.get(name), nameCounts.get(name) == 1 ? "time" : "times"));
            outPut.append("=============     =============\n");

            Map<String, Integer> prices = priceCounts.get(name);
            List<String> sortedPrices = new ArrayList<>(prices.keySet());
            sortedPrices.sort((a, b) -> Double.compare(Double.parseDouble(b), Double.parseDouble(a))); // descending

            for (String price : sortedPrices) {
                int count = prices.get(price);
                outPut.append(String.format("Price: %-6s     seen: %d %s\n", price, count, count == 1 ? "time" : "times"));
                outPut.append("-------------     -------------\n");
            }

            outPut.append("\n");
        }

        outPut.append(String.format("Errors            seen: %d %s\n", errorCount, errorCount == 1 ? "time" : "times"));
        return outPut.toString();
    }



    private String capitalizeFirstLetter(String word) {
        if (word == null || word.isEmpty()) return word;

        word = word.toLowerCase()
                .replaceAll("0", "o")
                .replaceAll("1", "i")
                .replaceAll("[^a-z]", ""); // remove anything that's not a letter

        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
