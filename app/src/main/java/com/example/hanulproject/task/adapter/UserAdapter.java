package com.example.hanulproject.task.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hanulproject.R;
import com.example.hanulproject.vo.UserVO;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Dialog dialog;
    Context context;
    int layout;
    ArrayList<UserVO> arrayList;
    LayoutInflater inflater;


    public UserAdapter(Context context, int layout, ArrayList<UserVO> arrayList) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList ;
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
        final UserAdapter.MyProfileViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);

            viewHolder = new UserAdapter.MyProfileViewHolder();
            viewHolder.id = convertView.findViewById(R.id.uid);
            /*viewHolder.pw = convertView.findViewById(R.id.upw);*/
            viewHolder.name = convertView.findViewById(R.id.uname);
            /*viewHolder.addr = convertView.findViewById(R.id.uaddr);
            viewHolder.phone = convertView.findViewById(R.id.uphone);
            viewHolder.email = convertView.findViewById(R.id.uemail);
            viewHolder.profile = convertView.findViewById(R.id.uprofile);
            viewHolder.admin = convertView.findViewById(R.id.uadmin);
*/

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (UserAdapter.MyProfileViewHolder) convertView.getTag();
        }

        viewHolder.id.setText(arrayList.get(position).getId());
        /*viewHolder.pw.setText(arrayList.get(position).getPw());*/
        viewHolder.name.setText(arrayList.get(position).getName());
       /* viewHolder.addr.setText(arrayList.get(position).getAddr());
        viewHolder.phone.setText(arrayList.get(position).getPhone());
        viewHolder.email.setText(arrayList.get(position).getEmail());
        viewHolder.profile.setText(arrayList.get(position).getProfile());
        viewHolder.admin.setText(arrayList.get(position).getAdmin());*/

        return convertView;
    }

    //아이템 지우기
    public void removeAllItem(){
        arrayList.clear();
        notifyDataSetChanged();
    }

    public static class MyProfileViewHolder{
        public TextView id;
        public TextView pw;
        public TextView name;
        public TextView addr;
        public TextView phone;
        public TextView email;
        public TextView profile;
        public TextView admin;
    }
}
