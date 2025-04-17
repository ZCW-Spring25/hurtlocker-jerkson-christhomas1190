package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {

//makes string a list of items
    private int errorCount;
    public List<Item> parseItemList(String valueToParse) {

        ArrayList<Item> items = new ArrayList<>();
            String[] splitItems = valueToParse.split("##");

            for (String singleItem : splitItems) {
                if (singleItem.isEmpty()) {
                    continue;
                }
                try {
                    // Attempt to parse the item and add it to the list
                    Item item = parseSingleItem(singleItem);
                    items.add(item); // Item is successfully parsed
                } catch (ItemParseException e) {
                    // Increment the error count when an exception occurs
                    this.errorCount++;
                }
            }

            // Return the list of successfully parsed items
            return items;
        }
    //checks single items in string converts to Item object
    public Item parseSingleItem(String singleItem) throws ItemParseException {
        //finds key value pairs ignoring case

        singleItem=singleItem.replaceAll("##","").trim();
        Pattern pattern = Pattern.compile("(?i)([a-z]+)[@:;^*%!]([^@:;^*%!]+)");
        Matcher matcher = pattern.matcher(singleItem);
        //stores pairs
        Map<String, String> fields= new HashMap<>();
        //checks matches found
        while(matcher.find()){
            String key = matcher.group(1).toLowerCase();//key
            String value= matcher.group(2);//value
            fields.put(key,value);
        }

        if(!fields.containsKey("name")|| !fields.containsKey("price")||
                !fields.containsKey("type")||!fields.containsKey("expiration")){
            throw new ItemParseException();
        }
        //takes field from the map
        String name=fields.get("name").toLowerCase();
        Double price=Double.parseDouble(fields.get("price"));
        String type= fields.get("type").toLowerCase();
        String expiration=fields.get("expiration");

        //created and returns Item
        return new Item(name,price,type,expiration);
    }
}
