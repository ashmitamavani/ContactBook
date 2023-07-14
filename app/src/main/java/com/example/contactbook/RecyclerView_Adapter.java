package com.example.contactbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactbook.Model.Contacts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ContactViewHolder> {

    MainActivity mainActivity;
    ArrayList<Contacts> contactslist;
    public RecyclerView_Adapter(MainActivity mainActivity, ArrayList<Contacts> contactslist) {
        this.mainActivity = mainActivity;
        this.contactslist = contactslist;
    }

    @NonNull
    @Override
    public RecyclerView_Adapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.recyclerview_item,parent,false);
        ContactViewHolder holder = new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_Adapter.ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.recycler_name.setText(""+contactslist.get(position).getName());
        //loadImageFromStorage(contactslist.get(position).getImgPath(),holder.recycler_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, Create_Contact_Activity.class);
                intent.putExtra("id",contactslist.get(position).getId());
                intent.putExtra("name",contactslist.get(position).getName());
                intent.putExtra("surname",contactslist.get(position).getSurname());
                intent.putExtra("number",contactslist.get(position).getNumber());
                intent.putExtra("position",position);
                intent.putExtra("imgpath",contactslist.get(position).getImgPath());
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactslist.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        CircleImageView recycler_image;
        TextView recycler_name;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            recycler_image = itemView.findViewById(R.id.recycler_image);
            recycler_name = itemView.findViewById(R.id.recycler_name);
        }
    }
    private void loadImageFromStorage(String path, ImageView recycler_image)
    {
        try {
            File f=new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            recycler_image.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
