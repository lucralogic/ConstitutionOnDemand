package com.hfad.constitutionondemand;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DirectoryActivity extends Activity {
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        //Create the ListView of the selected subdirectory
        Intent intent = getIntent();
        position = intent.getIntExtra("selectedDocument", 0);
        ArrayAdapter<Content> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Content.contents[position]);
        ListView listView = findViewById(R.id.sub_directory_list);
        listView.setAdapter(adapter);
        //Set the selected subdirectory to the TextView
        TextView subDirSelected = findViewById(R.id.subDirectory);
        Resources res = getResources();
        String[] options = res.getStringArray(R.array.options);
        subDirSelected.setText(options[position]);
        //Add Adapter to handle selections in ListView
        AdapterView.OnItemClickListener dirClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int dirPosition, long id) {
                Intent dirIntent = new Intent(DirectoryActivity.this, ContentActivity.class);
                dirIntent.putExtra("selectedContent", dirPosition);
                dirIntent.putExtra("directorySelected", position);
                startActivity(dirIntent);
            }
        };
        //Add the listener to the list view
        listView.setOnItemClickListener(dirClickListener);
    }
}
