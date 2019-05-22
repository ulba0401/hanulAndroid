package com.example.hanulproject.menu.status;


import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

public class GetWeather extends AsyncTask<String,Void,String> {
    int nx, ny;

    public GetWeather(int nx, int ny) {
        this.nx = nx;
        this.ny = ny;
    }

    private String time() {
        SimpleDateFormat sdf2 = new SimpleDateFormat("kkmm");
        Date now = new Date();
        int val = Integer.parseInt(sdf2.format(now));
        if (val >= 210 && val <= 510) {
            return "0200";
        } else if (val > 510 && val <= 810) {
            return "0500";
        } else if (val > 810 && val <= 1110) {
            return "0800";
        } else if (val > 1110 && val <= 1410) {
            return "1100";
        } else if (val > 1410 && val <= 2010) {
            return "1400";
        } else if (val > 2010 && val <= 2310) {
            return "1700";
        } else {
            return "2300";
        }
    }


    @Override
   protected String doInBackground(String... voids) {
        BufferedReader br = null;
        String result = "";
        String key = "7yVeHeB4RQMlqi6UOTxYhZ5Px5Est6psR%2F66FQBNsBaaUzE5nV5yU0lEVGqw1s2dQwuI0dIu3T4KMw0cswBaFw%3D%3D";
        try {
            //주소 붙여넣기
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            Date now = new Date();
            String urlstr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData";
            String ServiceKey = "?ServiceKey=" + key;
            String date = "&base_date=" + sdf1.format(now);
            String time = "&base_time=" + time();
            String nx_ny = "&nx=" + nx + "&ny=" + ny;
            String type = "&_type=json";

            String total_url = urlstr + ServiceKey + date + time + nx_ny + type;

            //System.out.println(total_url);
            URL url = new URL(total_url);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

            result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result = result + line + "\n";
            }
            //*   System.out.println(result);*//

            //result
            result = result.substring(result.indexOf("["),result.indexOf("]")+1).trim();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }



}
