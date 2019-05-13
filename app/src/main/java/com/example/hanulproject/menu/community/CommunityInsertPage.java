package com.example.hanulproject.menu.community;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hanulproject.R;
import com.example.hanulproject.task.task.Insert;
import com.example.hanulproject.vo.CommunityVO;

import static com.example.hanulproject.task.common.CommonMethod.isNetworkConnected;

public class CommunityInsertPage extends AppCompatActivity {
    Button insert, cancel;
    EditText cmititle, cmicontent;
    CommunityVO vo;
    String title = "", content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_insert);
        insert = findViewById(R.id.insert);
        cancel =findViewById(R.id.cancel);
        cmititle = findViewById(R.id.cmititle);
        cmicontent = findViewById(R.id.cmicontent);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void btnAddClicked(View v){
        if (isNetworkConnected(this)==true){
            title = cmititle.getText().toString();
            content = cmicontent.getText().toString();
            Insert insert = new Insert(title, content, 3);
            insert.execute();

            finish();
        }else{
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }


}
