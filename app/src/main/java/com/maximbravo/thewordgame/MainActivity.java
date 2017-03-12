package com.maximbravo.thewordgame;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends Activity {
    private final int nameRequest = 0;
    private TextView p1;
    private TextView p1Data;
    private EditText p1In;
    private Button p1Check;
    private TextView p2;
    private TextView p2Data;
    private EditText p2In;
    private Button p2Check;
    private TextView p3;
    private TextView p3Data;
    private EditText p3In;
    private Button p3Check;
    private TextView p4;
    private TextView p4Data;
    private EditText p4In;
    private Button p4Check;
    private HashMap<String, ArrayList<String>> game = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1 = (TextView) findViewById(R.id.player_1);
        p1Data = (TextView) findViewById(R.id.p1_data);
        p1In = (EditText) findViewById(R.id.p1_in);
        p1Check = (Button) findViewById(R.id.p1_check);
        p2 = (TextView) findViewById(R.id.player_2);
        p2Data = (TextView) findViewById(R.id.p2_data);
        p2In = (EditText) findViewById(R.id.p2_in);
        p2Check = (Button) findViewById(R.id.p2_check);
        p3 = (TextView) findViewById(R.id.player_3);
        p3Data = (TextView) findViewById(R.id.p3_data);
        p3In = (EditText) findViewById(R.id.p3_in);
        p3Check = (Button) findViewById(R.id.p3_check);
        p4 = (TextView) findViewById(R.id.player_4);
        p4Data = (TextView) findViewById(R.id.p4_data);
        p4In = (EditText) findViewById(R.id.p4_in);
        p4Check = (Button) findViewById(R.id.p4_check);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Pop.class);
                startActivityForResult(i, nameRequest);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == nameRequest){
            if(data.hasExtra("name")) {
                String newName = data.getStringExtra("name");
                if (!game.containsKey(newName)) {
                    game.put(newName, new ArrayList<String>());
                    updateUi();
                }
            }
        }
    }
    public void check(View view){
        String word = "";
       if(view == p1Check){
           word = p1In.getText().toString();
       } else if(view == p2Check){
           word = p2In.getText().toString();
       } else if(view == p3Check){
           word = p3In.getText().toString();
       } else {
           word = p4In.getText().toString();
       }
        isValidWord(word);
    }

    public boolean isValidWord(String word) {
        //Get the text file
        File file = new File();

//Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }
    }

    public void updateUi(){
        if(game.size() == 0) return;

        int current = 0;
        for(String name : game.keySet()){
            current++;
            switch (current){
                case 1:
                    p1.setText(name);
                    p1Data.setText(convertArrayListToString(game.get(name)));
                    break;
                case 2:
                    p2.setText(name);
                    p2Data.setText(convertArrayListToString(game.get(name)));
                    break;
                case 3:
                    p3.setText(name);
                    p3Data.setText(convertArrayListToString(game.get(name)));
                    break;
                case 4:
                    p4.setText(name);
                    p4Data.setText(convertArrayListToString(game.get(name)));
                    break;
            }
        }
    }

    private String convertArrayListToString(ArrayList<String> data) {
        StringBuilder result = new StringBuilder();
        for(String s : data){
            result.append(s + "   " + s.length() + "\n");
        }
        return result.toString();
    }
}
