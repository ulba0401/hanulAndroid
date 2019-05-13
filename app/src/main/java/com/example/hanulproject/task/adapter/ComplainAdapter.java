package com.example.hanulproject.task.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.vo.ComplainVO;

import java.util.ArrayList;

public class ComplainAdapter extends BaseAdapter {
    Dialog dialog;
    Context context;
    int layout;
    ArrayList<ComplainVO> arrayList;
    LayoutInflater inflater;


    public ComplainAdapter(Context context, int layout, ArrayList<ComplainVO> arrayList) {
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
        final ComplainAdapter.ComplainViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);

            viewHolder = new ComplainAdapter.ComplainViewHolder();
            viewHolder.title = convertView.findViewById(R.id.cptitle);
            viewHolder.writer = convertView.findViewById(R.id.cpwriter);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ComplainAdapter.ComplainViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(arrayList.get(position).getTitle());
        viewHolder.writer.setText(arrayList.get(position).getWriter());

        return convertView;
    }

    //아이템 지우기
    public void removeAllItem(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public static class ComplainViewHolder{
        //public TextView no;
        public TextView title;
        //public TextView content;
        public TextView writer;
       /* public TextView admin;
        public TextView filename;
        public TextView filepath;*/
    }
}

