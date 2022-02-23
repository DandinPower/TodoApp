package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>{
    private ArrayList<ToDo> mTodoData;
    private Context mContext;

    ToDoAdapter(Context context,ArrayList<ToDo> TodoData){
        this.mContext = context;
        this.mTodoData = TodoData;
    }

    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.card_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(ToDoAdapter.ViewHolder holder, int position) {
        ToDo currentTodo = mTodoData.get(position);
        holder.bindTo(currentTodo);
    }

    @Override
    public int getItemCount() {
        return mTodoData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mTitleText;
        private TextView InfoText;
        private TextView DueText;
        private TextView DoText;

        ViewHolder(View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.title);
            InfoText = itemView.findViewById(R.id.info);
            DueText = itemView.findViewById(R.id.due);
            DoText = itemView.findViewById(R.id.dodate);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ToDo currentTodo = mTodoData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("id",currentTodo.getId());
            detailIntent.putExtra("title", currentTodo.getTitle());
            detailIntent.putExtra("info", currentTodo.getInfo());
            detailIntent.putExtra("due", currentTodo.getDue());
            detailIntent.putExtra("do", currentTodo.getDo());
            mContext.startActivity(detailIntent);
        }

        void bindTo(ToDo currentTodo) {
            mTitleText.setText("Title:"+currentTodo.getTitle());
            InfoText.setText("Info:"+currentTodo.getInfo());
            DueText.setText("Due:"+currentTodo.getDue());
            DoText.setText("Dodate:"+currentTodo.getDo());
        }
    }
}
