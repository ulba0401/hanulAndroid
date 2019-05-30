package com.example.hanulproject.menu.notice;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanulproject.R;
import com.example.hanulproject.menu.complain.ComplainDetail;
import com.example.hanulproject.task.task.DownLoad;
import com.example.hanulproject.task.task.detail.NoticeCallDetail;
import com.example.hanulproject.vo.NoticeVO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

public class NoticeDetail extends AppCompatActivity {
    Button modify, back, btnDownload;
    NoticeVO vo;
    TextView no, title, content, writedate, readcnt, filename, filepath ;
    Bitmap mSaveBm;
    String filePath, fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_detail);
        title=findViewById(R.id.ndtitle);
        content=findViewById(R.id.ndcontent);
        filename=findViewById(R.id.ndfilename);
        btnDownload=findViewById(R.id.btnDownload);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vo = (NoticeVO) getIntent().getSerializableExtra("vo");

        try {
            NoticeCallDetail detail = new NoticeCallDetail(vo.getNo());
            vo = detail.execute().get();
        } catch (Exception e){
            e.printStackTrace();
        }
        filePath = vo.getFilepath();
        fileName = vo.getFilename();
        if(filePath != null && !(filePath.equals("")) ) {
            download();
        } else {
            btnDownload.setVisibility(View.GONE);
        }
        title.setText(vo.getTitle());
        content.setText(vo.getContent());
        filename.setText(vo.getFilename());

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("filepath",filePath);
                Log.d("filepath",fileName);

                OutputStream outStream = null;
                String extStorageDirectory =
                        Environment.getExternalStorageDirectory().toString();

                File file = new File(extStorageDirectory, fileName);
                try {
                    outStream = new FileOutputStream(file);
                    mSaveBm.compress(
                            Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();

                    Toast.makeText(NoticeDetail.this,
                            fileName+"이 저장되었습니다", Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(NoticeDetail.this,
                            e.toString(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(NoticeDetail.this,
                            e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    void download(){
        DownLoad downLoad = new DownLoad(filePath);
        mSaveBm = null;
        try {
            mSaveBm = downLoad.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
