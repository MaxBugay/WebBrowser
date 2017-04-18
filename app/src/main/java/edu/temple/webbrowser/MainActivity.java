package edu.temple.webbrowser;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<FragmentTabs> frags;
    private FragmentManager fragmentManager;

    private EditText editURL;

    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frags = new ArrayList<>();
        fragmentManager = getFragmentManager();
        editURL = (EditText) findViewById(R.id.urlAddress);
        currentTab = -1;

        //Previous Tab
        findViewById(R.id.previousButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTab > 0){
                    currentTab--;
                    editURL.setText(frags.get(currentTab).getURL());
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameWeb, frags.get(currentTab))
                            .commit();
                }

            }
        });

        //Next Tab
        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTab < frags.size() - 1){
                    currentTab++;
                    editURL.setText(frags.get(currentTab).getURL());
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameWeb, frags.get(currentTab))
                            .commit();
                }
            }
        });

        //Go to URL
        findViewById(R.id.goButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frags.add(FragmentTabs.newInstance(editURL.getText().toString()));
                currentTab = frags.size() - 1;
                fragmentManager.beginTransaction()
                        .replace(R.id.frameWeb, frags.get(currentTab))
                        .commit();
            }
        });

        //Intents
        Intent intent = getIntent();
        if (intent.getData() != null) {
            frags.add(FragmentTabs.newInstance(intent.getData().toString()));
            currentTab++;
            editURL.setText(frags.get(currentTab).getURL());
            fragmentManager.beginTransaction()
                    .replace(R.id.frameWeb, frags.get(currentTab))
                    .commit();
        }
    }
}

