package com.example.trickapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_TRICK_REQUEST =1;
    private TrickViewModel trickViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddTrick = findViewById(R.id.button_add_trick);
        buttonAddTrick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTrickActivity.class);
                startActivityForResult(intent, ADD_TRICK_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TrickAdapter adapter = new TrickAdapter();
        recyclerView.setAdapter(adapter);

        trickViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TrickViewModel.class);
        trickViewModel.getAllTricks().observe(this, new Observer<List<Trick>>() {
            @Override
            public void onChanged(List<Trick> tricks) {
                adapter.setTricks(tricks);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                trickViewModel.delete(adapter.getTrickAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TrickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Trick trick) {
                Intent intent = new Intent(MainActivity.this, AddTrickActivity.class); // THIS IS WHERE YOU WOULD PUT IN THE NEW ACTIVITY FOR THE NEXT SCREEN
                intent.putExtra(AddTrickActivity.EXTRA_ID, trick.getId());
                intent.putExtra(AddTrickActivity.EXTRA_NAME, trick.getTitle());
                intent.putExtra(AddTrickActivity.EXTRA_LANDING, trick.getStyle());
                intent.putExtra(AddTrickActivity.EXTRA_NOTES, trick.getNotes());
                startActivityForResult(intent, ADD_TRICK_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TRICK_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddTrickActivity.EXTRA_NAME);
            String notes = data.getStringExtra(AddTrickActivity.EXTRA_NOTES);
            String landing = data.getStringExtra(AddTrickActivity.EXTRA_LANDING);

            Trick trick = new Trick(name, notes, landing);
            trickViewModel.insert(trick);

            Toast.makeText(this, "Trick Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Trick Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
}