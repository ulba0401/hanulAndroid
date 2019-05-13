package com.example.hanulproject.menu.complain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.hanulproject.R;
import com.example.hanulproject.menu.Menu_main;
import com.example.hanulproject.menu.community.CommunityInsertPage;
import com.example.hanulproject.task.adapter.ComplainAdapter;
import com.example.hanulproject.task.task.Select;
import com.example.hanulproject.vo.ComplainVO;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Complain_main extends Fragment {

    Menu_main activity;
    ListView listView;
    ArrayList<ComplainVO> cplist;
    ComplainAdapter adapter;
    Select select;
    FloatingActionButton complainPlus;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Menu_main) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.complain_main, container, false);

        complainPlus=rootView.findViewById(R.id.complainPlus);
        listView=rootView.findViewById(R.id.cplist_view);

        cplist = new ArrayList<>();
        adapter = new ComplainAdapter(getActivity(), R.layout.complain_list_view, cplist);
        listView.setAdapter(adapter);

        select = new Select(cplist, adapter);
        select.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ComplainVO vo = (ComplainVO) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), ComplainDetail.class);
                intent.putExtra("vo", vo);
                startActivity(intent);
            }
        });

        complainPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ComplainInsertPage.class);
                startActivityForResult(intent,200);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                // MainActivity 에서 요청할 때 보낸 요청 코드 (200)
                case 200:
                    refresh();
                    Log.d("onActivityResult","성공함여");
                    break;
            }
        }
    }

    public void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }
}
