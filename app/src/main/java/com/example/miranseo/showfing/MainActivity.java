
package com.example.miranseo.showfing;

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
import android.util.Log;


public class MainActivity extends ActionBarActivity {
    private GridView gridView;
    public static ImageAdapter imageAdapter;
    public static String url = null;
    public static Uri imageUri = null;
    public static ArrayList<Images> imageList = null;

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
                imageAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        imageList = db.getList();
        imageAdapter = new ImageAdapter(this, imageList);
        gridview.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> paretnt, View v, int position, long id) {

                Intent intent = new Intent(MainActivity.this, FloatingWindowService.class);
                int pos = position;
                intent.putExtra("position", pos);
                startService(intent);
            }
        });
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
                imageAdapter.notifyDataSetChanged();
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
            imageList = null;
            Log.d("Insert: ", "Inserting ..");
            db.addImages(url);
            Log.i("********************", url);

            // Reading all contacts
            Log.d("Reading: ", "Reading all contacts..");
//            //url = db.getAllImages();

            imageList = db.getList();


        }

        imageAdapter.notifyDataSetChanged();
;

        for(int i = 0; i< imageList.size(); i++) {
            Log.d("imageList uri****", "" + i + imageList.get(i).getPhoto());
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