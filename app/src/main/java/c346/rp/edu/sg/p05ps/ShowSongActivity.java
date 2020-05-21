package c346.rp.edu.sg.p05ps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSongActivity extends AppCompatActivity {

    ListView lvSongs;
    SongsAdapter aa;
    Button btnFilter;
    int requestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        lvSongs = findViewById(R.id.lvSongs);

        DBHelper dbh = new DBHelper(this);
        final ArrayList<Song> songs = dbh.getAllSongs();

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

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song target = songs.get(position);
                Intent i = new Intent(ShowSongActivity.this, EditActivity.class);
                i.putExtra("data", target);
                startActivityForResult(i, requestCode);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(data != null) {
                DBHelper dbh = new DBHelper(ShowSongActivity.this);
                aa.notifyDataSetChanged();
            }

        }
    }
}
