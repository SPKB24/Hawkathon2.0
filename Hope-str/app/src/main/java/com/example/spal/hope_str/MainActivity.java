package com.example.spal.hope_str;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            UserProfile profile = ReadFromAWS.getPersonalProfile();

            TextView greeting = (TextView) findViewById(R.id.greetingMsg);
            greeting.setText("Welcome " + profile.getName().replaceAll(" .*", ""));

        } catch (Exception e) {
            // Dear Program,
            // pls don't come here.
            // thanks,
            // Sohit
            int a = 2 + 2;
        }
    }

    public void searchButtonClick(View view) {
        //Do something
        EditText input = (EditText) findViewById(R.id.search_bar);

        try {
            if (!input.getText().toString().equals("")) {
                Intent intent = new Intent(context, SearchResult.class);
                intent.putExtra("query", input.getText().toString().toLowerCase());
                startActivity(intent);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Hello world");
        }
    }

    public void searchCancer(View view) {
        try {
            Intent intent = new Intent(context, SearchResult.class);
            intent.putExtra("query", "cancer");
            startActivity(intent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Hello world");
        }
    }

    public void searchHIV(View view) {
        try {
            Intent intent = new Intent(context, SearchResult.class);
            intent.putExtra("query", "hiv");
            startActivity(intent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Hello world");
        }
    }

    public void searchParkinsons(View view) {
        try {
            Intent intent = new Intent(context, SearchResult.class);
            intent.putExtra("query", "parkinsons");
            startActivity(intent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Hello world");
        }
    }

    public void searchEbola(View view) {
        try {
            Intent intent = new Intent(context, SearchResult.class);
            intent.putExtra("query", "ebola");
            startActivity(intent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Hello world");
        }
    }
}