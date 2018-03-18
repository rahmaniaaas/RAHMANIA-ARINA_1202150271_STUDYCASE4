package com.rahmaniaaas.asynctaskasik;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class LihatNama extends AppCompatActivity {
    //Membuat variabel untuk widget dinamis
    private ListView mListView;
    private ProgressBar mProgressBar;
    private String [] mMahasiswa= {
            "Rahmania","Arina","Ari","Nia","Lenni",
            "Yuniar","Nurul","Mega","Aufa","Dhany","Bowo","Ainun","Risma","Syifa","Ivana",
            "Lala","Ibnu","Banna","Happy","Sinta"
    }; // ListView daftar nama mahasiswa yang akan ditampilkan

    private AddItemToListView mAddItemToListView;
    private Button mStartAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_nama);
        setTitle("Lihat Nama"); //set title pada tampilan



        //Me-refer semua variabel yang telah dibuat sebelumnya

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar1);
        mListView = (ListView) findViewById(R.id.listView);
        mStartAsyncTask = (Button) findViewById(R.id.btn_async1);

        // Membuat progress bar terlihat ketika aplikasi running
         mListView.setVisibility(View.GONE);

       // Mengeset adapter listview
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));


        // listener jika button asynctask diklik
        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //proses menghubungkan adapter dengan listview
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();

            }
        });
    }


    //class asynctask
    public class AddItemToListView  extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(LihatNama.this);

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter(); //adapter

            //membuat progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            //Jika button negative diklik (CANCEL PROCESS)
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show(); //Menjalankan progressDialog
        }

        //Melaksanakan doInBackround task
        @Override
        protected Void doInBackground(Void... params) {
            for (String item : mMahasiswa){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int) ((counter/(float)mMahasiswa.length)*100);
            mProgressBar.setProgress(current_status);

            mProgressDialog.setProgress(current_status);

            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++;
        }

        //Bila doInBackground()metode selesai, nilai pengembalian secara otomatis akan diteruskan ke onPostExecute()callback.
        @Override
        protected void onPostExecute(Void aVoid) {
            //menyembunyikan progreebar
            mProgressBar.setVisibility(View.GONE);

            //menghapus progress dialog
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }



    }


    }
