package id.co.rumahcoding.sekolahku.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import id.co.rumahcoding.sekolahku.model.Student;

public class DatabaseManager {

    private SQLiteDatabase db ;
    private DatabaseHelper dbHelper ;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    public long addStudent(Student student){
        ContentValues cv = new ContentValues();
        cv.put("first_name", student.getFirstName());
        cv.put("last_name", student.getLastName());
        cv.put("phone", student.getPhone());
        cv.put("email", student.getEmail());
        cv.put("gender", student.getGender());
        cv.put("education", student.getEducation());
        cv.put("hobby", student.getHobby());
        cv.put("address", student.getAddress());

        return db.insert("STUDENT", null, cv);
    }



}
