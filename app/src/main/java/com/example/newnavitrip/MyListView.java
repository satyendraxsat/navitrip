package com.example.newnavitrip;

import android.app.Activity;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> subtitle;
    private final ArrayList<String> imgid;

    public MyListAdapter(Activity context, ArrayList<String> maintitle, ArrayList<String> subtitle, ArrayList<String> imgid) {
        super(context, R.layout.mylist, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid=imgid;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(maintitle.get(position));
//        imageView.setImageResource(imgid[position]);
//        imageView.setImageResource(imgid);
        subtitleText.setText(subtitle.get(position));

        return rowView;

    };
}
