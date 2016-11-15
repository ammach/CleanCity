package bean;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ammach on 11/12/2016.
 */
@IgnoreExtraProperties
public class Reclamation {

    public String dateRec;
    public String descRec;
    public String imgRec;
    public String nomUser;

    public String getDateRec() {
        return dateRec;
    }

    public void setDateRec(String dateRec) {
        this.dateRec = dateRec;
    }

    public String getDescRec() {
        return descRec;
    }

    public void setDescRec(String descRec) {
        this.descRec = descRec;
    }

    public String getImgRec() {
        return imgRec;
    }

    public void setImgRec(String imgRec) {
        this.imgRec = imgRec;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public Reclamation() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Reclamation(String dateRec, String descRec, String imgRec, String nomUser) {
        this.dateRec = dateRec;
        this.descRec = descRec;
        this.imgRec = imgRec;
        this.nomUser = nomUser;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("dateRec", dateRec);
        result.put("descRec", descRec);
        result.put("imgRec", imgRec);
        result.put("nomUser", nomUser);

        return result;
    }

}
