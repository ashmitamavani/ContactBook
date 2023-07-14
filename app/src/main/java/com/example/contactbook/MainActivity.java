package com.example.contactbook;

import static com.example.contactbook.R.layout.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.contactbook.Model.Contacts;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutCompat create_contact;
    FloatingActionButton dialpad;
    ArrayList<Contacts> contactslist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        create_contact = findViewById(R.id.create_contact);
        dialpad = findViewById(R.id.dialpad);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        showData();

        RecyclerView_Adapter adapter = new RecyclerView_Adapter(MainActivity.this,contactslist);
        recyclerView.setAdapter(adapter);

        dialpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
                dialog.setContentView(R.layout.dialpad);
                dialog.show();
            }
        });

        create_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Create_Contact_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void showData() {
        myDatabase myDatabase = new myDatabase(MainActivity.this);
        Cursor cursor = myDatabase.ShowContacts();

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String number = cursor.getString(3);
            String imguri = cursor.getString(4);
            Contacts contacts = new Contacts(id,name,surname,number,imguri);
            contactslist.add(contacts);
        }
    }
}