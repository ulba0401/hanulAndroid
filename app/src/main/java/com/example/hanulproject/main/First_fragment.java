package com.example.hanulproject.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.hanulproject.MainActivity;
import com.example.hanulproject.R;
import com.example.hanulproject.menu.status.GetWeather;
import com.example.hanulproject.menu.status.TranslateXY;
import com.example.hanulproject.task.task.ReadMessage;
import com.example.hanulproject.vo.TranslatexyVO;
import com.example.hanulproject.vo.WeatherInfoVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class First_fragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    MainActivity activity;
    ListView my_home_list;
    ImageView weather_back, finedust, ufinedust, weather_icon;
    TextView temp, tmn, tmx, weather_title;
    String a = "";
    ReadMessage rm = new ReadMessage();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment1, container, false);

        String category;
        int fcstValue;

        //위경도 기상청용 xy로 변환
        TranslateXY txy = new TranslateXY();
        TranslatexyVO tvo = txy.getTransXY(35.154981035369644, 126.89330053179164);
        WeatherInfoVO info = new WeatherInfoVO();

        my_home_list = view.findViewById(R.id.my_home_list);
        temp = view.findViewById(R.id.nowtemp);
        finedust = view.findViewById(R.id.finedust);
        ufinedust = view.findViewById(R.id.ufinedust);
        tmn = view.findViewById(R.id.tmn);
        tmx = view.findViewById(R.id.tmx);
        weather_title = view.findViewById(R.id.weather_title);
        weather_back = view.findViewById(R.id.weather_back);
        weather_icon = view.findViewById(R.id.weather_icon);

        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(weather_back);

        //날씨받아오기
        GetWeather gw = new GetWeather(tvo.getX(), tvo.getY());
        try {
            a = gw.execute().get().toString();

            JSONArray jsonArray = new JSONArray(a);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject row = jsonArray.getJSONObject(i);
                category = (String) row.getString("category");


                if (category.equals("SKY")){
                    fcstValue = row.getInt("fcstValue");
                    info.setSky(String.valueOf(fcstValue));
                } else if (category.equals("T3H")){
                    fcstValue = row.getInt("fcstValue");
                    info.setT3h(String.valueOf(fcstValue));
                } else if (category.equals("TMN")){
                    fcstValue = row.getInt("fcstValue");
                    info.setTmn(String.valueOf(fcstValue));
                } else if (category.equals("TMX")){
                    fcstValue = row.getInt("fcstValue");
                    info.setTmx(String.valueOf(fcstValue));
                } else if (category.equals("PTY")){
                    fcstValue = row.getInt("fcstValue");
                    info.setPty(String.valueOf(fcstValue));
                }

            }

            //SKT 값에 따른 그림 설정
            if (Integer.parseInt(info.getPty()) == 0){
                switch (Integer.parseInt(info.getSky())) {

                    case 1:
                        Glide.with(this).load(R.drawable.sunny).into(gifImage);
                        weather_title.setText("맑음");
                        weather_icon.setImageResource(R.drawable.sunny_icon);
                    case 2:
                        Glide.with(this).load(R.drawable.littlecloud).into(gifImage);
                        weather_title.setText("구름조금");
                        weather_icon.setImageResource(R.drawable.littlecloud_icon);
                    case 3:
                        Glide.with(this).load(R.drawable.manycloud).into(gifImage);
                        weather_title.setText("구름많음");
                        weather_icon.setImageResource(R.drawable.littlecloud_icon);
                    case 4:
                        Glide.with(this).load(R.drawable.manymanycloud).into(gifImage);
                        weather_title.setText("흐림");
                        weather_icon.setImageResource(R.drawable.cloud_icon);
                }
            }else if (Integer.parseInt(info.getPty()) == 1){
                Glide.with(this).load(R.drawable.rain).into(gifImage);
                weather_title.setText("비");
                weather_icon.setImageResource(R.drawable.rain_icon);

            }else if (Integer.parseInt(info.getPty()) == 2){
                Glide.with(this).load(R.drawable.snow).into(gifImage);
                weather_title.setText("눈/비");
                weather_icon.setImageResource(R.drawable.snowandrain_icon);
            }else if (Integer.parseInt(info.getPty()) == 3){
                Glide.with(this).load(R.drawable.snow).into(gifImage);
                weather_title.setText("눈");
                weather_icon.setImageResource(R.drawable.snow_icon);
            }


            //현재기온
            temp.setText(String.valueOf(Integer.parseInt(info.getT3h())) + " ℃");

            //최저기온
            if(info.getTmn() != null) {
                tmn.setText(String.valueOf(Integer.parseInt(info.getTmn())) + " ℃");
            }else {
                tmn.setText("-");
            }

            //최고기온
            if(info.getTmx() != null) {
                tmx.setText(String.valueOf(Integer.parseInt(info.getTmx())) + " ℃");
            }else {
                tmx.setText("-");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }


}


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