package com.example.phycology1;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
public class TermiAdapter extends RecyclerView.Adapter<TermiAdapter.termViewHolder> {

    class termViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private termViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Task> mtermi = Collections.emptyList(); // Cached copy of words

    TermiAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public termViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recylcertermi, parent, false);
        return new termViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(termViewHolder holder, int position) {
        Task current = mtermi.get(position);
        holder.wordItemView.setText(current.getTask());
    }

    void setTasks(List<Task> terms) {
        mtermi = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mtermi.size();
    }
}
