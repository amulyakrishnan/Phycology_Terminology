package com.example.phycology1;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

 class DatabaseRepo {
    private TaskDao mTaskDao;
    private LiveData<List<Task> > mAlltermi;

    DatabaseRepo(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAlltermi = mTaskDao.getAll();
    }

    LiveData<List<Task>> getAll() {
        return mAlltermi;
    }
    void insert (Task task) {
        new insertAsyncTask(mTaskDao).execute(task);
    }
    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao mAsyncTaskDao;

        insertAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

