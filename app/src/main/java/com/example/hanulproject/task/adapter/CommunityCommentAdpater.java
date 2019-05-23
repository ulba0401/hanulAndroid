package com.example.hanulproject.task.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.menu.community.CommunityDetail;
import com.example.hanulproject.menu.community.Community_commentDelete;
import com.example.hanulproject.menu.community.Community_commentSelect;
import com.example.hanulproject.menu.community.Community_commentUpdate;
import com.example.hanulproject.menu.community.Community_commentVO;
import com.example.hanulproject.menu.community.Last;
import com.example.hanulproject.task.task.Delete;

import java.util.ArrayList;

public class CommunityCommentAdpater extends BaseAdapter {

    CommunityCommentAdpater adpater = this;
    Community_commentSelect select;
    Context context;
    int layout;
    ArrayList<Community_commentVO> arrayList;
    LayoutInflater inflater;

    CommunityDetail communityDetail;

    public void upDateItemList(ArrayList<Community_commentVO> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public CommunityCommentAdpater(Context context, int layout, ArrayList<Community_commentVO> arrayList){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
        this.layout = layout;
    }

    public void setCommunity_commentSelect(Community_commentSelect community_commentSelect){
        this.select = community_commentSelect;
    }

    public void setCommunityDetail(CommunityDetail communityDetail){
        this.communityDetail = communityDetail;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.content = convertView.findViewById(R.id.cmi_content);
            viewHolder.writer = convertView.findViewById(R.id.cmi_writer);
            viewHolder.modify = convertView.findViewById(R.id.cmi_modify);
            viewHolder.delete = convertView.findViewById(R.id.cmi_delete);
            viewHolder.modifyContent = convertView.findViewById(R.id.cmi_modify_content);
            viewHolder.modify_insert = convertView.findViewById(R.id.cmi_modify_insert);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.writer.setText(arrayList.get(position).getWriter());
        viewHolder.content.setText(arrayList.get(position).getContent());
        viewHolder.modifyContent.setText(arrayList.get(position).getContent());

        viewHolder.modifyContent.setVisibility(View.GONE);
        viewHolder.modify_insert.setVisibility(View.GONE);

        viewHolder.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.content.setVisibility(View.GONE);
                viewHolder.modify.setVisibility(View.GONE);
                viewHolder.modifyContent.setVisibility(View.VISIBLE);
                viewHolder.modify_insert.setVisibility(View.VISIBLE);

            }
        });

        viewHolder.modify_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.modifyContent.setVisibility(View.GONE);
                viewHolder.modify_insert.setVisibility(View.GONE);
                Community_commentUpdate community_commentUpdate = new Community_commentUpdate(arrayList.get(position).getNo(),viewHolder.modifyContent.getText().toString(), adpater);
                community_commentUpdate.execute();
                viewHolder.content.setVisibility(View.VISIBLE);
                viewHolder.modify.setVisibility(View.VISIBLE);
                removeAllItem();
                communityDetail.refresh();

//                Intent intent = new Intent(CommunityDetail.contextDetail, Last.class);
//                CommunityDetail.contextDetail.startActivity(intent);

            }
        });

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(communityDetail);
                builder.setTitle("삭제여부");
                builder.setMessage("정말 삭제하시겠습니까?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Community_commentDelete community_commentDelete = new Community_commentDelete(arrayList.get(position).getNo(),adpater);
                        community_commentDelete.execute();
                        removeAllItem();
                        communityDetail.refresh();

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();



//                Intent intent = new Intent(CommunityDetail.contextDetail, Last.class);
//                CommunityDetail.contextDetail.startActivity(intent);
            }
        });

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
        EditText modifyContent;
        Button modify;
        Button delete;
        Button modify_insert;
    }
}
