package com.example.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabDots = findViewById(R.id.tabDots);
        ArrayList<Card> cards = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/") // use 10.0.2.2 to refer to localhost on the host machine
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AndroidApi cardApi = retrofit.create(AndroidApi.class);

        Call<List<Card>> call = cardApi.getCards();
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (!response.isSuccessful()) {
                    Log.e("API Error", "Error Code: " + response.code());
                    return;
                }
                List<Card> cardsx = response.body();
                cards.addAll(cardsx);
                Log.d("UWUX", "x");
                for (Card card : cards) {
                    Log.d("API", "Card: " + card.number.toString());
                }
                Log.d("UWUX", "x");

                // Set the ViewPager here
                CardAdapter adapter = new CardAdapter(cards);
                viewPager.setAdapter(adapter);

                new TabLayoutMediator(tabDots, viewPager, (tab, position) -> {
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setImageResource(R.drawable.tab_selector);
                    tab.setCustomView(imageView);
                }).attach();
                tabDots.setSelectedTabIndicatorColor(Color.BLACK);
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.e("API Error", "Request Failed: " + t.getMessage());
            }
        });

        Call<User> usercall = cardApi.getUserByEmail("azurexsx@gmail.com");

        usercall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Log.e("API Error", "Error Code: " + response.code());
                    return;
                }

                User user = response.body();
                Log.d("API END", user.user_name);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API Error", "Request Failed: " + t.getMessage());
            }
        });

        if(getSupportActionBar() !=  null){
            getSupportActionBar().hide();
        }
    }

    public static void updateVievws(){

    }
}