package com.spartanmart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.spartanmart.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 100;
    public static final int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_myAccount)
    public void onUserSelectMyAccount() {
        startActivity(new Intent(MainActivity.this, AccountActivity.class));
        finish();
    }

    @OnClick(R.id.btn_notification)
    public void onUserSelectNotification() {
        startActivity(new Intent(MainActivity.this, AccountActivity.class));
    }

    @OnClick(R.id.btn_myAccount)
    public void onUserSelectSell(View view) {
        PopupMenu popUpMenu = new PopupMenu(MainActivity.this, view);
        popUpMenu.getMenuInflater().inflate(R.menu.sell_menu,popUpMenu.getMenu());
        popUpMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_camera:
                        Toast.makeText(getApplicationContext(),"Take a Picture",Toast.LENGTH_LONG).show();
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                        }
                        return true;

                    case R.id.item_gallery:
                        Toast.makeText(getApplicationContext(),"Your Photo",Toast.LENGTH_LONG).show();
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                        return true;
                }

                return true;
            }
        });
        popUpMenu.show();
    }

}
