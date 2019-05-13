package com.example.hanulproject.menu.complain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hanulproject.R;
import com.example.hanulproject.task.task.Update;
import com.example.hanulproject.vo.ComplainVO;

public class ComplainModify extends AppCompatActivity {
    ComplainVO vo;
    EditText cptitle , cpcontent;
    Button back, modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complain_modify);

        vo = (ComplainVO) getIntent().getSerializableExtra("vo");
        cptitle = findViewById(R.id.cpmtitle);
        cpcontent = findViewById(R.id.cpmcontent);
        back = findViewById(R.id.back);
        modify = findViewById(R.id.modify);

        cptitle.setText(vo.getTitle());
        cpcontent.setText(vo.getContent());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo.setTitle(cptitle.getText().toString());
                vo.setContent(cptitle.getText().toString());

                Update update = new Update(vo);
                update.execute();

                cptitle.setText(vo.getTitle());
                cpcontent.setText(vo.getContent());
                finish();
            }
        });


    }
}
