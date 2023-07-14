package com.example.contactbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.contactbook.Model.Contacts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Create_Contact_Activity extends AppCompatActivity {

    AppCompatEditText name,surname,number,email;
    CircleImageView image;
    TextView save;
    ImageView back,more;
    Uri imageuri;
    Bitmap bitmap;
    String imagePath,imageName;
    int id,position;
    String name1,surname1,number1,imgpath;
    myDatabase myDatabase = new myDatabase(Create_Contact_Activity.this);
    ArrayList<Contacts> contactslist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);
        image = findViewById(R.id.image);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);
        more = findViewById(R.id.more);

        id = getIntent().getIntExtra("id",0);
        name1 = getIntent().getStringExtra("name");
        surname1 = getIntent().getStringExtra("surname");
        number1 = getIntent().getStringExtra("number");
        position = getIntent().getIntExtra("position",0);
        //imgpath = getIntent().getStringExtra("imgpath");

        if (getIntent().getExtras() != null)
        {
            name.setText(""+name1);
            surname.setText(""+surname1);
            number.setText(""+number1);
            //image.setImageURI(Uri.parse(imgpath));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create_Contact_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                startActivityForResult(intent,100);
//            }
//        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getExtras() == null)
                {
                    String n1 = name.getText().toString();
                    String n2 = surname.getText().toString();
                    String n3 = number.getText().toString();
                    myDatabase.AddContacts(""+n1,""+n2,""+n3,imagePath);
                } else {
                    String n1 = name.getText().toString();
                    String n2 = surname.getText().toString();
                    String n3 = number.getText().toString();
                    Log.d("AAA", "onClick: n1"+n1);
                    Log.d("AAA", "onClick: n2"+n2);
                    Log.d("AAA", "onClick: n3"+n3);
                    myDatabase.UpdateContact(id,""+n1,""+n2,""+n3);
                }
                Intent intent = new Intent(Create_Contact_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Create_Contact_Activity.this,more);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.delete)
                        {
                            myDatabase.DeleteContact(contactslist.get(position).getId());
                            contactslist.remove(position);
                        }
                        Intent intent = new Intent(Create_Contact_Activity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100 && data != null)
//        {
//            imageuri = data.getData();
//            image.setImageURI(imageuri);
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageuri);
//            }catch (IOException e)
//            {
//                throw new RuntimeException(e);
//            }
//            imagePath = saveToInternalStorage(bitmap);
//            imagePath = imagePath + "/" + imageName;
//        }
//    }
//
//    private String saveToInternalStorage(Bitmap bitmapImage){
//        //ContextWrapper cw = new ContextWrapper(getApplicationContext());
//        // path to /data/data/yourapp/app_data/imageDir
//        File directory = getDir("imageDir", Context.MODE_PRIVATE);
//        // Create imageDir
//        File mypath=new File(directory,imageName);
//
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(mypath);
//            // Use the compress method on the BitMap object to write image to the OutputStream
//            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return directory.getAbsolutePath();
//    }
//
//    private void loadImageFromStorage(String path)
//    {
//        try {
//            File f=new File(path);
//            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            ImageView img=findViewById(R.id.image);
//            img.setImageBitmap(b);
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//    }

}