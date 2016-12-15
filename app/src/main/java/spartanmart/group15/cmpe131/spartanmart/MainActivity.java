package spartanmart.group15.cmpe131.spartanmart;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;


import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static spartanmart.group15.cmpe131.spartanmart.R.id.btn_sell;

public class MainActivity extends AppCompatActivity {

    private Button btnMyAccount, btnNotification, btnSell;
    private static final int REQUEST_IMAGE_CAPTURE = 100;
    public static final int RESULT_LOAD_IMG = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMyAccount = (Button) findViewById(R.id.btn_myAccount);
        btnNotification = (Button) findViewById(R.id.btn_notification);
        btnSell = (Button) findViewById(btn_sell);

        btnMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
                finish();
            }
        });

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
            }
        });

        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popUpMenu=new PopupMenu(MainActivity.this,view);
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
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                                return true;
                        }

                        return true;
                    }
                });
                popUpMenu.show();
            }
        });



    }
}
