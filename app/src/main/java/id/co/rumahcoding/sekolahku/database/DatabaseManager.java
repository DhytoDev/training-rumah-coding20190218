package id.co.rumahcoding.sekolahku.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import id.co.rumahcoding.sekolahku.model.Student;

public class DatabaseManager {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
        dbHelper.close();
    }

    public long addStudent(Student student) {
        ContentValues cv = setContentValues(student);

        return db.insert("STUDENT", null, cv);
    }

    public Student getStudentById(long id) {
        String sql = "SELECT * FROM STUDENT WHERE id = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{Long.toString(id)});

        cursor.moveToFirst();

        Student student = fetchRow(cursor);

        return student;
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student ORDER BY first_name DESC";
        Cursor cursor = db.rawQuery(sql, null);

        List<Student> students = fetchCursor(cursor);

        cursor.close();

        return students;
    }

    @NotNull
    private Student fetchRow(Cursor cursor) {
        Student student = new Student();
        student.setId(cursor.getLong(0));
        student.setFirstName(cursor.getString(1));
        student.setLastName(cursor.getString(2));
        student.setPhone(cursor.getString(3));
        student.setEmail(cursor.getString(4));
        student.setGender(cursor.getString(5));
        student.setEducation(cursor.getString(6));
        student.setHobby(cursor.getString(7));
        student.setAddress(cursor.getString(8));
        return student;
    }

    public long deleteAll() {
        return db.delete("STUDENT", null, null);
    }

    public long deleteStudentById(long id) {
        return db.delete("STUDENT", "id=?",
                new String[]{Long.toString(id)});
    }

    public long updateStudent(Student student) {
        ContentValues cv = setContentValues(student);

        return db.update("STUDENT", cv, "id=?",
                new String[]{Long.toString(student.getId())});

    }

    public List<Student> searchStudentByName(String keyword) {
        String sql = "SELECT * FROM STUDENT WHERE first_name LIKE ? " +
                "OR last_name LIKE ?";

        Cursor cursor = db.rawQuery(sql, new String[]{"%" + keyword + "%",
                "%" + keyword + "%"});

        List<Student> students = fetchCursor(cursor);

        cursor.close();

        return students;
    }

    @NotNull
    private List<Student> fetchCursor(Cursor cursor) {
        cursor.moveToFirst();

        List<Student> students = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            Student student = fetchRow(cursor);
            students.add(student);
            cursor.moveToNext();
        }
        return students;
    }

    @NotNull
    private ContentValues setContentValues(Student student) {
        ContentValues cv = new ContentValues();
        cv.put("first_name", student.getFirstName());
        cv.put("last_name", student.getLastName());
        cv.put("phone", student.getPhone());
        cv.put("email", student.getEmail());
        cv.put("gender", student.getGender());
        cv.put("education", student.getEducation());
        cv.put("hobby", student.getHobby());
        cv.put("address", student.getAddress());
        return cv;
    }
}
