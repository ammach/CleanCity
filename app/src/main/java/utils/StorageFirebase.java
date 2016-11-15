package utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ammach on 11/15/2016.
 */
public class StorageFirebase {

    String storageRefUrl;
    Context context;
    StorageReference storageReference;

    public StorageFirebase(String storageRef, Context context) {
        this.storageRefUrl = storageRef;
        this.context = context;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl(storageRef);
    }

    public void postFile(byte[] data, String pathStorage, final InterfaceFirebase interfaceFirebase) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("start uploading");
        progressDialog.show();
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("image/jpg")
                .build();

        StorageReference destinationRef= storageReference.child(pathStorage);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date=new Date();
        UploadTask uploadTask = destinationRef.child(simpleDateFormat.format(date)).putBytes(data, metadata);

        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Upload is " + progress + "% done");
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(context, "upload paused", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                progressDialog.dismiss();
                Toast.makeText(context, "upload error", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Handle successful uploads on complete

                Uri downloadUrl = taskSnapshot.getMetadata().getDownloadUrl();
                progressDialog.setMessage("upload done");
                Toast.makeText(context, "" + downloadUrl.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                interfaceFirebase.writeToDB(downloadUrl.toString());
            }
        });
    }
}
