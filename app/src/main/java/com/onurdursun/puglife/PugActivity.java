package com.onurdursun.puglife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PugActivity extends AppCompatActivity{

    ImageView pugLargeImageView;
    String[] pugUrls;
    Button backButton;
    Button nextButton;
    int pugPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.large_view_layout);

        pugLargeImageView = findViewById(R.id.pugLargeImageView);
        backButton = findViewById(R.id.backButton);
        nextButton = findViewById(R.id.nextButton);

        Intent i = getIntent();
        pugUrls = i.getStringArrayExtra("pugUrls");
        pugPosition = i.getIntExtra("pugPosition", 0);

        Picasso.with(getApplicationContext()).load(pugUrls[pugPosition]).into(pugLargeImageView);
    }

    public void closeLargeView(View view){
        finish();
    }

    public void nextPug(View view){
        pugPosition++;
        Picasso.with(getApplicationContext()).load(pugUrls[pugPosition]).into(pugLargeImageView);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
