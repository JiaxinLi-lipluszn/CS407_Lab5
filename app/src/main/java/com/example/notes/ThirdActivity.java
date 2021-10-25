package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {
    int noteid = -1;
    TextView noteText;
    //DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        noteText = (TextView)findViewById(R.id.giantEditText);
        // Get EditText View

        // Get the intent
        Intent intent = getIntent();

        // Get the note id
        noteid = intent.getIntExtra("noteid",-1);

        if(noteid != -1){
            Note note = display_notes.notes.get(noteid);
            String noteContent = note.getContent();
            noteText.setText(noteContent);
        }
    }

    public void saveMethod(View view){
        // Get the content

        String content = noteText.getText().toString();

        // Fetch the database
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // Add content to the sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("content", content).apply();
        String username= sharedPreferences.getString("username", "default");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1){
            title = "NOTE_" + (display_notes.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        }else{
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        goToActivity2();
    }

    public void goToActivity2(){
        Intent intent = new Intent(this, display_notes.class);
        //intent.putExtra("message", s);
        startActivity(intent);
    }
}