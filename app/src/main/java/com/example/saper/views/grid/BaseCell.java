package com.example.saper.views.grid;

import android.content.Context;
import android.view.View;

import com.example.saper.Game;

public abstract class BaseCell extends View {


    private int value;              //wartosc odkrywana 0 lub 1 lub -1
    private boolean is_Bomb;        //sprawdzenie czy jest bomba
    private boolean is_Revealed;    //odkrywanie zer i jedynek przy kliknieciu na button
    private boolean is_Clicked;      //sprawdzenie czy zostala kliknieta bomba
    private boolean is_Flagged;     //Sprawdzenie czy button jest zaflagowany

    private int x,y;                //pozycja na tablicy
    private int position;           //pozycja

    public BaseCell(Context context)
    {
        super(context);

    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        is_Bomb=false;
        is_Clicked=false;
        is_Flagged=false;
        is_Revealed=false;
        if(value==-1)
        {
            is_Bomb=true;
        }
        this.value = value;
    }

    //
    public boolean Is_Bomb() {
        return is_Bomb;
    }

    public void setIs_Bomb(boolean is_Bomb) {
        this.is_Bomb = is_Bomb;
    }

    public boolean Is_Revealed() {
        return is_Revealed;
    }

    public void setIs_Revealed() {

        is_Revealed = true;
        invalidate();
    }

    public boolean Is_Clicked() {
        return is_Clicked;
    }

    public void setIs_Clicked() {
        this.is_Clicked = true;
        this.is_Revealed=true;

        invalidate();
    }

    public boolean Is_Flagged() {
        return is_Flagged;
    }

    public void setIs_Flagged(boolean is_Flagged) {
        this.is_Flagged = is_Flagged;
    }


    public int getXpos() {
        return x;
    }


    public int getYpos() {
        return y;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int x,int y) {
        this.x=x;
        this.y=y;

        this.position = y*Game.width+x;

        invalidate();

    }
}
