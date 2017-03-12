package com.maximbravo.thewordgame;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
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

import static android.R.attr.value;
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

        p2 = (TextView) findViewById(R.id.player_2);
        p2Data = (TextView) findViewById(R.id.p2_data);
        p2In = (EditText) findViewById(R.id.p2_in);

        p3 = (TextView) findViewById(R.id.player_3);
        p3Data = (TextView) findViewById(R.id.p3_data);
        p3In = (EditText) findViewById(R.id.p3_in);

        p4 = (TextView) findViewById(R.id.player_4);
        p4Data = (TextView) findViewById(R.id.p4_data);
        p4In = (EditText) findViewById(R.id.p4_in);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        game.put("Maxim", new ArrayList<String>());
        game.put("Katia", new ArrayList<String>());
        game.put("Alex", new ArrayList<String>());
        game.put("Wendy", new ArrayList<String>());
        updateUi();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == nameRequest){
            if(resultCode == RESULT_OK) {
                if (data.hasExtra("name")) {
                    String newName = data.getStringExtra("name");
                    if (!game.containsKey(newName)) {
                        game.put(newName, new ArrayList<String>());
                        updateUi();
                    }
                }
            }
        }
    }
    public void check(){
        String word = "";
       if(p1In.length() != 0){
           word = p1In.getText().toString();
           if(isValidWord(word)){
               String currentP = p1.getText().toString();
               ArrayList<String> value = game.get(currentP);
               value.add(word);
               game.put(currentP, value);
               p1In.setText("");
               p2In.requestFocus();
           }
       } else if(p2In.length() != 0){
           word = p2In.getText().toString();
           if(isValidWord(word)){
               String currentP = p2.getText().toString();
               ArrayList<String> value = game.get(currentP);
               value.add(word);
               game.put(currentP, value);
               p2In.setText("");
               p3In.requestFocus();
           }
       } else if(p3In.length() != 0){
           word = p3In.getText().toString();
           if(isValidWord(word)){
               String currentP = p3.getText().toString();
               ArrayList<String> value = game.get(currentP);
               value.add(word);
               game.put(currentP, value);
               p3In.setText("");
               p4In.requestFocus();
           }
       } else if (p4In.length() != 0){
           word = p4In.getText().toString();
           if(isValidWord(word)){
               String currentP = p4.getText().toString();
               ArrayList<String> value = game.get(currentP);
               value.add(word);
               game.put(currentP, value);
               p4In.setText("");
               p1In.requestFocus();
           }
       }
        updateUi();
    }

    public boolean isValidWord(String word) {
        if(word.length() == 0)return false;
        return true;
//        File sdcard = Environment.getExternalStorageDirectory();
//        //Get the text file
//        File file = new File(sdcard, "words.txt");
//
////Read text from file
//        StringBuilder text = new StringBuilder();
//
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                if(line.equals(word)){
//                    return true;
//                }
//            }
//            br.close();
//        }
//        catch (IOException e) {
//            //You'll need to add proper error handling here
//        }
//        return false;
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
                    TextView p1Total = (TextView) findViewById(R.id.p1_total);
                    p1Total.setText("Total: " + getSum(game.get(name)));
                    break;
                case 2:
                    p2.setText(name);
                    p2Data.setText(convertArrayListToString(game.get(name)));
                    TextView p2Total = (TextView) findViewById(R.id.p2_total);
                    p2Total.setText("Total: " + getSum(game.get(name)));
                    break;
                case 3:
                    p3.setText(name);
                    p3Data.setText(convertArrayListToString(game.get(name)));
                    TextView p3Total = (TextView) findViewById(R.id.p3_total);
                    p3Total.setText("Total: " + getSum(game.get(name)));
                    break;
                case 4:
                    p4.setText(name);
                    p4Data.setText(convertArrayListToString(game.get(name)));
                    TextView p4Total = (TextView) findViewById(R.id.p4_total);
                    p4Total.setText("Total: " + getSum(game.get(name)));
                    break;
            }
        }
    }

    public int getSum(ArrayList<String> words) {
        int sum = 0;
        for(String word : words){
            if(!word.equals("-1")) {
                sum += word.length();
            }
        }
        return sum;
    }

    private String convertArrayListToString(ArrayList<String> data) {
        StringBuilder result = new StringBuilder();
        for(String s : data){
            if(!s.equals("-1")) {
                result.append(s + "   " + s.length() + "\n");
            } else {
                result.append("PASS   0\n");
            }
        }
        return result.toString();
    }
}
