package com.example.saper.views.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.saper.Game;

import java.util.jar.Attributes;

public class Grid extends GridView {
    Grid(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        Game.getInstance().create_grid(context);
        setNumColumns(Game.width);
        setAdapter(new GridAdapter());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Game.getInstance().width * Game.getInstance().height;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return Game.getInstance().getCellAt(position);
        }
    }

}
