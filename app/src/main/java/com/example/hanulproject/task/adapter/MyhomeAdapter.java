package com.example.hanulproject.task.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.vo.MyhomeVO;

import java.util.ArrayList;

public class MyhomeAdapter extends BaseAdapter {

    Dialog dialog;
    Context context;
    int layout;
    ArrayList<MyhomeVO> arrayList;
    LayoutInflater inflater;


    public MyhomeAdapter(Context context, int layout, ArrayList<MyhomeVO> arrayList) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        this.arrayList = arrayList;
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
        final MyhomeViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);

            viewHolder = new MyhomeViewHolder();
            viewHolder.addr = convertView.findViewById(R.id.my_home_menu);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MyhomeViewHolder) convertView.getTag();
        }

        return null;
    }

    public static class MyhomeViewHolder{
        public TextView addr;
    }
}
