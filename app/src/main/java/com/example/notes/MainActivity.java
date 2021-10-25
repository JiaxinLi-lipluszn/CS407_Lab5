package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    public static String usernameKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(usernameKey, "").equals("")){
            goToActivity2();
        }else{
            setContentView(R.layout.activity_main);
        }

    }
    public void clickFunction(View view) {
        // Log.i("Info", "Button pressed");
        EditText FirstTextField = (EditText) findViewById(R.id.editTextTextUserName);
        String str = FirstTextField.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply();

        //Toast.makeText(MainActivity.this, FirstTextField.getText().toString(), Toast.LENGTH_LONG).show();

        goToActivity2();
    }

    public void goToActivity2(){
        Intent intent = new Intent(this, display_notes.class);
        //intent.putExtra("message", s);
        startActivity(intent);
    }
}