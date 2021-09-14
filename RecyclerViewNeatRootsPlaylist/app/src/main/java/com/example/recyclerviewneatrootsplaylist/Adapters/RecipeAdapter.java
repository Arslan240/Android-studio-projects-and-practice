package com.example.recyclerviewneatrootsplaylist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewneatrootsplaylist.Models.RecipeModel;
import com.example.recyclerviewneatrootsplaylist.R;

import java.util.ArrayList;


//This is an adapter class which extends RecyclerView.Adapter<> generic abstract class to attain that functionality.
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.viewHolder>{

    ArrayList<RecipeModel> recipeModelArrayList;
    Context context;

    public RecipeAdapter(ArrayList<RecipeModel> recipeModelArrayList, Context context) {
        this.recipeModelArrayList = recipeModelArrayList;
        this.context = context;
    }


//--------------------------------------------------- METHODS ---------------------------------------------------------

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //this method is used to inflate the layout.

        View view = LayoutInflater.from(context).inflate(R.layout.resourcefile_for_recycler_view_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) { //this method sets the values of our imageview and textView.

        RecipeModel model = recipeModelArrayList.get(position); //this will return the RecipeModel object in the list at the position index

        holder.imageView.setImageResource(model.getPic()); //we set the image in the holder by getting the data from the model that we created earlier.

        holder.textView.setText(model.getText());

    }


    @Override
    public int getItemCount() {  //this method returns total items in list that needs to be shown
        return recipeModelArrayList.size();
    }



    //------------------------------------------------------ VIEW HOLDER INNER CLASS ---------------------------------------------------------------------

    //This is an inner class which will be used to pass to generic class RecyclerView.Adapter<> while extending the outer class. You know what i mean.
    //This is a viewHolder class which holds views that we need to show in recycler view. To attain that functionality It extends RecyclerView.ViewHolder.
    //We pass such view holder to the RecyclerView.Adapter<> class which is a viewholder and it should be edxtending RecyclerView.ViewHolder class.



    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
