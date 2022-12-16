package com.example.sepiapetsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnItemClockListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "petName";

    RecyclerView recyclerView;


    ArrayList<String> petImages = new ArrayList<>();
    ArrayList<String> petNames = new ArrayList<>();
    ArrayList<String> details = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        try {
            JSONObject object = new JSONObject(loadJasonfromAssets());

            JSONArray petsArray = object.getJSONArray("pets");

            for(int i=0; i<petsArray.length(); i++){
                JSONObject petsDetail = petsArray.getJSONObject(i);

                petImages.add(petsDetail.getString("image_url"));
                petNames.add(petsDetail.getString("title"));
                details.add(petsDetail.getString("content_url"));
            }
        }  catch (JSONException e){
            e.printStackTrace();
        }

        CustomAdapter customAdapter = new CustomAdapter( petImages, petNames, MainActivity.this);
        recyclerView.setAdapter(customAdapter);
        customAdapter.setOnItemClockListener(MainActivity.this);
    }

    private String loadJasonfromAssets() {
        String json = null;

        try{
            InputStream is = getAssets().open("pets_list.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

        return json;
    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, MainActivity2.class);
        String getName = petNames.get(position);
        String getImage = petImages.get(position);
        String getDetail = details.get(position);

        detailIntent.putExtra("Image", getImage);
        detailIntent.putExtra("Name", getName);
        detailIntent.putExtra("Details", getDetail);

        startActivity(detailIntent);


    }


}