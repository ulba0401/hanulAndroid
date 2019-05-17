package com.example.hanulproject.menu.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hanulproject.R;
import com.example.hanulproject.menu.Menu_main;
import com.example.hanulproject.task.adapter.UserAdapter;
import com.example.hanulproject.task.task.Select;
import com.example.hanulproject.vo.UserVO;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class List_main extends Fragment {
    Menu_main activity;
    ListView listView;
    ArrayList<UserVO> ulist;
    UserAdapter adapter;
    Select select;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.list_main, container, false);

        listView=rootView.findViewById(R.id.mblist_view);
        ulist=new ArrayList<>();
        adapter=new UserAdapter(getActivity(), R.layout.member_list_view, ulist);
        listView.setAdapter(adapter);

        select = new Select(ulist, adapter);
        select.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserVO vo= (UserVO) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), ListDetail.class);
                startActivityForResult(intent, 200);
            }
        });
        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 200: refresh();
                    Log.d("onActivityResult","User 새로고침");
            }
        }
    }
    public void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }
}
