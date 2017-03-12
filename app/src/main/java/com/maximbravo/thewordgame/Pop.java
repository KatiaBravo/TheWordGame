package com.maximbravo.thewordgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Kids on 3/12/2017.
 */

public class Pop extends Activity {
    private EditText nameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompt);
        nameField = (EditText) findViewById(R.id.input_name);
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.5), (int) (height*0.50));
    }
    public void done(View v){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("name", nameField.getText().toString());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
