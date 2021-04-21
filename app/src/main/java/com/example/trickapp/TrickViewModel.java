package com.example.trickapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TrickViewModel extends AndroidViewModel {
    private TrickRepository repository;
    private LiveData<List<Trick>> allTricks;
    public TrickViewModel(@NonNull Application application) {
        super(application);
        repository = new TrickRepository(application);
        allTricks = repository.getAllTricks();
    }

    public void insert(Trick trick){
        repository.insert(trick);
    }
    public void update(Trick trick){
        repository.update(trick);
    }
    public void delete(Trick trick){
        repository.delete(trick);
    }
    public LiveData<List<Trick>> getAllTricks(){
        return allTricks;
    }

}
