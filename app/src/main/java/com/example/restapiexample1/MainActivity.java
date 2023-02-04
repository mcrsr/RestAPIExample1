package com.example.restapiexample1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jayway.jsonpath.JsonPath;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getData(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://reqres.in/api/users";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<String> first_name = JsonPath.read(response, "$.data[*].first_name");
                        List<String> last_name = JsonPath.read(response, "$.data[*].last_name");
                        List<String> avatar = JsonPath.read(response, "$.data[*].avatar");

                        MyListAdapter adapter=new MyListAdapter(MainActivity.this, first_name.toArray(new String[0]), last_name.toArray(new String[0]),avatar.toArray(new String[0]));
                        ListView list = (ListView) findViewById(R.id.list);
                        list.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//
            }
        });

        queue.add(stringRequest);

    }
}