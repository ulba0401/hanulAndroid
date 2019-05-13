package com.example.hanulproject.task.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.vo.NoticeVO;

import java.util.ArrayList;

public class StatusAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    /*Dialog dialog;
    Context context;
    int layout;
    ArrayList<NoticeVO> arrayList;
    LayoutInflater inflater;


    public StatusAdapter(Context context, int layout, ArrayList<NoticeVO> arrayList) {
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
        final NoticeAdapter.NoticeViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);

            viewHolder = new NoticeAdapter.NoticeViewHolder();
            viewHolder.no = convertView.findViewById(R.id.nno);
            viewHolder.title = convertView.findViewById(R.id.ntitle);
            viewHolder.content = convertView.findViewById(R.id.ncontent);
            viewHolder.writedate = convertView.findViewById(R.id.nwritedate);
            viewHolder.readcnt = convertView.findViewById(R.id.nreadcnt);
            viewHolder.filename = convertView.findViewById(R.id.nfilename);
            viewHolder.filepath = convertView.findViewById(R.id.nfilepath);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (NoticeAdapter.NoticeViewHolder) convertView.getTag();
        }

        viewHolder.no.setText(arrayList.get(position).getNo());
        viewHolder.title.setText(arrayList.get(position).getTitle());
        viewHolder.content.setText(arrayList.get(position).getContent());
        viewHolder.writedate.setText(arrayList.get(position).getWritedate());
        viewHolder.readcnt.setText(arrayList.get(position).getReadcnt());
        viewHolder.filename.setText(arrayList.get(position).getFilename());
        viewHolder.filepath.setText(arrayList.get(position).getFilename());

        return convertView;
    }

    //아이템 지우기
    public void removeAllItem(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public static class StatusViewHolder{
        public TextView no;
        public TextView title;
        public TextView content;
        public TextView writedate;
        public TextView readcnt;
        public TextView filename;
        public TextView filepath;
    }*/
}
