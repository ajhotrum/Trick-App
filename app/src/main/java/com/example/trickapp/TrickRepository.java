package com.example.trickapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TrickRepository {
    private TrickDao trickDao;
    private LiveData<List<Trick>> allTricks;

    public TrickRepository(Application application){
        TrickDatabase database = TrickDatabase.getInstance(application);
        trickDao = database.trickDao();
        allTricks = trickDao.getAllTricks();
    }

    public void insert(Trick trick) {
        new InsertTrickAsyncTask(trickDao).execute(trick);
    }

    public void update(Trick trick){
        new UpdateTrickAsyncTask(trickDao).execute(trick);
    }

    public void delete(Trick trick){
        new DeleteTrickAsyncTask(trickDao).execute(trick);
    }

    public LiveData<List<Trick>> getAllTricks() {
        return allTricks;
    }

    private static class InsertTrickAsyncTask extends AsyncTask<Trick, Void, Void> {
        private TrickDao trickDao;

        private InsertTrickAsyncTask(TrickDao trickDao){
            this.trickDao = trickDao;
        }

        @Override
        protected Void doInBackground(Trick... tricks) {
            trickDao.insert(tricks[0]);
            return null;
        }
    }

    private static class UpdateTrickAsyncTask extends AsyncTask<Trick, Void, Void> {
        private TrickDao trickDao;

        private UpdateTrickAsyncTask(TrickDao trickDao){
            this.trickDao = trickDao;
        }

        @Override
        protected Void doInBackground(Trick... tricks) {
            trickDao.update(tricks[0]);
            return null;
        }
    }

    private static class DeleteTrickAsyncTask extends AsyncTask<Trick, Void, Void> {
        private TrickDao trickDao;

        private DeleteTrickAsyncTask(TrickDao trickDao){
            this.trickDao = trickDao;
        }

        @Override
        protected Void doInBackground(Trick... tricks) {
            trickDao.delete(tricks[0]);
            return null;
        }
    }

}
