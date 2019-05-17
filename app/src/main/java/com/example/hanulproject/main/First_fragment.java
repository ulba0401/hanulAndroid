package com.example.hanulproject.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.example.hanulproject.menu.status.GeoPoint;
import com.example.hanulproject.menu.status.GeoTrans;
import com.example.hanulproject.menu.status.GetDust;
import com.example.hanulproject.menu.status.GetWeather;
import com.example.hanulproject.menu.status.TranslateXY;
import com.example.hanulproject.task.task.ReadMessage;
import com.example.hanulproject.vo.DustInfoVO;
import com.example.hanulproject.vo.DustStationVO;
import com.example.hanulproject.vo.MyhomeVO;
import com.example.hanulproject.vo.TranslatexyVO;
import com.example.hanulproject.vo.WeatherInfoVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class First_fragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    MainActivity activity;
    ListView my_home_list;
    ImageView weather_back, finedust, ufinedust, weather_icon;
    TextView temp, tmn, tmx, weather_title, udvalue, dvalue;
    String weather = "";
    ReadMessage rm = new ReadMessage();
    TranslatexyVO tvo;
    ArrayList<DustInfoVO> dustinfolist = new ArrayList<>();

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

        //위경도 기상청용 xy로 변환
        TranslateXY txy = new TranslateXY();
        TranslatexyVO tvo = txy.getTransXY(33.380799580047004, 126.54054857471584);
        WeatherInfoVO info = new WeatherInfoVO();


