package com.example.phycology1;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Database;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private TermiViewmodel termiViewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        RecyclerView recyclerView = findViewById( R.id.rec);
        final TermiAdapter adapter = new TermiAdapter( this );
        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        termiViewmodel = ViewModelProviders.of(this).get(TermiViewmodel.class);

        termiViewmodel.getAll().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {

                adapter.setTasks(tasks);
            }
        });


        FloatingActionButton fab = findViewById( R.id.floating_button_add );
        fab.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, NewTermiActivity.class );
                startActivityForResult( i, NEW_WORD_ACTIVITY_REQUEST_CODE );
            }
        } );
    }
        public void onActivityResult ( int requestCode, int resultCode,Intent data){
            super.onActivityResult( requestCode, resultCode, data);

            if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
                Task task = new Task(data.getStringExtra( NewTermiActivity.EXTRA_REPLY));
                termiViewmodel.insert( task );
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "empty_not_saved",
                        Toast.LENGTH_LONG ).show();
            }
        }
    }






