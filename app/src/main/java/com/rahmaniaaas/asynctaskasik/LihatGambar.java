package com.rahmaniaaas.asynctaskasik;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class LihatGambar extends AppCompatActivity {
    //Membuat variabel untuk widget dinamis
    private EditText editurl;
    private ImageView gambar;
    private Button carigambar;
    private ProgressDialog pd1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lihat_gambar);
            //Menambahkan judul pada tampilan menu Lihat Gambar
            setTitle("Lihat Gambar");

            //refer variabel yang telah dibuat
            gambar = (ImageView)findViewById(R.id.image1);
            editurl = (EditText)findViewById(R.id.edit_url);
        }
        //method saat button diklik
        public void cari(View view) {
            //loading gambar dari internet ke imageview menggunakan library picasso
            Picasso.with(LihatGambar.this).load(editurl.getText().toString())
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher)
                    .into(gambar);
        }
    }