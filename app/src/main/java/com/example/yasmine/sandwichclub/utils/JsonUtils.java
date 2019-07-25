package com.example.yasmine.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;


import com.example.yasmine.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(json)) {
            return null;
        }


        Sandwich sandwiches =  new Sandwich();

        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONObject name = baseJsonResponse.getJSONObject("name");
            String mainName  = name.getString("mainName");


            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            // list of string to store list of alsoKnownAs array;
            List <String> list1 = new ArrayList <String>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                list1.add(alsoKnownAs.getString(i));
            }

            String placeOfOrigin  =baseJsonResponse.getString("placeOfOrigin");
            String description  = baseJsonResponse.getString("description");
            String image  = baseJsonResponse.getString("image");


            JSONArray ingredients = baseJsonResponse.getJSONArray("ingredients");
            // list od string to store list of ingredients array;
            List <String> list2 = new ArrayList <String>();
            for (int i = 0; i < ingredients.length(); i++) {
               list2.add(ingredients.getString(i));
            }

             sandwiches = new Sandwich(mainName ,list1 , placeOfOrigin , description , image , list2);
        }

        catch (JSONException e) {
        // If an error is thrown when executing any of the above statements in the "try" block,
        // catch the exception here, so the app doesn't crash. Print a log message
        // with the message from the exception.
        Log.e("JsonyUtils", "Problem parsing the sandwiches JSON results", e);
    }


    return sandwiches;

    }
}
