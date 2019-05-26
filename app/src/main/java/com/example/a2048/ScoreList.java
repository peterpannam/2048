package com.example.a2048;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ScoreList extends ArrayAdapter<String>{

    private final Activity context;
    private final List<String> scores;


    public ScoreList(Activity context,
                      List<String> scores) {
        super(context, R.layout.list_score, scores);
        this.context = context;
        this.scores = scores;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_score, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setText(scores.get(position));
        return rowView;
    }
}

