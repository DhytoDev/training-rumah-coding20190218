package id.co.rumahcoding.sekolahku.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

import id.co.rumahcoding.sekolahku.R;
import id.co.rumahcoding.sekolahku.database.DatabaseManager;
import id.co.rumahcoding.sekolahku.model.Student;

public class FormActivity extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextEmail;
    private EditText editTextPhone, editTextAddress;
    private RadioGroup radioGroupGender;
    private Spinner spinnerEducation;
    private CheckBox checkBoxReading, checkBoxWriting, checkBoxCoding;
    private Button buttonSave;

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editTextFirstName = findViewById(R.id.edit_text_first_name);
        editTextLastName = findViewById(R.id.edit_text_last_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPhone = findViewById(R.id.edit_text_phone_number);
        editTextAddress = findViewById(R.id.edit_text_address);
        radioGroupGender = findViewById(R.id.radio_group_gender);
        spinnerEducation = findViewById(R.id.spinner_education);
        checkBoxReading = findViewById(R.id.checkbox_reading);
        checkBoxWriting = findViewById(R.id.checkbox_writing);
        checkBoxCoding = findViewById(R.id.checkbox_coding);

        long id = getIntent().getLongExtra("id", 0);

        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        Student student = null;

        if (id > 0) {
            student = databaseManager.getStudentById(id);
            Log.d("ID", String.valueOf(id));
        }

        if (student != null) {
            editTextFirstName.setText(student.getFirstName());
            editTextLastName.setText(student.getLastName());
            editTextEmail.setText(student.getEmail());
            editTextPhone.setText(student.getPhone());
            editTextAddress.setText(student.getAddress());

            String gender = student.getGender();

            if (gender.equals("Pria")) {
                radioGroupGender.check(R.id.radio_man);
            } else if (gender.equals("Wanita")) {
                radioGroupGender.check(R.id.radio_woman);
            }

            ArrayAdapter adapter = (ArrayAdapter) spinnerEducation.getAdapter();
            int position = adapter.getPosition(student.getEducation());
            spinnerEducation.setSelection(position);

            String hobby = student.getHobby();

            if (hobby.contains("Membaca")) {
                checkBoxReading.setChecked(true);
            } else {
                checkBoxReading.setChecked(false);
            }

            if (hobby.contains("Menulis")) {
                checkBoxWriting.setChecked(true);
            } else {
                checkBoxWriting.setChecked(false);
            }

            if (hobby.contains("Coding")) {
                checkBoxCoding.setChecked(true);
            } else {
                checkBoxCoding.setChecked(false);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseManager.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_save) {
            addStudent();
        }

        return super.onOptionsItemSelected(item);
    }

    private void addStudent() {
        boolean isValid = validateForm();

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();
        String address = editTextAddress.getText().toString();
        int checkedGenderId = radioGroupGender.getCheckedRadioButtonId();

        String gender = "";
        String hobby = "";

        String education = spinnerEducation.getSelectedItem().toString();

        if (checkedGenderId == R.id.radio_man) {
            gender = "Pria";
        } else if (checkedGenderId == R.id.radio_woman) {
            gender = "Wanita";
        } else {
            return;
        }

        if (checkBoxReading.isChecked()) {
            hobby += "Membaca,";
        }

        if (checkBoxWriting.isChecked()) {
            hobby += "Menulis,";
        }

        if (checkBoxCoding.isChecked()) {
            hobby += "Coding";
        }

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setPhone(phone);
        student.setGender(gender);
        student.setEducation(education);
        student.setHobby(hobby);
        student.setAddress(address);

        if (!isValid) {
            validateForm();
        } else {
            long intentId = getIntent().getLongExtra("id", 0);

            if (intentId > 0) {
                student.setId(intentId);
                databaseManager.updateStudent(student);
                Toast.makeText(this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                long id = databaseManager.addStudent(student);

                if (id == -1) {
                    Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Data berhasil disimpan dengan id " + id, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    private boolean validateForm() {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();

        if (firstName.isEmpty()) {
            editTextFirstName.setError("Nama depan harus diisi");
            return false;
        } else {
            if (!isValidName(firstName)) {
                editTextFirstName.setError("Invalid characters");
                return false;
            }
        }

        if (lastName.isEmpty()) {
            editTextLastName.setError("Nama belakang harus diisi");
            return false;
        } else {
            if (!isValidName(lastName)) {
                editTextLastName.setError("Invalid characters");
                return false;
            }

        }

        if (!isValidEmail(editTextEmail.getText().toString())) {
            editTextEmail.setError("Invalid Email");
            return false;
        }

        if (!isValidPhoneNumber(editTextPhone.getText().toString())) {
            editTextPhone.setError("Invalid phone number");
            return false;
        }

        return true;
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
