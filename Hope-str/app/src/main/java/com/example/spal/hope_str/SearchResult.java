package com.example.spal.hope_str;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity {

    List<UserProfile> profiles = new ArrayList<>();
    final Context context = this;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_list);

        extras = getIntent().getExtras();
        String query = extras.getString("query");

        try {
            profiles = ReadFromAWS.pullProfiles(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /** Now start with ListView stuff ***/
        final ArrayAdapter<UserProfile> adapter
                = new CustomAdapter(this, profiles.toArray(new UserProfile[0]));

        // Initialize ListView
        final ListView lv= (ListView) findViewById(R.id.listView);

        if (lv != null) {

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Get item name (something from values list)

                            /////// THis will go to profile page...
                            Intent intent = new Intent(context, ProfilePage.class);
                            intent.putExtra("profiles", profiles.get(position).toStringArray());
                            startActivity(intent);
                        }
                    }
            );
        }
    }
}
