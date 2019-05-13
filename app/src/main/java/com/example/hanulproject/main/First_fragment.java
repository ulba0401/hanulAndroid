package com.example.hanulproject.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hanulproject.MainActivity;
import com.example.hanulproject.R;

public class First_fragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    MainActivity activity;
    ListView my_home_list;
    TextView setTemperatures, setFinedust, setUltrafinedust;


    // newInstance constructor for creating fragment with arguments
    public static First_fragment newInstance(int page, String title) {
        First_fragment fragment = new First_fragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment1, container, false);

        my_home_list = view.findViewById(R.id.my_home_list);

        setTemperatures = view.findViewById(R.id.setTemperatures);
        setFinedust = view.findViewById(R.id.setFinedust);
        setUltrafinedust = view.findViewById(R.id.setUltrafinedust);



       /*
        //집 리스트 조회처리

        dtos = new ArrayList<>();
        adapter = new NoticeAdapter(getActivity(),R.layout.noticeview ,dtos);
        listView.setAdapter(adapter);


        select = new NoticeSelect(dtos, adapter, progressDialog);
        select.execute();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoticeDTO dto = (NoticeDTO) adapter.getItem(position);
                Toast.makeText(activity, "아이템 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        noticePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeInsert_page.class);
                startActivity(intent);
            }
        });
        */
        return view;
    }
}
