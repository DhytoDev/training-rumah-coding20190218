package id.co.rumahcoding.sekolahku.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import id.co.rumahcoding.sekolahku.R;
import id.co.rumahcoding.sekolahku.model.Student;

public class DetailActivity extends AppCompatActivity {

    private TextView textName, textPhone, textEmail, textGender, textHobby, textEducation, textAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textName = findViewById(R.id.textview_name);
        textPhone = findViewById(R.id.textview_phone);
        textGender = findViewById(R.id.textview_gender);
        textHobby = findViewById(R.id.textview_hobby);
        textEducation = findViewById(R.id.textview_education);
        textAddress = findViewById(R.id.textview_address);

        Student student = getIntent().getParcelableExtra("student");

        textName.setText(String.format("%s %s", student.getFirstName(), student.getLastName()));
        textPhone.setText(student.getPhone());
        textGender.setText(student.getGender());
        textHobby.setText(student.getHobby());
        textAddress.setText(student.getAddress());
        textEducation.setText(student.getEducation());
    }
}
