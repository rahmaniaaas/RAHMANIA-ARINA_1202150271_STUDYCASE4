package com.rahmaniaaas.asynctaskasik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lihatNama(View view) {
        //membuat intent jika memilih button lihat nama, maka akan pindah ke tampilan menu lihat nama
        Intent a = new Intent(this, LihatNama.class);
        startActivity(a);
    }

    public void lihatGambar(View view) {
        //membuat intent jika memilih button lihat gambar, maka akan pindah ke tampilan menu lihat gambar
        Intent b = new Intent(this, LihatGambar.class);
        startActivity(b);
    }
}
