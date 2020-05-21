package c346.rp.edu.sg.p05ps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSongActivity extends AppCompatActivity {

    ListView lvSongs;
    SongsAdapter aa;
    Button btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        lvSongs = findViewById(R.id.lvSongs);

        DBHelper dbh = new DBHelper(this);
        ArrayList<Song> songs = dbh.getAllSongs();

        aa = new SongsAdapter(this, R.layout.row, songs);
        lvSongs.setAdapter(aa);

        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowSongActivity.this);
                ArrayList<Song> songs = dbh.getFilterSongs();

                aa = new SongsAdapter(ShowSongActivity.this, R.layout.row, songs);
                lvSongs.setAdapter(aa);
            }
        });
    }
}
