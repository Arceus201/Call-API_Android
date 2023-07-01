package com.example.retrofitandroidexample;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitandroidexample.API.ApiService;
import com.example.retrofitandroidexample.Model.Entries;
import com.example.retrofitandroidexample.Model.EntriesModel;
import com.example.retrofitandroidexample.example_model.Favotite;
import com.example.retrofitandroidexample.example_model.Job;
import com.example.retrofitandroidexample.example_model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tv_count,tv_link;
    private Button btCall_APi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_count = findViewById(R.id.tv_count);
        tv_link = findViewById(R.id.tv_link);
        btCall_APi = findViewById(R.id.callApi);

        btCall_APi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickCallAPI();
            }
        });



//        //C1: convert 1
//
//        //khoi tao doi tuong
//        Job job = new Job(1, "Coder");
//        List<Favotite> favotiteList = new ArrayList<>();
//        favotiteList.add(new Favotite(1,"Music" ));
//        favotiteList.add(new Favotite(2,"Read Book"));
//        User user = new User(1,"Hung Nguyen",true, job, favotiteList);
//
//        //convert doi tuong sang  Json
//        Gson gson = new Gson();
//        String strJson = gson.toJson(user);
//
//        Log.e(TAG, "String Json : " + strJson );

    }
    //C2: Convert 2
    private String getStringJson(User user) throws JSONException {
        String result = "";
        JSONObject jsonObjecTotal = new JSONObject();
        jsonObjecTotal.put("id", user.getId());
        jsonObjecTotal.put("name", user.getName());

        JSONObject jsonObjectJob = new JSONObject();
        jsonObjectJob.put("id", user.getJob().getId());
        jsonObjectJob.put("job",user.getJob().getJob());

        jsonObjecTotal.put("job", jsonObjectJob);

        result = jsonObjecTotal.toString();
        return result;
    }


    //link API https://api.publicapis.org/entries
    private void ClickCallAPI() {
        ApiService.apiService.getEntry().enqueue(new Callback<EntriesModel>() {
            @Override
            public void onResponse(Call<EntriesModel> call, Response<EntriesModel> response) {
                Toast.makeText(MainActivity.this, "Call API Sussces", Toast.LENGTH_SHORT).show();
                EntriesModel entriesModel =response.body();

                if(entriesModel!=null){
                    tv_count.setText(String.valueOf(entriesModel.getCount()));

//                    List<Entries> entries = new ArrayList<>();
                    tv_link.setText(entriesModel.getEntries().get(0).getLink());
                }

            }

            @Override
            public void onFailure(Call<EntriesModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}