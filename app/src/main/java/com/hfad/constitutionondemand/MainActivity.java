/*
    Constitution on Demand is an Android program that provides a handy copy of the Constitution and
    other documents
    Copyright (C) 2020  Lucralogic, LLC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
        switcher =  findViewById(R.id.viewSwitcher);
        if(!isFirst) {
            opening_handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Delay for a while
                    switcher.showNext();
                }
            }, 1500);
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
        ListView listView = findViewById(R.id.main_directory_list);
        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("first", true);
    }
}
