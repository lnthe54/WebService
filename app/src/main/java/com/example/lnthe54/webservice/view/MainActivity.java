package com.example.lnthe54.webservice.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lnthe54.webservice.R;
import com.example.lnthe54.webservice.adapter.FoodAdapter;
import com.example.lnthe54.webservice.model.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FoodAdapter.CallBack, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private RecyclerView rvListFood;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> listFood;
    private FloatingActionButton btnAdd;

    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!checkPermissions()) {
            return;
        }

        initViews();
        ReadJSON("http://192.168.1.160/androidwebservice/demogetdatafood.php");
        addEvent();
    }

    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String check : permissions) {
                int status = checkSelfPermission(check);
                if (status == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(permissions, 0);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (checkPermissions()) {
            initViews();
        } else {
            finish();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initViews() {
        rvListFood = findViewById(R.id.list_food);
        rvListFood.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvListFood.setHasFixedSize(true);

        btnAdd = findViewById(R.id.ic_add);

    }

    private void addEvent() {
        btnAdd.setOnClickListener(this);
    }

    private void addEvents(JSONArray response) {

        listFood = new ArrayList<>();

        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject jsonObject = response.getJSONObject(i);
                listFood.add(new Food(jsonObject.getString("name"), jsonObject.getInt("price"),
                        jsonObject.getString("img"), jsonObject.getString("time"), jsonObject.getString("address"), jsonObject.getString("description")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        foodAdapter = new FoodAdapter(this, listFood);
        rvListFood.setAdapter(foodAdapter);
    }

    private void ReadJSON(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        addEvents(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void itemClick(int position) {
        Toast.makeText(MainActivity.this, listFood.get(position).getDescription(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_add: {
                Intent openAddActivity = new Intent(MainActivity.this, AddActivity.class);
                startActivity(openAddActivity);
                break;
            }
        }
    }
}
