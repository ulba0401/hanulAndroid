package com.example.hanulproject.task.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.vo.NoticeVO;

import java.util.ArrayList;

public class NoticeAdapter extends BaseAdapter {

    Dialog dialog;
    Context context;
    int layout;
    ArrayList<NoticeVO> arrayList;
    LayoutInflater inflater;

    public NoticeAdapter(Context context, int layout, ArrayList<NoticeVO> arrayList) {
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
        final NoticeViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);

            viewHolder = new NoticeViewHolder();
            viewHolder.title = convertView.findViewById(R.id.ntitle);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (NoticeViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(arrayList.get(position).getTitle());
        return convertView;
    }

    //아이템 지우기
    public void removeAllItem(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public static class NoticeViewHolder{
        //public TextView no;
        public TextView title;
        //public TextView content;
        //public TextView writedate;
        /*public TextView readcnt;
        public TextView filename;
        public TextView filepath;*/
    }

}
