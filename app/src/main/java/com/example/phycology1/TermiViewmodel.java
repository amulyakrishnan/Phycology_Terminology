package com.example.phycology1;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.widget.TextView;

import java.util.List;

public class TermiViewmodel extends AndroidViewModel {
    private DatabaseRepo mRepository;
    private LiveData<List<Task>> mAlltermi;

    public TermiViewmodel( Application application) {
        super(application);

        mRepository = new DatabaseRepo(application);
        mAlltermi = mRepository.getAll();
    }
    public LiveData<List<Task>> getAll() { return mAlltermi; }
    public void insert(Task task) { mRepository.insert(task); }
}
