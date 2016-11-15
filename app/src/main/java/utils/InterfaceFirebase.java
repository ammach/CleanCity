package utils;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by ammach on 11/15/2016.
 */
public interface InterfaceFirebase {

    public void writeToDB(String uploadUrl);

    public void readFromDB(DataSnapshot dataSnapshot);
}
