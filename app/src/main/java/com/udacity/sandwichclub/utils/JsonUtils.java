package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    //Method to parse the JSON

    public static Sandwich parseSandwichJson(String json) {
        try {
            //Converting the Json string into a JsonObject
            JSONObject sandwichJsonObject = new JSONObject(json);

            //Parsing the JSON
            //Getting the 'Name'
            JSONObject nameOfSandwich = sandwichJsonObject.getJSONObject("name");
            String mainName = nameOfSandwich.getString("mainName");

            //Getting the 'Also Known as'
            JSONArray alsoKnownAsJsonArray = nameOfSandwich.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnownAsArray = new ArrayList<>();
            for(int i =0; i<alsoKnownAsJsonArray.length(); i++){
                alsoKnownAsArray.add(alsoKnownAsJsonArray.getString(i));
            }

            //Getting the 'Place of origin'
            String placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");

            //Getting the 'Descripition'
            String description = sandwichJsonObject.getString("description");

            //Getting the image URL
            String imageUrl = sandwichJsonObject.getString("image");

            //Getting the 'Ingredients'
            JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray("ingredients");
            ArrayList<String> ingredientsArray = new ArrayList<>();
            for(int j=0; j< ingredientsJsonArray.length(); j++){
                ingredientsArray.add(ingredientsJsonArray.getString(j));
            }

            return new Sandwich(mainName, alsoKnownAsArray, placeOfOrigin, description,imageUrl, ingredientsArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
