package id.co.rumahcoding.sekolahku.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import id.co.rumahcoding.sekolahku.R;
import id.co.rumahcoding.sekolahku.adapter.StudentListAdapter;
import id.co.rumahcoding.sekolahku.database.DatabaseManager;
import id.co.rumahcoding.sekolahku.model.Student;

public class ListActivity extends AppCompatActivity {

    private ListView studentListView;
    private List<Student> students;
    private StudentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        studentListView = findViewById(R.id.listview_students);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStudentsData();
    }

    private void getStudentsData() {
        DatabaseManager dbManager = new DatabaseManager(this);
        dbManager.open();

        students = dbManager.getAllStudents();

        adapter = new StudentListAdapter(this, students);
        studentListView.setAdapter(adapter);

        dbManager.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_data) {
            Intent intent = new Intent(ListActivity.this, FormActivity.class);
            startActivity(intent);
        } else if (id == R.id.delete_all) {
            new AlertDialog.Builder(this)
                    .setTitle("Hapus Semua Data")
                    .setMessage("Anda yakin ?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DatabaseManager dbManager = new DatabaseManager(ListActivity.this);
                            dbManager.open();

                            dbManager.deleteAll();

                            dbManager.close();

                            students.clear();
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .show();


        }

        return super.onOptionsItemSelected(item);
    }


}
