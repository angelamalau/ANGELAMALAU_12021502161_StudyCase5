package com.example.android.angelamalau_12021502161_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addToDo extends AppCompatActivity {
    //deklarasi variable yang akan digunakan
    EditText ToDo, Description, Priority;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        //set title menjadi add to do
        setTitle("Add To Do");

        //menginisialkan dengan id untuk di class ini
        ToDo        = (EditText) findViewById(R.id.editTodo);
        Description = (EditText) findViewById(R.id.editDesc);
        Priority    = (EditText) findViewById(R.id.editPriority);
        db          = new database(this);
    }

    @Override
    public void onBackPressed() {
        //membuat intent baru dari add to do menuju list to do
        Intent intent = new Intent(addToDo.this, ToDoList.class);
        startActivity(intent);//memulai intent
        this.finish(); //mengakhiri activity setelah intent dijalankan
    }

    //method yang dijalanan ketika tombol tambah to do diklik
    public void tambah(View view) {
        //apabila data todoname, deskripsi dan prioritas di isi,
        if (db.inputdata(new addData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //maka akan menampilkan toast bahwa data berhasil di tambahkan ke dalam list
            Toast.makeText(this, "To Do List ditambahkan!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(addToDo.this, ToDoList.class)); //berpindah dari add to do ke list to do
            this.finish(); //mengakhiri activity yang sudah dilakukan

        }else {
            //apabila edit text kosong maka akan muncul toast bahwa tidak bisa menambah ke dalam list
            Toast.makeText(this, "Tidak bisa ditambahkan ke dalam list", Toast.LENGTH_SHORT).show();
            //set semua edit text menjadi kosong
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }
}
