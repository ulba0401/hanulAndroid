package com.example.hanulproject.menu.complain;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.task.task.Update;
import com.example.hanulproject.vo.ComplainVO;

import java.util.Date;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class ComplainModify extends AppCompatActivity {
    ComplainVO vo;
    EditText cpmtitle , cpmcontent;
    Button back, modify, btnPhotoLoad;
    TextView cpmfilename, fileName;
    String uploadType, imageFilePathA, imageUploadPathA, uploadFileName;

    long now;
    Date date;
    java.text.SimpleDateFormat tmpDateFormat;

    final int CAMERA_REQUEST = 1000;
    final int LOAD_IMAGE = 1001;
    final int VIDEO_REQUEST = 1003;
    final int LOAD_VIDEO = 1004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complain_modify);

        vo = (ComplainVO) getIntent().getSerializableExtra("vo");
        cpmtitle = findViewById(R.id.cpmtitle);
        cpmcontent = findViewById(R.id.cpmcontent);
        back = findViewById(R.id.back);
        modify = findViewById(R.id.modify);
        cpmfilename = findViewById(R.id.cpmfilename);
        btnPhotoLoad = findViewById(R.id.btnPhotoLoad);
        fileName = findViewById(R.id.cpmfilename);

        now = System.currentTimeMillis();
        date = new Date(now);
        tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");

        cpmtitle.setText(vo.getTitle());
        cpmcontent.setText(vo.getContent());
        cpmfilename.setText(vo.getFilename());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo.setTitle(cpmtitle.getText().toString());
                vo.setContent(cpmcontent.getText().toString());
                vo.setFilename(cpmfilename.getText().toString());

                Update update = new Update(vo);
                update.setFileInfo(uploadType, imageFilePathA, imageUploadPathA, uploadFileName);
                update.execute();

//                cptitle.setText(vo.getTitle());
//                cpcontent.setText(vo.getContent());

                Intent resultIntent = new Intent();
                resultIntent.putExtra("title",vo.getTitle());
                resultIntent.putExtra("content",vo.getContent());
                resultIntent.putExtra("filename",vo.getFilename());
                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });

        btnPhotoLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOAD_IMAGE && resultCode == RESULT_OK) {
            uploadType = "image";

            try {
                String path = "";
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    // Get the path from the Uri
                    path = getPathFromURI(selectedImageUri);
                }
                // 이미지 돌리기 및 리사이즈
//                Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
//                if (newBitmap != null) {
//                    //imageView.setImageBitmap(newBitmap);
//                } else {
//                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
//                }
                if(vo.getFilename() == null || vo.getFilename().equals("")){
                    imageFilePathA = path;
                    Log.d("ComplainInsert", "imageFilePathA Path : " + imageFilePathA);
                    uploadFileName = tmpDateFormat.format(date)+(imageFilePathA.split("/")[imageFilePathA.split("/").length - 1]);
                    imageUploadPathA = ipConfig + "/AA/resources/images/upload/" + uploadFileName;
                    Log.d("ComplainInsert",uploadFileName);
                }else{
                    imageFilePathA = path;
                    Log.d("ComplainUpdate", "imageFilePathA Path : " + imageFilePathA);
                    uploadFileName = vo.getFilename();
                    imageUploadPathA = ipConfig + "/AA/resources/images/upload/" + vo.getFilename();
                    Log.d("ComplainUpdate",uploadFileName);

                }
                fileName.setText(uploadFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d("ComplainInsert => ", "imagepath is null, whatever something is wrong!!");
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}
