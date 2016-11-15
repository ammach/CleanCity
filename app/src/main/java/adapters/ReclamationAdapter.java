package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ammach.cleancity.DetailActivity;
import com.ammach.cleancity.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import bean.Reclamation;

/**
 * Created by ammach on 11/12/2016.
 */
public class ReclamationAdapter extends BaseAdapter {

    List<Reclamation> reclamations;
    Context context;

    public ReclamationAdapter(List<Reclamation> reclamations, Context context) {
        this.reclamations = reclamations;
        this.context = context;
    }

    @Override
    public int getCount() {
        return reclamations.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.reclamation_item,null);

        TextView nomUser= (TextView) convertView.findViewById(R.id.nomRec);
        TextView dateRec= (TextView) convertView.findViewById(R.id.dateRec);
        ImageView imgRec= (ImageView) convertView.findViewById(R.id.imgRec);
        TextView commenterRec= (TextView) convertView.findViewById(R.id.commenterRec);
        TextView nbreParticipes= (TextView) convertView.findViewById(R.id.nbreParticipes);

        commenterRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });

        Reclamation reclamation=reclamations.get(position);

        //get user for reclamation
        nomUser.setText(reclamation.getNomUser());
        dateRec.setText(reclamation.getDateRec());
//        reclamation.getImgRec()
        Picasso.with(context).load(reclamation.getImgRec()).into(imgRec);
        nbreParticipes.setText(""+100.0 * new Random().nextInt()+" Participants");

        return convertView;
    }
}
