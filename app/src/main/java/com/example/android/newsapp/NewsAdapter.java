package com.example.android.newsapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, ArrayList<News> news){
        super(context, 0, news);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final News currentItem = getItem(position);

        TextView sectionTextView = listItemView.findViewById(R.id.section_text_view);
        sectionTextView.setText(currentItem.getSection());

        TextView titleTextView = listItemView.findViewById(R.id.title_text_view);
        titleTextView.setText(currentItem.getTitle());

        TextView timeTextView = listItemView.findViewById(R.id.time_text_view);
        timeTextView.setText(currentItem.getTime());

        TextView authorTextView = listItemView.findViewById(R.id.author_text_view);
        authorTextView.setText(currentItem.getAuthor());

        return listItemView;
    }

}

