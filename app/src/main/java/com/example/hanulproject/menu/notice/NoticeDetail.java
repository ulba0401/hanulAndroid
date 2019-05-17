package com.example.hanulproject.menu.notice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.task.task.detail.NoticeCallDetail;
import com.example.hanulproject.vo.NoticeVO;

public class NoticeDetail extends AppCompatActivity {
    Button modify, back;
    NoticeVO vo;
    TextView no, title, content, writedate, readcnt, filename, filepath ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_detail);
        title=findViewById(R.id.ndtitle);
        content=findViewById(R.id.ndcontent);
        filename=findViewById(R.id.ndfilename);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vo = (NoticeVO) getIntent().getSerializableExtra("vo");
//        title.setText(vo.getTitle());
//        content.setText(vo.getContent());
//        filename.setText(vo.getFilename());

        try {
            NoticeCallDetail detail = new NoticeCallDetail(vo.getNo());
            vo = detail.execute().get();
        } catch (Exception e){
            e.printStackTrace();
        }

        title.setText(vo.getTitle());
        content.setText(vo.getContent());
        filename.setText(vo.getFilename());

    }
}
