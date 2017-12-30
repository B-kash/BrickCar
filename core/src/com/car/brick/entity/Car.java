package com.car.brick.entity;


import com.badlogic.gdx.math.Rectangle;

/**
 * Created by bikash on 12/30/17.
 */

public class Car extends Rectangle {
    public Car() {
    }

    public Car(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Car(Rectangle rect) {
        super(rect);
    }
    public void move(){
        y++;
    }
    public void move(int y){
        this.y+=y;
    }
}
