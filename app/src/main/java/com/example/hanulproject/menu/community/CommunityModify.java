package com.example.hanulproject.menu.community;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hanulproject.R;
import com.example.hanulproject.task.common.CommonMethod;
import com.example.hanulproject.task.task.Update;
import com.example.hanulproject.vo.CommunityVO;
import com.example.hanulproject.vo.ComplainVO;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.Date;

import static com.example.hanulproject.task.common.CommonMethod.ipConfig;

public class CommunityModify extends AppCompatActivity {

    ImageView cmmImageView;
    Button modify, cancel, cmmPhotonLoad, btnPhotoDelete;
    EditText cmmtitle, cmmcontent;
    CommunityVO vo;
    String title = "", content = "";

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
        setContentView(R.layout.community_modify);

        now = System.currentTimeMillis();
        date = new Date(now);
        tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");

        vo = (CommunityVO) getIntent().getSerializableExtra("vo");
        cmmImageView = findViewById(R.id.cmmImageView);
        modify = findViewById(R.id.modify);
        cancel = findViewById(R.id.cancel);
        cmmPhotonLoad = findViewById(R.id.cmmPhotonLoad);
        cmmtitle = findViewById(R.id.cmmtitle);
        cmmcontent = findViewById(R.id.cmmcontent);
        btnPhotoDelete = findViewById(R.id.btnPhotoDelete);

        cmmtitle.setText(vo.getTitle());
        cmmcontent.setText(vo.getContent());
        cmmImageView.setVisibility(View.GONE);

        cmmPhotonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmmImageView.setVisibility(View.VISIBLE);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo.setTitle(cmmtitle.getText().toString());
                vo.setContent(cmmcontent.getText().toString());

                Update update = new Update(vo);
                update.setFileInfo(uploadType, imageFilePathA, imageUploadPathA, uploadFileName);
                update.execute();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("title",vo.getTitle());
                resultIntent.putExtra("content",vo.getContent());
                resultIntent.putExtra("filePath",vo.getFilepath());
                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });

        btnPhotoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadType = "delete";
                cmmImageView.setVisibility(View.GONE);
            }
        });

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);

        if(vo.getFilename() != null && !(vo.getFilename().equals(""))) {
            cmmImageView.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(vo.getFilepath().toString(),
                    cmmImageView, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String s, View view) {
//                            viewHolder.progressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingFailed(String s, View view, FailReason failReason) {
//                            viewHolder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//                            viewHolder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadingCancelled(String s, View view) {
//                            viewHolder.progressBar.setVisibility(View.GONE);
                        }
                    });
        }
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
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                if (newBitmap != null) {
                    cmmImageView.setImageBitmap(newBitmap);
                } else {
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                }
                if(vo.getFilename() == null || vo.getFilename().equals("")){
                    imageFilePathA = path;
                    int communityInsert = Log.d("CommunityUpdate", "imageFilePathA Path : " + imageFilePathA);
                    uploadFileName = tmpDateFormat.format(date)+(imageFilePathA.split("/")[imageFilePathA.split("/").length - 1]);
                    imageUploadPathA = ipConfig + "/AA/resources/images/community/" + uploadFileName;
                    Log.d("CommunityUpdate",uploadFileName);
                }else {
                    imageFilePathA = path;
                    Log.d("CommunityUpdate", "imageFilePathA Path : " + imageFilePathA);
                    uploadFileName = vo.getFilename();
                    imageUploadPathA = ipConfig + "/AA/resources/images/community/" + vo.getFilename();
                    Log.d("CommunityUpdate",uploadFileName);
                }
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
