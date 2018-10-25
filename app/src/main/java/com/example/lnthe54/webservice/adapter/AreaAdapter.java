package com.example.lnthe54.webservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lnthe54.webservice.R;

/**
 * @author lnthe54 on 10/25/2018
 * @project WebService
 */
public class AreaAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] area;

    public AreaAdapter(Context context, int resourceId, String[] area) {
        super(context, resourceId, area);
        this.context = context;
        this.area = area;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = LayoutInflater.from(context).inflate(R.layout.layout_custom_spinner, parent, false);
        TextView label = row.findViewById(R.id.spinner_textView);
        label.setText(area[position]);

        return row;
    }

}
