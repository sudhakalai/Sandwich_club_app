package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //declaring UI views
    private TextView alsoKnownAsTextview;
    private TextView originTextview;
    private TextView ingredientsTextview;
    private TextView descriptionTextview;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Initializing UI views
        imageView = findViewById(R.id.image_iv);
        alsoKnownAsTextview = findViewById(R.id.also_known_tv);
        originTextview = findViewById(R.id.origin_tv);
        ingredientsTextview = findViewById(R.id.ingredients_tv);
        descriptionTextview = findViewById(R.id.description_tv);

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


        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        //Populating the image
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageView);

        //Populating the list of also known as names
        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        String alsoKnownAs = "";
        if(!alsoKnownAsList.isEmpty()){
            alsoKnownAs = alsoKnownAsList.toString();
            alsoKnownAs = alsoKnownAs.substring(1, alsoKnownAs.length() -1);
            alsoKnownAsTextview.setText(alsoKnownAs);
        }else {
            alsoKnownAsTextview.setText(R.string.no_details_available);
        }

        //Populating the list of ingredients
        List<String> ingredientsList = sandwich.getIngredients();
        String ingredients = "";
        if(!ingredientsList.isEmpty()){
           ingredients = ingredientsList.toString();
           ingredients = ingredients.substring(1, ingredients.length() -1);
            ingredientsTextview.setText(ingredients);
        }else {
            ingredientsTextview.setText(R.string.no_details_available);
        }

        //Populating the place of origin
        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        if(!placeOfOrigin.isEmpty()){
            originTextview.setText(placeOfOrigin);
        }else{
            originTextview.setText(R.string.no_details_available);
        }

       //Populating the description
       descriptionTextview.setText(sandwich.getDescription());

    }
}
