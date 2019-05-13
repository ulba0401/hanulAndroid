package com.example.hanulproject.menu.complain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.task.task.Update;
import com.example.hanulproject.vo.ComplainVO;

public class ComplainModify extends AppCompatActivity {
    ComplainVO vo;
    EditText cpmtitle , cpmcontent;
    Button back, modify;
    TextView cpmfilename;

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


    }
}
