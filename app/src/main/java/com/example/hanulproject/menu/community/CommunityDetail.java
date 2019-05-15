package com.example.hanulproject.menu.community;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.menu.notice.NoticeDetail;
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
        title.setText(vo.getTitle());
        writer.setText(vo.getWriter());
        content.setText(vo.getContent());
        filePath = vo.getFilepath();
        //filename.setText(vo.getFilename());

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

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityDetail.this,CommunityModify.class);
                intent.putExtra("vo",vo);
                startActivity(intent);
            }
        });
    }

    void reset(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","result");
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}
