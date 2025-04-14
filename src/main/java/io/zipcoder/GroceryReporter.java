package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.beans.NameGenerator.capitalize;

public class GroceryReporter {
    private final String originalFileText;
    private List<Item> itemList;

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile("RawInput.JerkSON");

        if(this.originalFileText==null){
            throw new IllegalArgumentException("Unable to read file: RawInput.JerkSON");
        }
        parseItems();
    }

    private void parseItems(){
        ItemParser itemParser=new ItemParser();
        try {
            itemList = itemParser.parseItemList(this.originalFileText);
        } catch (ItemParseException e) {
            e.printStackTrace();
            itemList = new ArrayList<>();
        }
        }
    public List<Item> getItemList(){
        return itemList;
    }

    @Override
    public String toString() {
        //creates map for item
        Map<String, Integer> nameCounts=new HashMap<>();
        //creates a nested array to store count of multiple of same item price
        Map<String, Map<String,Integer>> priceCounts= new HashMap<>();
        int errorCount=0;

        for(Item item :itemList){
            if(item==null||item.getName()==null||item.getPrice()==null) {
                errorCount=0;
            }
            //capitalize comes from the JSL
            String name=capitalize(item.getName());
            //turns the double to a string
            String price= String.valueOf(item.getPrice());

        }
        return originalFileText;
    }
}
