package com.example.saper.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.saper.Game;
import com.example.saper.R;

public class Cell extends BaseCell implements View.OnClickListener, View.OnLongClickListener {

    public Cell(Context context, int x, int y)
    {
        super(context);
        setPosition(x,y);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        Game.getInstance().click(getXpos(),getYpos());
    }
    @Override
    public boolean onLongClick(View v) {
        Game.getInstance().flag(getXpos(),getYpos());
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton(canvas);
        if(Is_Flagged())
        {
            drawFlag(canvas);
        }
        else if(Is_Revealed() && Is_Bomb() && Is_Clicked())
        {
            drawBombExploded(canvas);
        }
        else
        {
            if(Is_Revealed())
            {
                if(getValue()==-1)
                {
                    drawBomb(canvas);
                }
                else
                {
                    drawNumbers(canvas);
                }
            }
            else
            {
                drawButton(canvas);
            }
        }
    }

    private void drawFlag(Canvas canvas)
    {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void drawBombExploded(Canvas canvas)
    {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void drawButton(Canvas canvas)
    {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawBomb(Canvas canvas)
    {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
    private void drawNumbers(Canvas canvas)
    {
        Drawable drawable =null;

        switch(getValue())
        {
            case 0:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number_8);
                break;
        }
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }



}
