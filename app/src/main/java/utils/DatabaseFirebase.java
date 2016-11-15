package utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ammach on 11/15/2016.
 */
public class DatabaseFirebase<T> {

    private DatabaseReference mDatabase;

    public DatabaseFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    public void writeToPath(T t,String path){
        mDatabase.child(path).push().setValue(t);
    }


    public void readFromPath(String path, final InterfaceFirebase interfaceFirebase){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                interfaceFirebase.readFromDB(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ref", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.child(path).addValueEventListener(postListener);
    }
}
