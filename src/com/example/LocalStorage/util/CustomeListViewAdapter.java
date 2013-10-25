package com.example.LocalStorage.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.LocalStorage.R;
import com.example.LocalStorage.model.Student;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/22/13
 * Time: 8:31 AM
 * To change this template use FileActivity | Settings | FileActivity Templates.
 */
public class CustomeListViewAdapter extends BaseAdapter
{
    private List<Student> listData;
    private LayoutInflater layoutInflater;

    public List<Student> getListData()
    {
        return listData;
    }

    public CustomeListViewAdapter(Context context, List<Student> listItems)
    {
        this.listData = listItems;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
    return listData.size();
    }

    @Override
    public Object getItem(int position)
    {
       return listData.get(position);
    }
    public void deleteStudent(int index){
        System.out.println(listData.get(index));
        listData.remove(index);
    }

    public void setListData(List<Student> listData)
    {
        this.listData = listData;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.custome_listview_row, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.main_tvRealName);
            holder.age = (TextView) convertView.findViewById(R.id.main_tvRealAge);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(listData.get(position).get_name());
        holder.age.setText(listData.get(position).get_age());

        return convertView;

    }

    private class ViewHolder
    {
        TextView name;
        TextView age;
    }
}
