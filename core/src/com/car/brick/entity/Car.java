package com.car.brick.entity;


import com.badlogic.gdx.math.Rectangle;

/**
 * Created by bikash on 12/30/17.
 */

public class Car extends Rectangle {
    int speedX,speedY;

    public Car(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public Car(float x, float y, float width, float height, int speedX, int speedY) {
        super(x, y, width, height);
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public Car(Rectangle rect, int speedX, int speedY) {
        super(rect);
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void move(){
        this.y+=this.speedY;
    }
    public void move(int y){
        this.y=this.y+this.speedY+y;
    }

}
