package com.example.android.angelamalau_12021502161_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ToDoList extends AppCompatActivity {
    //deklarasi variable yang akan digunakan
    database db;
    RecyclerView rv;
    adapter adapter;
    ArrayList<addData> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        setTitle("To Do List"); //set title menjadi To Do List

        //mengakses recyclerview yang ada pada layout
        rv = findViewById(R.id.recview);

        datalist = new ArrayList<>();//membuat araylist baru

        db = new database(this);//membuat database baru

        db.readdata(datalist);//memanggil method readdata

        //menginisialkan shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Color", R.color.white);

        //membuat adapter baru
        adapter = new adapter(this,datalist, color);
        //menghindari perubahan ukuran yang tidak perlu ketika menambahkan / hapus item pada recycler view
        rv.setHasFixedSize(true);
        //menampilkan layoutnya linier
        rv.setLayoutManager(new LinearLayoutManager(this));
        //inisiasi adapter untuk recycler view
        rv.setAdapter(adapter);

        //menjalankan method hapus data pada list to do
        swipeToDelete();
    }

    //membuat method untuk menghapus item pada to do list
    public void swipeToDelete(){
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                addData current = adapter.getData(position);
                //apabila item di swipe ke arah kiri
                if(direction==ItemTouchHelper.RIGHT){
                    //remove item yang dipilih dengan mengenali todonya sebagai primary key
                    if(db.deletedata(current.getTodo())){
                        //menghapus data
                        adapter.deleteData(position);
                        //membuat snack bar dan pemberitahuan bahwa item sudah terhapus dengan durasi 1 sekon
                        Snackbar.make(findViewById(R.id.coor), "Data Dihapus", 1000).show();
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rv);
    }
    //ketika menu pada activity dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    //method yang dijalankan ketika item dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item
        int id = item.getItemId();

        //apabila item yang dipilih adalah setting
        if (id==R.id.action_settings){
            //membuat intent baru dari list to do ke pengaturan
            Intent intent = new Intent(ToDoList.this, setting.class);
            startActivity(intent); //memulai intent
            finish();//mengakhiri activity setelah intent dijalankan
        }
        return true;
    }

    //method yang akan dijalankan ketika tombol add diklik
    public void add(View view) {
        //intent dari list to do ke add to do
        Intent intent = new Intent(ToDoList.this, addToDo.class);
        startActivity(intent); //memulai intent
    }
}
