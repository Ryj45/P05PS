package c346.rp.edu.sg.p05ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShow;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int index =  rgStars.getCheckedRadioButtonId();
                rb = findViewById(index);
                int stars = Integer.parseInt(rb.getText().toString());

                if(title.length() == 0 || singers.length() == 0 || etYear.getText().toString().length() == 0 || String.valueOf(index).length() == 0){
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                long insert_id = dbh.insertSong(title, singers, year, stars);
                if(insert_id != 1) {
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                }
                etTitle.setText("");
                etSingers.setText("");
                etYear.setText("");
                rgStars.clearCheck();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowSongActivity.class);
                startActivity(i);

            }
        });
    }
}
