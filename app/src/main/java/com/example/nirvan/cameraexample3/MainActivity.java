package com.example.nirvan.cameraexample3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{


    ImageView myImage;
    private String pictureImagePath = "";
    Uri outputFileUri;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button imgButton=(Button) findViewById(R.id.imgButton);


        View.OnClickListener imgButtonClickListener=new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = timeStamp + ".jpg";
                File storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
                File file = new File(pictureImagePath);
                outputFileUri = Uri.fromFile(file);
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(cameraIntent, 1);
            }
        };
        imgButton.setOnClickListener(imgButtonClickListener);


    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("TAG","CUSTOOOM");
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            File imgFile = new File(pictureImagePath);
           if (imgFile.exists())
            {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                myImage = (ImageView) findViewById(R.id.imageViewTest);

                //
                final RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                relativeParams.addRule(RelativeLayout.BELOW,R.id.text);
                myImage.setLayoutParams(relativeParams);
                //
                myImage.setImageBitmap(myBitmap);

            }
        }






    }



}
