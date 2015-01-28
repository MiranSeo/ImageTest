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
        public static ImageAdapter imageAdapter;
        public String url = null;
        public static Uri imageUri = null;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Intent를 얻어오고 액션과 MIME 타입을 가져온다.
        //Intent intent = getIntent();
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


//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        imageAdapter = new ImageAdapter(this);
//        gridview.setAdapter(imageAdapter);
//
//
//        gridview.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//        imageAdapter.notifyDataSetChanged();
    }

    /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            GridView gridview = (GridView) findViewById(R.id.gridview);
            imageAdapter = new ImageAdapter(this);
            gridview.setAdapter(imageAdapter);
            // 1/28 수정부분!!!!!-----------------------앱 처음 실행시켰을때도 DB에 있는 이미지들이 보이게 하려면?-----------
            // url = db.getAllImages();
            // imageAdapter.mThumbIds.add(url);


            gridview.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                }
            });
            imageAdapter.notifyDataSetChanged();


        }

    @Override
    protected void onResume() {
        super.onResume();
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
    }

    // Intent의 Type이 image일 경우 수행
    void handleSendImage(Intent intent) {
        DatabaseHandler db = new DatabaseHandler(this);
        imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        url = imageUri.toString();

        // 보내는 쪽에서 pacelable 로 구현한 객체를 intent에 넣어 전달하면 getParcelableExtra로 받는다
        if (imageUri != null) {
            // Inserting Contacts
            Log.d("Insert: ", "Inserting ..");

            db.addImages(url);

            Log.i("********************", url);

            // Reading all contacts
            Log.d("Reading: ", "Reading all contacts..");
            url = db.getAllImages();
            //db.getAllImages();
            //getAllImages에 문제가 있음!!
            }

            imageAdapter.mThumbIds.add(url);

            for(int i = 0; i< ImageAdapter.mThumbIds.size(); i++) {
                Log.d("mThumblds****", "" + imageAdapter.mThumbIds.get(i));
            }




            //db.getAllImages();
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
