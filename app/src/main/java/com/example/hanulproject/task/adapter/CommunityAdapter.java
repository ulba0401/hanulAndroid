package com.example.hanulproject.task.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.vo.CommunityVO;

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
            viewHolder.title = convertView.findViewById(R.id.cmtitle);
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
        viewHolder.title.setImageResource(R.drawable.com_facebook_button_icon);
        viewHolder.writer.setText(arrayList.get(position).getWriter());
        //viewHolder.content.setText(arrayList.get(position).getContent());
        //viewHolder.readcnt.setText(arrayList.get(position).getReadcnt());
        //viewHolder.filename.setText(arrayList.get(position).getFilename());
        //viewHolder.filepath.setText(arrayList.get(position).getFilename());

        return convertView;
    }

    //아이템 지우기
    public void removeAllItem(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public static class CommunityViewHolder{
        public TextView no;
        public ImageView title;
        public TextView content;
        public TextView writer;
        public TextView readcnt;
        public TextView filename;
        public TextView filepath;
    }
}
