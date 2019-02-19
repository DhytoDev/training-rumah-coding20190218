package id.co.rumahcoding.sekolahku.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "School", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE STUDENT (id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, " +
                "last_name TEXT, phone TEXT, email TEXT, gender TEXT, education TEXT, hobby TEXT, address TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
