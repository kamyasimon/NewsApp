package com.hipipo.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> News) {
        super(context, 0, News);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.mininewslist, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        News currentNews = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView newsTitleTextView = (TextView) listItemView.findViewById(R.id.webTitle);
        newsTitleTextView.setText(currentNews.getnewsTopic());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView newsDateTextView = (TextView) listItemView.findViewById(R.id.webPublicationDate);
        newsDateTextView.setText(currentNews.getnewsPublicationDate());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView newsSectionTextView = (TextView) listItemView.findViewById(R.id.sectionName);
        newsSectionTextView.setText(currentNews.getNewsSection());



        return listItemView;
    }
}
