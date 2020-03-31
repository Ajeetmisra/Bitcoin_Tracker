package com.example.bitcointracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private final String BASE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
    TextView mPriceTextView;
    TextView mBaseCurrency;
    ImageView mImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPriceTextView = (TextView) findViewById(R.id.priceText);

//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(BASE_URL, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // called when response HTTP status is "200 OK"
//                //Log.d("bitcoin", "JSON: " + response.toString());
//                // JSONObject jsonObject = new JSONObject();
//                try {
//                    String price1 = response.getJSONObject("bpi").getJSONObject("USD").getString("rate");
//                    Log.d("bitcoin", "onSuccess: " + price1);
//                    mPriceTextView.setText(price1);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
//                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                Log.d("bitcoin", "Request fail! Status code: " + statusCode);
//                Log.d("bitcoin", "Fail response: " + response);
//                Log.e("ERROR", e.toString());
//            }
//        });

        //onCreate wala bracket

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("bitcoin", "onItemSelected: " + parent.getItemAtPosition(position));
                String selectedItem = (String) parent.getItemAtPosition(position);
               someNetworking(BASE_URL,selectedItem);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    // somenetworking code

    public void someNetworking(String BASE_URL, final String Item){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                //Log.d("bitcoin", "JSON: " + response.toString());
                // JSONObject jsonObject = new JSONObject();
                try {
                    String price1 = response.getJSONObject("bpi").getJSONObject(Item).getString("rate");
                    Log.d("bitcoin", "onSuccess: " + price1);
                    mPriceTextView.setText(price1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("bitcoin", "Request fail! Status code: " + statusCode);
                Log.d("bitcoin", "Fail response: " + response);
                Log.e("ERROR", e.toString());
            }
        });

    }
}
