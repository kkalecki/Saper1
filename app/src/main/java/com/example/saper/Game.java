package com.example.saper;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.saper.util.Generator;
import com.example.saper.views.grid.Cell;

public class Game
{
    public static final int bomb_number=10;
    public static final int width=10;
    public static final int height=15;
    private static Game instance;
    private Context context;
    private Cell[][] minesweeper_grid = new Cell[width][height];

    public static Game getInstance(){
        if(instance==null)
        {
            instance=new Game();
        }
        return instance;
    }

    private Game()
    { }
    public void create_grid(Context context)
    {
        this.context=context;

        //tworzenie planszy

        int ready_grid[][]= Generator.generate_map(bomb_number,width,height); //gotowa plansza
        set_grid(context,ready_grid);
    }

    public Cell getCellAt(int position) {
        int x=position % width;
        int y=position / width;

        return minesweeper_grid[x][y];
    }

    private void set_grid(final Context context, final int [][] grid)
    {
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                if(minesweeper_grid[i][j]==null)
                {
                    minesweeper_grid[i][j]=new Cell(context,i,j);
                }
                minesweeper_grid[i][j].setValue(grid[i][j]);
                minesweeper_grid[i][j].invalidate();
            }
        }
    }
    public Cell getCellAt(int x,int y)
    {
        return minesweeper_grid[x][y];
    }

    public void click(int x, int y) {
        if(x>=0 && y>=0 && x<width && y<height && !getCellAt(x,y).Is_Clicked())
        {
            getCellAt(x,y).setIs_Clicked();

            if(getCellAt(x,y).getValue()==0)
            {
                for(int i=-1;i<=1;i++)
                {
                    for(int j=-1;j<=1;j++)
                    {
                        if(i!=j)
                        {
                            click(x+i,y+j);
                        }
                    }
                }
            }
            if(getCellAt(x,y).Is_Bomb())
            {
                onGamelost();
            }
        }
        checkEnd();


    }

    private boolean checkEnd()
    {
        int bombnotfound = bomb_number;
        int notRevealed = width*height;
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                if(getCellAt(i,j).Is_Revealed() || getCellAt(i,j).Is_Flagged())
                {
                    notRevealed--;
                }
                if(getCellAt(i,j).Is_Flagged() && getCellAt(i,j).Is_Bomb())
                {
                    bombnotfound--;
                }
            }
        }
        if(bombnotfound==0 && notRevealed==0)
        {
            Toast.makeText(context,"You won, congratulation!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void onGamelost()
    {
        Toast.makeText(context,"You lost!", Toast.LENGTH_SHORT).show();

        for(int i =0;i <width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if(getCellAt(i,j).getValue()==-1)
                {
                    getCellAt(i,j).setIs_Revealed();
                }
            }
        }
    }



    public void flag(int xpos, int ypos)
    {
        boolean isFlagged = getCellAt(xpos,ypos).Is_Flagged();
        getCellAt(xpos,ypos).setIs_Flagged(!isFlagged);
        getCellAt(xpos,ypos).invalidate();
    }
}
