package com.hfad.constitutionondemand;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import static android.text.Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE;
import static com.hfad.constitutionondemand.Content.contents;

public class ContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        //Create the TextView of the selected content
        Intent intent = getIntent();
        int position = intent.getIntExtra("selectedContent", 0);
        int directorySelected = intent.getIntExtra("directorySelected",0);
        TextView textContent = findViewById(R.id.content);
        textContent.setMovementMethod(new ScrollingMovementMethod());
        Content lclContent = contents[directorySelected][position];
        textContent.setText(Html.fromHtml(lclContent.getText(),TO_HTML_PARAGRAPH_LINES_CONSECUTIVE));

        //Set the selections to the title and description
        TextView selectedTitle = findViewById(R.id.contentTitle);
        Resources res = getResources();
        String[] options = res.getStringArray(R.array.options);
        selectedTitle.setText(options[directorySelected]);
        TextView selectedContent = findViewById(R.id.displayedContent);
        selectedContent.setText(contents[directorySelected][position].getDirectory());
    }
}
