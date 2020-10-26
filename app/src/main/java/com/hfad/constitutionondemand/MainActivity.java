package com.hfad.constitutionondemand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ViewSwitcher;

public class MainActivity extends Activity {

    private ViewSwitcher switcher;
    // ArrayList<String> listItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean isFirst = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null) {
            isFirst = savedInstanceState.getBoolean("first");
        }
        final Handler opening_handler = new Handler();
        switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        if(!isFirst) {
            opening_handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Delay for a while
                    switcher.showNext();
                }
            }, 5000);
        } else switcher.showNext();
        //Add Adapter to handle selections in ListView
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
              if(Content.contents[position].length > 1){
                  Intent intent = new Intent(MainActivity.this, DirectoryActivity.class);
                  intent.putExtra("selectedDocument", position);
                  startActivity(intent);}
              else{
                  Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                  intent.putExtra("selectedContent", 0);
                  intent.putExtra("directorySelected", position);
                  startActivity(intent);
              }
            }
        };
        //Add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.main_directory_list);
        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("first", true);
    }
}
