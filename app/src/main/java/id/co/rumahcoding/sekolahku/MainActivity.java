package id.co.rumahcoding.sekolahku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private RadioButton radioMan, radioWoman;
    //    private RadioButton radioWoman ;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFirstName = findViewById(R.id.edit_text_first_name);
        radioMan = findViewById(R.id.radio_man);
        radioWoman = findViewById(R.id.radio_woman);
        buttonSave = findViewById(R.id.button_save);

        buttonSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (radioMan.isChecked()) {
                            radioMan.setChecked(false);
                            radioWoman.setChecked(true);
                        } else {
                            radioMan.setChecked(true);
                            radioWoman.setChecked(false);
                        }
                    }
                }
        );
    }
}
