package com.example.hanulproject.menu.complain;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanulproject.R;
import com.example.hanulproject.login.LoginRequest;
import com.example.hanulproject.task.task.Delete;
import com.example.hanulproject.task.task.DownLoad;
import com.example.hanulproject.task.task.detail.ComplainCallDetail;
import com.example.hanulproject.vo.ComplainVO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

public class ComplainDetail extends AppCompatActivity {
    Button modify, back, delete, btnDownload;
    ComplainVO vo;
    String filepath, fileName;
    TextView no, title, content, writer, readcnt, filename;
    Bitmap mSaveBm;

    @Override
    protected void onResume() {
        super.onResume();

        ComplainCallDetail detail = new ComplainCallDetail(vo.getNo());
        try {
            vo = detail.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        title.setText(vo.getTitle());
        writer.setText(vo.getWriter());
        content.setText(vo.getContent());
        filename.setText(vo.getFilename());
        fileName = vo.getFilename();
        filepath = vo.getFilepath();
        if(filepath != null && !(filepath.equals(""))){
            download();
        }else{
            btnDownload.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complain_detail);
        title=findViewById(R.id.cpdtitle);
        writer=findViewById(R.id.cpdwriter);
        content=findViewById(R.id.cpdcontent);
        filename=findViewById(R.id.cpdfilename);
        modify=findViewById(R.id.modify);
        delete=findViewById(R.id.delete);
        btnDownload=findViewById(R.id.btnPhotoLoad);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               reset();
            }
        });

        vo = (ComplainVO) getIntent().getSerializableExtra("vo");

        if(LoginRequest.vo.getEmail().equals(vo.getWriter()) || LoginRequest.vo.getAdmin().equals("Y") || LoginRequest.vo.getId().equals(vo.getWriter())){
            modify.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
        }else{
            modify.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComplainDetail.this,ComplainModify.class);
                intent.putExtra("vo",vo);
                startActivityForResult(intent,200);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ComplainDetail.this);
                builder.setTitle("삭제여부");
                builder.setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Delete delete = new Delete(vo.getNo(),2);
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

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("filepath",filepath);
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

                    Toast.makeText(ComplainDetail.this,
                            fileName+"이 저장되었습니다", Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ComplainDetail.this,
                            e.toString(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ComplainDetail.this,
                            e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 200:

                    break;
            }
        }
    }

    // finish 실행시 새로고침 시켜주는 함수
    void reset(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","result");
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    //다운로드할수 있게 준비해주는 메소드 filePath 받아온 다음에 바로 실행해야함
    void download(){
        DownLoad downLoad = new DownLoad(filepath);
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
