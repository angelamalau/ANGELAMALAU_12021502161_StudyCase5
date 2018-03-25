package com.example.android.angelamalau_12021502161_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class setting extends AppCompatActivity {
    //deklarasi variable yang akan digunakan
    TextView shapecolor;
    int idwarna;
    AlertDialog.Builder alert;
    SharedPreferences.Editor sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");

        //membuat alert dialog baru
        alert = new AlertDialog.Builder(this);

        //menginisialisasi shared preference
        SharedPreferences sharedP = getApplicationContext().getSharedPreferences("Preferences", 0);
        sp = sharedP.edit();
        idwarna = sharedP.getInt("Color", R.color.white);

        //mengakses text view pada layout
        shapecolor = findViewById(R.id.shapecolor);

        //set shape color dengan color id yang terpilih
        shapecolor.setText(getShapeColor(idwarna));
    }

    //apabila tombol back diklik
    @Override
    public void onBackPressed() {
        //intent baru dari setting menuju list to do
        Intent intent = new Intent(setting.this, ToDoList.class);
        startActivity(intent); //memulai intent
        finish(); //mengakhiri activity setelah intent dijalankan
    }

    //method yang dijalankan ketika pilihan Menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.onBackPressed(); //menjalankan method onbackpressed
        }
        return true;
    }

    //mendapatkan string warna yang akan digunakan untuk mengubah shape color
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    public void choosecolor(View view) {
        alert.setTitle("Shape Color"); //set title menjadi Shape Color
        //membuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.settingwarna, null);
        alert.setView(view1); //menampilkan view yang telah dibuat

        //mengakses radio group pada layout
        final RadioGroup radG = view1.findViewById(R.id.radiocolor);
        radG.check(getColorid(idwarna));

        //ketika klik OK pada alert dialog
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        idwarna = R.color.red;
                        break;
                    case R.id.green:
                        idwarna = R.color.green;
                        break;
                    case R.id.blue:
                        idwarna = R.color.blue;
                        break;
                    case R.id.white:
                        idwarna = R.color.white;
                        break;
                }
                //set shape color menjadi color id yang dipilih
                shapecolor.setText(getShapeColor(idwarna));

                //menaruh shared preference
                sp.putInt("Color", idwarna);

                //commit shared preference
                sp.commit();
            }
        });

        //ketika klik cancel pada alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //membuat dan menampilkan alert dialog
        alert.create().show();
    }
}
