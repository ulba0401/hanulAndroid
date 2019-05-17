package com.example.hanulproject.menu.community;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.login.LoginRequest;
import com.example.hanulproject.menu.complain.ComplainDetail;
import com.example.hanulproject.menu.notice.NoticeDetail;
import com.example.hanulproject.task.task.Delete;
import com.example.hanulproject.task.task.detail.CommunityCallDetail;
import com.example.hanulproject.vo.CommunityVO;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class CommunityDetail extends AppCompatActivity {

    Button modify, back, delete;
    CommunityVO vo;
    TextView no, title, content, writer, writedate, readcnt, filename ;
    String filePath;
    ImageView cmdImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_detail);
        title=findViewById(R.id.cmdtitle);
        writer=findViewById(R.id.cmdwriter);
        content=findViewById(R.id.cmdcontent);
//        filename=findViewById(R.id.cmdfilename);
        modify=findViewById(R.id.modify);
        delete=findViewById(R.id.delete);
        cmdImageView = findViewById(R.id.cmdImageView);
        cmdImageView.setVisibility(View.GONE);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        vo = (CommunityVO) getIntent().getSerializableExtra("vo");

        if(LoginRequest.vo.getEmail().equals(vo.getWriter()) || LoginRequest.vo.getAdmin().equals("Y")){
            modify.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
        }else{
            modify.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmdImageView.setImageResource(R.drawable.blank);
                cmdImageView.setVisibility(View.GONE);

                Intent intent = new Intent(CommunityDetail.this,CommunityModify.class);
                //intent.putExtra("vo",vo);
                startActivityForResult(intent,200);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CommunityDetail.this);
                builder.setTitle("삭제여부");
                builder.setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Delete delete = new Delete(vo.getNo(),3);
                        delete.execute();
                        reset();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CommunityCallDetail detail = new CommunityCallDetail(vo.getNo());
        try {
            vo = detail.execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }

        title.setText(vo.getTitle());
        writer.setText(vo.getWriter());
        content.setText(vo.getContent());
        filePath = vo.getFilepath();
        //filename.setText(vo.getFilename());

        imageLoad();
    }

    // 피니쉬할때 새로고침하게 해줌
    void reset(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","result");
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                // MainActivity 에서 요청할 때 보낸 요청 코드 (200)
                case 200:

                    break;
            }
        }

    }


    //이미지를 띄우는 메소드
    private void imageLoad(){

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);

        if(vo.getFilename() != null && !(vo.getFilename().equals(""))){
            cmdImageView.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(vo.getFilepath().toString(),
                    cmdImageView, new ImageLoadingListener() {
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
}