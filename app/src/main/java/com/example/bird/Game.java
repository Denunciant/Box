package com.example.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class Game extends View {
  //  private Bitmap box;
    private Bitmap background;

    private Paint score = new Paint();
    private Paint level = new Paint();

    private Bitmap life[] = new Bitmap[2];

    private int canvasW;
    private int canvasH;

    private Bitmap box[] = new Bitmap[2];
    private int boxX = 10;
    private int boxY;
    private int boxS;

    private boolean touchF = false;


    public Game(Context context) {
        super(context);

        box[0] = BitmapFactory.decodeResource(getResources(),R.drawable.box_right );
        box[1] = BitmapFactory.decodeResource(getResources(),R.drawable.box_right );

        score.setColor(Color.BLUE);
        score.setTextSize(40);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_g);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        level.setColor(Color.BLACK);
        level.setTextSize(40);
        level.setTypeface(Typeface.DEFAULT_BOLD);
        level.setTextAlign(Paint.Align.CENTER);

        boxY = 500;
        //score = 0;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvasW = canvas.getWidth();
        canvasH = canvas.getHeight();
        canvas.drawBitmap(background,0,0,null);

        int minBoxy = box[0].getHeight();
        int maxBoxy = canvasH - box[0].getHeight()* 3;
        boxY += boxS;
        if (boxY < minBoxy){
           boxY = minBoxy;
        }
        if (boxY > maxBoxy){
            boxY = maxBoxy;
        }

        boxS += 2;
        if (touchF) {
            canvas.drawBitmap(box[1],boxX,boxY, null );
            touchF = false;
        }else{
            canvas.drawBitmap(box[0], boxX, boxY, null);
        }


       // canvas.drawBitmap(box,0,0,null);

        canvas.drawText("Your score: 0",20,67,score);
        canvas.drawText("Level: 1", canvasW / 2, 67 ,level);
        canvas.drawBitmap(life[0], 830, 30, null);
        canvas.drawBitmap(life[0], 890, 30, null);
        canvas.drawBitmap(life[1], 950, 30, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            touchF = true;
            boxS = -20;
        }
        return true;
    }
}
