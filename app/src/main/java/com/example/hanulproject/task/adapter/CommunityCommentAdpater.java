package com.example.hanulproject.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.menu.community.Community_commentVO;

import java.util.ArrayList;

public class CommunityCommentAdpater extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Community_commentVO> arrayList;
    LayoutInflater inflater;

    public CommunityCommentAdpater(Context context, int layout, ArrayList<Community_commentVO> arrayList){
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
        final ViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.content = convertView.findViewById(R.id.cmi_content);
            viewHolder.writer = convertView.findViewById(R.id.cmi_writer);
            viewHolder.modify = convertView.findViewById(R.id.cmi_modify);
            viewHolder.delete = convertView.findViewById(R.id.cmi_delete);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.content.setText(arrayList.get(position).getContent());
        viewHolder.writer.setText(arrayList.get(position).getWriter());

        return convertView;
    }

    //아이템 지우기
    public void removeAllItem(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    private static class ViewHolder{
        TextView writer;
        TextView content;
        Button modify;
        Button delete;
    }
}
