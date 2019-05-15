package com.example.hanulproject.task.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hanulproject.MainActivity;
import com.example.hanulproject.R;
import com.example.hanulproject.menu.community.CommunityDetail;
import com.example.hanulproject.menu.community.Community_main;
import com.example.hanulproject.vo.CommunityVO;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class CommunityAdapter extends BaseAdapter {
    Dialog dialog;
    Context context;
    int layout;
    ArrayList<CommunityVO> arrayList;
    LayoutInflater inflater;

    public CommunityAdapter(Context context, int layout, ArrayList<CommunityVO> arrayList) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CommunityViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);

            viewHolder = new CommunityViewHolder();
            //viewHolder.no = convertView.findViewById(R.id.cmno);
            viewHolder.iv_img = convertView.findViewById(R.id.cmtitle);
            //viewHolder.content = convertView.findViewById(R.id.cmcontent);
            viewHolder.writer = convertView.findViewById(R.id.cmwriter);
            //viewHolder.readcnt = convertView.findViewById(R.id.cmreadcnt);
            //viewHolder.filename = convertView.findViewById(R.id.cmfilename);
            //viewHolder.filepath = convertView.findViewById(R.id.cmfilepath);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (CommunityViewHolder) convertView.getTag();
        }
        //viewHolder.no.setText(arrayList.get(position).getNo());
        viewHolder.iv_img.setImageResource(R.drawable.blank);
        viewHolder.writer.setText(arrayList.get(position).getWriter());
        //viewHolder.content.setText(arrayList.get(position).getContent());
        //viewHolder.readcnt.setText(arrayList.get(position).getReadcnt());
        //viewHolder.filename.setText(arrayList.get(position).getFilename());
        //viewHolder.filepath.setText(arrayList.get(position).getFilename());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this.context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();

        ImageLoader.getInstance().init(config);

        //https://iw90.tistory.com/113

        if(arrayList.get(position).getFilename() != null && !(arrayList.get(position).getFilename().equals(""))){
            viewHolder.iv_img.setImageResource(R.drawable.blank);
            ImageLoader.getInstance().displayImage(arrayList.get(position).getFilepath().toString(),
                    viewHolder.iv_img, new ImageLoadingListener() {
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
        return convertView;
    }

    //아이템 지우기
    public void removeAllItem(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public static class CommunityViewHolder{
        public TextView no;
        public ImageView iv_img;
        public TextView content;
        public TextView writer;
        public TextView readcnt;
        public TextView filename;
        public TextView filepath;
        public ProgressBar progressBar;
    }
}
