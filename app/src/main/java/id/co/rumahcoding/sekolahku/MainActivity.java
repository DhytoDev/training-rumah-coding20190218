package id.co.rumahcoding.sekolahku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextEmail;
    private EditText editTextPhone ;
    private RadioButton radioMan, radioWoman;
    //    private RadioButton radioWoman ;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFirstName = findViewById(R.id.edit_text_first_name);
        editTextLastName = findViewById(R.id.edit_text_last_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPhone = findViewById(R.id.edit_text_phone_number);
        radioMan = findViewById(R.id.radio_man);
        radioWoman = findViewById(R.id.radio_woman);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_save) {
//            Toast.makeText(this, "Menu save diklik", Toast.LENGTH_SHORT).show();
            validateForm();
        }

        return super.onOptionsItemSelected(item);
    }

    private void validateForm() {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();

        if (firstName.isEmpty()) {
            editTextFirstName.setError("Nama depan harus diisi");
        } else {
            if(!isValidName(firstName)) {
                editTextFirstName.setError("Invalid characters");
            }
        }

        if (lastName.isEmpty()) {
            editTextLastName.setError("Nama belakang harus diisi");
        } else {
            if(!isValidName(lastName)) {
                editTextLastName.setError("Invalid characters");
            }

        }

        if (!isValidEmail(editTextEmail.getText().toString())) {
            editTextEmail.setError("Invalid Email");
        }

        if (!isValidPhoneNumber(editTextPhone.getText().toString())) {
            editTextPhone.setError("Invalid phone number");
        }
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() > 10;
    }

    private boolean isValidName(String name) {
        final String NAME_PATTERN = "[a-zA-Z.? ]*";

        return Pattern.compile(NAME_PATTERN).matcher(name).matches();
    }

}
