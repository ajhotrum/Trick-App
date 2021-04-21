package com.example.trickapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddTrickActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.trickapp.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.example.trickapp.EXTRA_NAME";
    public static final String EXTRA_NOTES =
            "com.example.trickapp.EXTRA_NOTES";
    public static final String EXTRA_LANDING =
            "com.example.trickapp.EXTRA_LANDING";

    private EditText editTextName;
    private EditText editTextNotes;
    private RadioGroup radioLandingGroup;
    private RadioButton landingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trick);

        editTextName = findViewById(R.id.name_section);
        editTextNotes = findViewById(R.id.notes_section);

        radioLandingGroup = (RadioGroup) findViewById(R.id.radio_group_landing);
        int selectId = radioLandingGroup.getCheckedRadioButtonId();
        landingButton = (RadioButton) findViewById(selectId);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close);
        setTitle("Add Note");

    }

    private void saveTrick() {
        String name = editTextName.getText().toString();
        String notes = editTextNotes.getText().toString();
        String landing = landingButton.getText().toString();

        if (name.trim().isEmpty()){
            Toast.makeText(this, "Please insert a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_NOTES, notes);
        data.putExtra(EXTRA_LANDING, landing);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != 1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_trick_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_trick:
                saveTrick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}