package com.example.hanulproject.menu.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.menu.notice.NoticeDetail;
import com.example.hanulproject.vo.CommunityVO;

public class CommunityDetail extends AppCompatActivity {

    Button modify, back, delete;
    CommunityVO vo;
    TextView no, title, content, writer, writedate, readcnt, filename, filepath ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_detail);
        title=findViewById(R.id.cmdtitle);
        writer=findViewById(R.id.cmdwriter);
        content=findViewById(R.id.cmdcontent);
        filename=findViewById(R.id.cmdfilename);
        modify=findViewById(R.id.modify);
        delete=findViewById(R.id.delete);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vo = (CommunityVO) getIntent().getSerializableExtra("vo");
        title.setText(vo.getTitle());
        writer.setText(vo.getWriter());
        content.setText(vo.getContent());
        //filename.setText(vo.getFilename());


        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityDetail.this,CommunityModify.class);
                intent.putExtra("vo",vo);
                startActivity(intent);
            }
        });
    }
}
