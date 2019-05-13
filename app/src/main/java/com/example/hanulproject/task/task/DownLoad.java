package com.example.hanulproject.task.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;

public class DownLoad extends AsyncTask<Void, Void, Bitmap> {

    String filePath;

    public DownLoad(String filePath){
        this.filePath = filePath;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap imgBitmap = null;
        InputStream in = null;

        try{
            in = new java.net.URL(filePath).openStream();
            imgBitmap = BitmapFactory.decodeStream(in);
            in.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return imgBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
