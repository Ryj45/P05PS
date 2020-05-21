package c346.rp.edu.sg.p05ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    TextView tvID;
    Button btnUpdate, btnDelete, btnCancel;
    EditText etTitleEdit, etSingersEdit, etYearEdit;
    RadioGroup rgStarsEdit;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tvID = findViewById(R.id.tvID);
        etTitleEdit = findViewById(R.id.etTitleEdit);
        etSingersEdit = findViewById(R.id.etSingersEdit);
        etYearEdit = findViewById(R.id.etYearEdit);
        rgStarsEdit = findViewById(R.id.rgStarsEdit);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel= findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");
        tvID.setText(String.valueOf(data.getId()));
        etTitleEdit.setText(data.getTitle());
        etSingersEdit.setText(data.getSingers());
        etYearEdit.setText(String.valueOf(data.getYear()));
        int star = data.getStars();
        if(star == 1){
            rgStarsEdit.check(R.id.rb11);
        } else if(star == 2){
            rgStarsEdit.check(R.id.rb12);
        }else if(star == 3){
            rgStarsEdit.check(R.id.rb13);
        }else if(star == 4){
            rgStarsEdit.check(R.id.rb14);
        }else{
            rgStarsEdit.check(R.id.rb15);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(etTitleEdit.getText().toString());
                data.setSingers(etSingersEdit.getText().toString());
                data.setYear(Integer.parseInt(etYearEdit.getText().toString()));
                int index =  rgStarsEdit.getCheckedRadioButtonId();
                RadioButton rb = findViewById(index);
                int stars = Integer.parseInt(rb.getText().toString());
                data.setStars(stars);
                dbh.updateSong(data);
                dbh.close();
                Intent a = new Intent();
                setResult(RESULT_OK, a);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                Intent a = new Intent();
                setResult(RESULT_OK, a);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent();
                setResult(RESULT_OK, a);
                finish();
            }
        });
    }
}
