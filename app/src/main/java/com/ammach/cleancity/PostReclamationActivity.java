package com.ammach.cleancity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.Reclamation;
import utils.DatabaseFirebase;
import utils.InterfaceFirebase;
import utils.StorageFirebase;

public class PostReclamationActivity extends AppCompatActivity {



    EditText editNomUser;
    EditText editDesc;
    ImageView imgRec;
    ProgressDialog progressDialog;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_reclamation);

        editNomUser= (EditText) findViewById(R.id.editUser);
        editDesc = (EditText) findViewById(R.id.editDesc);
        imgRec = (ImageView) findViewById(R.id.imgRec);
    }

    public void drag(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                imgRec.setImageURI(data.getData());
            }
        }
    }

    public void post(View view) {
        imgRec.setDrawingCacheEnabled(true);
        imgRec.buildDrawingCache();
        Bitmap bitmap = imgRec.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        StorageFirebase storageFirebase=new StorageFirebase("gs://clean-city-fe2fa.appspot.com",this);
        storageFirebase.postFile(data, "reclamations-pictures", new InterfaceFirebase() {
            @Override
            public void writeToDB(String uploadUrl) {
                Toast.makeText(PostReclamationActivity.this, "start insert", Toast.LENGTH_SHORT).show();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                Date date=new Date();
                Reclamation reclamation=new Reclamation(simpleDateFormat.format(date),editDesc.getText().toString(),uploadUrl,editNomUser.getText().toString());
                DatabaseFirebase databaseFirebase=new DatabaseFirebase();
                databaseFirebase.writeToPath(reclamation,"reclamations");
                Toast.makeText(PostReclamationActivity.this, "end insert", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void readFromDB(DataSnapshot dataSnapshot) {

            }
        });

    }
}
