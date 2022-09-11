package com.example.registerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExamAdpter extends RecyclerView.Adapter<com.example.registerapp.ExamAdpter.ViewHolder> {

    ArrayList<User> ExamModel= new ArrayList<>();



    public ExamAdpter(ArrayList<User> ExamModel){
        this.ExamModel = ExamModel;

    }
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_items, parent, false);  //inflates the xml file in recyclerview
        return new ViewHolder(view) ;

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.email.setText(ExamModel.get(position).getEmail());
        holder.name.setText(ExamModel.get(position).getName());
        holder.surname.setText(ExamModel.get(position).getSurname());
        holder.national.setText(ExamModel.get(position).getNational());
        holder.phone.setText(ExamModel.get(position).getPhone());
        holder.date.setText(ExamModel.get(position).getDate());


    }

    @Override
    public int getItemCount() {
        return ExamModel.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView email,name, surname,national,date,phone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           email = itemView.findViewById(R.id.username);                               //holds the reference of the materials to show data in recyclerview
            name = itemView.findViewById(R.id.name);
            surname =  itemView.findViewById(R.id.surname);
            national=  itemView.findViewById(R.id.national);
            date =  itemView.findViewById(R.id.date);
           phone =  itemView.findViewById(R.id.phone);

        }
    }
}
