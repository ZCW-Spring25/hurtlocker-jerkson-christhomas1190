package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.List;

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
        itemList=itemParser.parseItemList(this.originalFileText);
    }
    public List<Item> getItemList(){
        return itemList;
    }
    @Override
    public String toString() {
        return originalFileText;
    }
}