//        //GeoTrans 클래스 사용 예시
//        GeoPoint in_pt = new GeoPoint(127., 38.);
//        System.out.println("geo in : xGeo="  + in_pt.getX() + ", yGeo=" + in_pt.getY());
//        GeoPoint tm_pt = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, in_pt);
//        System.out.println("tm : xTM=" + tm_pt.getX() + ", yTM=" + tm_pt.getY());
//        GeoPoint katec_pt = GeoTrans.convert(GeoTrans.TM, GeoTrans.KATEC, tm_pt);
//        System.out.println("katec : xKATEC=" + katec_pt.getX() + ", yKATEC=" + katec_pt.getY());
//        GeoPoint out_pt = GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, katec_pt);
//        System.out.println("geo out : xGeo=" + out_pt.getX() + ", yGeo=" + out_pt.getY());
//        GeoPoint in2_pt = new GeoPoint(128., 38.);
//        System.out.println("geo distance between (127,38) and (128,38) =" + GeoTrans.getDistancebyGeo(in_pt, in2_pt) + "km");

        my_home_list = view.findViewById(R.id.my_home_list);
        temp = view.findViewById(R.id.nowtemp);
        finedust = view.findViewById(R.id.finedust);
        ufinedust = view.findViewById(R.id.ufinedust);
        tmn = view.findViewById(R.id.tmn);
        tmx = view.findViewById(R.id.tmx);
        weather_title = view.findViewById(R.id.weather_title);
        weather_back = view.findViewById(R.id.weather_back);
        weather_icon = view.findViewById(R.id.weather_icon);
        dvalue = view.findViewById(R.id.dvalue);
        udvalue = view.findViewById(R.id.udvalue);

        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(weather_back);

        //미세먼지 근처 3군데 3시간 평균값 리스트 받아오기
        GetDust dust = null;
        dust = new GetDust(33.380799580047004, 126.54054857471584);

        try {
            dustinfolist = dust.execute().get();

            for (int i = 0; i < dustinfolist.size(); i++){

                int a = dustinfolist.get(i).getPm10Value();
                if (dustinfolist.get(i).getPm10Grade() != 0) {
                    if (dustinfolist.get(i).getPm10Grade() == 1) {
                        finedust.setImageResource(R.drawable.verygood);
                        dvalue.setText(String.valueOf(dustinfolist.get(i).getPm10Value()));
                        break;
                    } else if (dustinfolist.get(i).getPm10Grade() == 2) {
                        finedust.setImageResource(R.drawable.soso);
                        dvalue.setText(String.valueOf(dustinfolist.get(i).getPm10Value()));
                        break;
                    } else if (dustinfolist.get(i).getPm10Grade() == 3) {
                        finedust.setImageResource(R.drawable.bad);
                        dvalue.setText(String.valueOf(dustinfolist.get(i).getPm10Value()));
                        break;
                    } else if (dustinfolist.get(i).getPm10Grade() == 4) {
                        finedust.setImageResource(R.drawable.verybad);
                        dvalue.setText(String.valueOf(dustinfolist.get(i).getPm10Value()));
                        break;
                    }
                }
            }


            for (int i = 0; i < dustinfolist.size(); i++){

                int a = dustinfolist.get(i).getPm25Value();
                if (dustinfolist.get(i).getPm25Grade() != 0) {
                    if (dustinfolist.get(i).getPm25Grade() == 1) {
                        ufinedust.setImageResource(R.drawable.verygood);
                        udvalue.setText(String.valueOf(dustinfolist.get(i).getPm25Value()));
                        break;
                    } else if (dustinfolist.get(i).getPm25Grade() == 2) {
                        ufinedust.setImageResource(R.drawable.soso);
                        udvalue.setText(String.valueOf(dustinfolist.get(i).getPm25Value()));
                        break;
                    } else if (dustinfolist.get(i).getPm25Grade() == 3) {
                        ufinedust.setImageResource(R.drawable.bad);
                        udvalue.setText(String.valueOf(dustinfolist.get(i).getPm25Value()));
                        break;
                    } else if (dustinfolist.get(i).getPm25Grade() == 4) {
                        ufinedust.setImageResource(R.drawable.verybad);
                        udvalue.setText(String.valueOf(dustinfolist.get(i).getPm25Value()));
                        break;
                    }
                }
            }
        }catch (Exception e ){
            e.getMessage();
        }



        //날씨받아오기
        GetWeather gw = new GetWeather(tvo.getX(), tvo.getY());
        try {
            weather = gw.execute().get().toString();
            int fcstValue = 0;
            JSONArray jsonArray = new JSONArray(weather);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                String category = (String) row.getString("category");

                if (category.equals("SKY")) {
                    fcstValue = row.getInt("fcstValue");
                    info.setSky(String.valueOf(fcstValue));
                } else if (category.equals("T3H")) {
                    fcstValue = row.getInt("fcstValue");
                    info.setT3h(String.valueOf(fcstValue));
                } else if (category.equals("TMN")) {
                    fcstValue = row.getInt("fcstValue");
                    info.setTmn(String.valueOf(fcstValue));
                } else if (category.equals("TMX")) {
                    fcstValue = row.getInt("fcstValue");
                    info.setTmx(String.valueOf(fcstValue));
                } else if (category.equals("PTY")) {
                    fcstValue = row.getInt("fcstValue");
                    info.setPty(String.valueOf(fcstValue));
                }

            }

            //SKT 값에 따른 그림 설정
            if (Integer.parseInt(info.getPty()) == 0) {
                switch (Integer.parseInt(info.getSky())) {

                    case 1:
                        Glide.with(this).load(R.drawable.littlecloud).into(gifImage);
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
            } else if (Integer.parseInt(info.getPty()) == 1) {
                Glide.with(this).load(R.drawable.rain).into(gifImage);
                weather_title.setText("비");
                weather_icon.setImageResource(R.drawable.rain_icon);

            } else if (Integer.parseInt(info.getPty()) == 2) {
                Glide.with(this).load(R.drawable.snow).into(gifImage);
                weather_title.setText("눈/비");
                weather_icon.setImageResource(R.drawable.snowandrain_icon);
            } else if (Integer.parseInt(info.getPty()) == 3) {
                Glide.with(this).load(R.drawable.snow).into(gifImage);
                weather_title.setText("눈");
                weather_icon.setImageResource(R.drawable.snow_icon);
            }


            //현재기온
            temp.setText(String.valueOf(Integer.parseInt(info.getT3h())) + " ℃");

            //최저기온
            if (info.getTmn() != null) {
                tmn.setText(String.valueOf(Integer.parseInt(info.getTmn())) + " ℃");
            } else {
                tmn.setText("-");
            }

            //최고기온
            if (info.getTmx() != null) {
                tmx.setText(String.valueOf(Integer.parseInt(info.getTmx())) + " ℃");
            } else {
                tmx.setText("-");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }


    //집 리스트 조회처리

}