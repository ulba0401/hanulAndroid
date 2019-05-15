package com.example.hanulproject.menu.community;

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
import android.widget.GridView;
import android.widget.ListView;

import com.example.hanulproject.R;
import com.example.hanulproject.menu.Menu_main;
import com.example.hanulproject.task.adapter.CommunityAdapter;
import com.example.hanulproject.task.task.Select;
import com.example.hanulproject.vo.CommunityVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Community_main extends Fragment implements Serializable {

    Menu_main activity;
    ArrayList<CommunityVO> cmlist;
    GridView gridView;
    CommunityAdapter adapter;
    Select select;
    FloatingActionButton communityPlus;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.community_main, container, false);
        communityPlus = rootView.findViewById(R.id.communityPlus);
        gridView = rootView.findViewById(R.id.grid_view);

        cmlist = new ArrayList<>();
        adapter = new CommunityAdapter(getActivity(), R.layout.community_list_view, cmlist);
        gridView.setAdapter(adapter);

        select = new Select(cmlist, adapter);
        select.execute();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommunityVO vo = (CommunityVO) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), CommunityDetail.class);
                intent.putExtra("vo", vo);
                startActivityForResult(intent,200);
            }
        });

        communityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommunityInsertPage.class);
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
                    Log.d("onActivityResult","Complain 새로고침");
                    break;
            }
        }
    }

    public void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }
}
