package com.a1kesamose.bunksta.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a1kesamose.bunksta.R;

public class NavigationDrawerAdapter extends BaseAdapter
{
    private Context context;
    private LayoutInflater inflater;
    private String[] drawerItems;
    private int selectedPosition;

    public NavigationDrawerAdapter(Context context, String[] drawerItems, int selectedPosition)
    {
        this.context = context;
        this.drawerItems = drawerItems;
        this.selectedPosition = selectedPosition;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return drawerItems.length;
    }

    @Override
    public Object getItem(int position)
    {
        return drawerItems[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        view = inflater.inflate(R.layout.list_item_navigation_drawer, viewGroup, false);
        View viewIndicator = view.findViewById(R.id.list_item_navigation_drawer_indicator);
        TextView textViewDrawerItem = (TextView)view.findViewById(R.id.list_item_navigation_drawer_textView);

        if(position == selectedPosition)
        {
            viewIndicator.setBackgroundColor(Color.CYAN);
        }
        else
        {
            viewIndicator.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
        textViewDrawerItem.setText(drawerItems[position]);

        return view;
    }

    public void selectItem(int position)
    {
        selectedPosition = position;
    }
}
