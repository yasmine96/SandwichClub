package com.example.yasmine.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.example.yasmine.sandwichclub.model.Sandwich;
import com.example.yasmine.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;

        }


        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        // Find the ImageView with the ID  image_iv name
        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        // Find the TextView with the ID  also_known_tv name
       TextView knownAsView = findViewById(R.id.also_known_tv);
        List<String> list1 = sandwich.getAlsoKnownAs();
        StringBuilder builder1 = new StringBuilder();
        for (String knownAs : list1) {
            builder1.append(knownAs + "\n");
        }

        knownAsView.setText(builder1.toString());


        // Find the TextView with the ID  ingredients_tv name
        TextView ingredientsView = findViewById(R.id.ingredients_tv);
        List<String> list2 = sandwich.getIngredients();
        StringBuilder builder2 = new StringBuilder();
        for (String ingredients : list2) {
            builder2.append(ingredients + "\n");
        }

        ingredientsView.setText(builder2);

        // Find the TextView with the ID  origin_tv name
        TextView originView = findViewById(R.id.origin_tv);
        String origin = sandwich.getPlaceOfOrigin();
        originView.setText(origin);

        // Find the TextView with the ID  description_tv name
        TextView descriptionView = findViewById(R.id.description_tv);
        String description = sandwich.getDescription();
        descriptionView.setText(description);


    }
}
