package com.example.spal.hope_str;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter< UserProfile > {

    CustomAdapter(Context context, UserProfile[] profiles) {
        super(context, R.layout.custom_row, profiles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.from(getContext()).inflate(R.layout.custom_row, parent, false);

        UserProfile profile = getItem(position);

        // Get TextViews for Header and Subtitle
        TextView rowName = (TextView) view.findViewById(R.id.rowName);
        TextView rowLocation = (TextView) view.findViewById(R.id.rowLocation);
        TextView rowActivity = (TextView) view.findViewById(R.id.rowDiseases);

        // Populate both TextViews
        rowName.setText(profile.getName() + ", " + profile.getAge());
        rowLocation.setText(profile.getLocation().toString());
        rowActivity.setText("Illnesses: " + profile.getmIllnesses());

        return view;
    }
}
