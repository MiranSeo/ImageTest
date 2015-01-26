package com.example.miranseo.imagetest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
        private GridView gridView;
        public static List<Images> imageList = null;
        public static ImageAdapter imageAdapter;
        public static String url = null;

        /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Intent를 얻어오고 액션과 MIME 타입을 가져온다.
            Intent intent = getIntent();
            String action = intent.getAction();
            String type = intent.getType();

            // Intent의 Action 종류에 따라 비교 후 해당 함수 수행.
            if (Intent.ACTION_SEND.equals(action) && type != null) {
                if (type.startsWith("image/")) {
                    handleSendImage(intent); // 단일 image 를 처리한다.
                }
            } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
                if (type.startsWith("image/")) {
                    handleSendMultipleImages(intent); // 여러 image 들을 처리한다.
                }
            }

//            DatabaseHandler db = new DatabaseHandler(this);


            GridView gridview = (GridView) findViewById(R.id.gridview);
            imageAdapter = new ImageAdapter(this);
            gridview.setAdapter(imageAdapter);
            imageAdapter.notifyDataSetChanged();

            gridview.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

    // Intent의 Type이 image일 경우 수행
    void handleSendImage(Intent intent) {
        DatabaseHandler db = new DatabaseHandler(this);
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        url = imageUri.toString();

        // 보내는 쪽에서 pacelable 로 구현한 객체를 intent에 넣어 전달하면 getParcelableExtra로 받는다
        if (imageUri != null) {
            //  전달 받은 image 를 사용한다.
            //byte[] logoImage = getLogoImage(url);

            // Inserting Contacts
            Log.d("Insert: ", "Inserting ..");

            db.addImages(new Images(url));

            Log.i("********************", url);

            // Reading all contacts
            Log.d("Reading: ", "Reading all contacts..");
            //List<Images> imageses = db.getAllImages();
            imageList = db.getAllImages();
            for (Images images : imageList) {
                String log = "Name: " + images.getPhoto();
                // Writing Contacts to log
                Log.d("Name: ", log);

            }
            imageAdapter.mThumbIds.addAll(imageList);
            //imageAdapter.mThumbIds.add();
            imageAdapter.notifyDataSetChanged();


            db.getAllImages();
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // 전달받은 여러개의 image 를 사용한다.
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



 }