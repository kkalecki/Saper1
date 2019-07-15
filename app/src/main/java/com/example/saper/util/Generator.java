package com.example.saper.util;

import java.util.Random;

public class Generator {
    public static int[][] generate_map(int bomb_number,int width,int height)
    {
        Random random= new Random();   // do generowania gdzie beda bomby

        int grid[][]=new int [width][height];   //mapa

        for(int i=0; i<width; i++)  //?
        {
            grid[i] = new int[height];
        }

        while(bomb_number>0)    //wstawianie bomb na mape
        {
            int x=random.nextInt(width);
            int y=random.nextInt(height);

            if(grid[x][y] != -1)
            {
                grid[x][y]= -1;
                bomb_number--;
            }
        }
        grid= neighbours(grid,width,height);
        return grid;

    }
    public static int[][] neighbours(int grid[][], int width, int height)
    {
        for(int i=0;i<width;i++)
        {
            for(int j=0;j<height;j++)
            {
                grid[i][j] = getnumber(grid,width,height,i,j);   //przypisuje ilosc bomb wokol pola
            }
        }
        return grid;
    }
    public static int getnumber(int grid[][],int width,int height,int x,int y)   //sprawdza ilosc bomb wokol pola
    {
        int count =0;

        if(grid[x][y] == -1)
        {
            return -1;
        }

        if(check(grid,x-1,y-1,width,height)) count++;
        if(check(grid,x,y-1,width,height)) count++;
        if(check(grid,x+1,y-1,width,height)) count++;
        if(check(grid,x-1,y,width,height)) count++;
        if(check(grid,x+1,y,width,height)) count++;
        if(check(grid,x-1,y+1,width,height)) count++;
        if(check(grid,x,y+1,width,height)) count++;
        if(check(grid,x+1,y+1,width,height)) count++;

        return count;
    }
    private static boolean check(int grid[][],int x,int y,int width,int height)
    {
        if(x>=0 && y>=0 && x<width && y<height)
        {
            if(grid[x][y] == -1)
            {
                return true;
            }
        }
        return false;
    }
}
