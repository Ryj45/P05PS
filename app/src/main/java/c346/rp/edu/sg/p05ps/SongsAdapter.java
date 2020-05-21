package c346.rp.edu.sg.p05ps;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SongsAdapter extends ArrayAdapter<Song> {
    Context context;
    ArrayList<Song> songs;
    int resource;
    ImageView iv1, iv2, iv3, iv4, iv5;
    TextView tvYearShow, tvTitleShow, tvSingersShow;

    public SongsAdapter(Context context, int resource, ArrayList<Song> songs){
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        tvYearShow = rowView.findViewById(R.id.tvYearShow);
        tvTitleShow = rowView.findViewById(R.id.tvTitleShow);
        tvSingersShow = rowView.findViewById(R.id.tvSingersShow);
        iv1 = rowView.findViewById(R.id.ivStar1);
        iv2 = rowView.findViewById(R.id.ivStar2);
        iv3 = rowView.findViewById(R.id.ivStar3);
        iv4 = rowView.findViewById(R.id.ivStar4);
        iv5 = rowView.findViewById(R.id.ivStar5);

        Song song = songs.get(position);
        tvYearShow.setText(String.valueOf(song.getYear()));
        tvTitleShow.setText(song.getTitle());
        tvSingersShow.setText(song.getSingers());

        if (song.getStars() == 0) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_off);
            iv2.setImageResource(android.R.drawable.btn_star_big_off);
            iv1.setImageResource(android.R.drawable.btn_star_big_off);
        } else if (song.getStars() == 1) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_off);
            iv2.setImageResource(android.R.drawable.btn_star_big_off);
        } else if (song.getStars() == 2) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_off);
        } else if (song.getStars() == 3) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
        } else if(song.getStars() == 4){
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
        }

        return rowView;

    }
}
