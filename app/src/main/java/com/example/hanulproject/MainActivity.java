package com.example.hanulproject;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanulproject.login.LoginRequest;
import com.example.hanulproject.login.Login_menu;
import com.example.hanulproject.main.BackPressCloseHandler;
import com.example.hanulproject.menu.Menu_main;
import com.example.hanulproject.menu.community.Community_main;
import com.example.hanulproject.menu.complain.Complain_main;
import com.example.hanulproject.menu.list.List_main;
import com.example.hanulproject.main.First_fragment;
import com.example.hanulproject.main.Second_fragment;
import com.example.hanulproject.menu.notice.Notice_main;
import com.example.hanulproject.menu.settings.Settings_main;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import me.relex.circleindicator.CircleIndicator;

// 커밋테스트
// 현지의 커밋테스트
// 영선의 커밋테스트
// 맏형의 커밋테스트
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FragmentPagerAdapter adapterViewPager;
    private BackPressCloseHandler backPressCloseHandler;
    public TextView email, name;
    ImageView profile;


    Notice_main notice;
    Complain_main complain;
    Community_main community;
    Settings_main settings;
    List_main list;



    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        //백버튼 누르면 종료되는 기능의 함수
        backPressCloseHandler = new BackPressCloseHandler(this);

        //setSupportActionBar : 액션바 설정
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //액션바 기본 타이틀 보여지지 않게
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        //메뉴 나오게 하기
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //로그인 성공시 프로필 보여주기
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View nav_header_view = navigationView.getHeaderView(0);

            profile = nav_header_view.findViewById(R.id.profileImage);
            email = nav_header_view.findViewById(R.id.navi_id);
            name = nav_header_view.findViewById(R.id.navi_name);
            email.setText(LoginRequest.vo.getEmail());
            name.setText(LoginRequest.vo.getName());
            profile.setImageResource(R.mipmap.ic_launcher_round);

            if(LoginRequest.vo.getProfile() != null && !(LoginRequest.vo.getProfile().equals(""))){
                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                        .threadPriority(Thread.NORM_PRIORITY - 2)
                        .denyCacheImageMultipleSizesInMemory()
                        .discCacheFileNameGenerator(new Md5FileNameGenerator())
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .writeDebugLogs() // Remove for release app
                        .build();
                ImageLoader.getInstance().init(config);

                    profile.setVisibility(View.VISIBLE);
                    ImageLoader.getInstance().displayImage(LoginRequest.vo.getProfile(),
                            profile, new ImageLoadingListener() {
                                @Override
                                public void onLoadingStarted(String s, View view) {
//                            viewHolder.progressBar.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onLoadingFailed(String s, View view, FailReason failReason) {
//                            viewHolder.progressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//                            viewHolder.progressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onLoadingCancelled(String s, View view) {
//                            viewHolder.progressBar.setVisibility(View.GONE);
                                }
                            });
            }

        //관리자 모드
        Menu nav_menu = navigationView.getMenu();
        MenuItem menuItem = nav_menu.findItem(R.id.adminmenu);
        //Log.d("adminTest", LoginRequest.vo.getAdmin());
        if(LoginRequest.vo.getAdmin().equals("Y")){
            menuItem.setVisible(true);
        }else {
            menuItem.setVisible(false);
        }





        //슬라이드 화면 설정
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(vpPager);

        checkDangerousPermissions();
    }



    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    //메뉴보이기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




    //네비게이션 메뉴 아이템 선택시
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notice) {
            intent = new Intent(MainActivity.this, Menu_main.class);
            intent.putExtra("selectKey", 1);
            startActivity(intent);
        } else if (id == R.id.nav_complain) {
            intent = new Intent(MainActivity.this, Menu_main.class);
            intent.putExtra("selectKey", 2);
            startActivity(intent);
        } else if (id == R.id.nav_community) {
            intent = new Intent(MainActivity.this, Menu_main.class);
            intent.putExtra("selectKey", 3);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            intent = new Intent(MainActivity.this, Menu_main.class);
            intent.putExtra("selectKey", 4);
            startActivity(intent);
        } else if (id == R.id.nav_list) {
            intent = new Intent(MainActivity.this, Menu_main.class);
            intent.putExtra("selectKey", 5);
            startActivity(intent);
        } else if(id == R.id.nav_logout){
            intent = new Intent(MainActivity.this, Login_menu.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //슬라이드 화면
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return First_fragment.newInstance(0, "Page # 1");
                case 1:
                    return Second_fragment.newInstance(1, "Page # 2");
                default:
                    return null;
            }
        }
        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
