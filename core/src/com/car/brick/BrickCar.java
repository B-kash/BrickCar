package com.car.brick;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.car.brick.entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

public class BrickCar extends ApplicationAdapter {
	SpriteBatch batch;
	Texture carImg;
	Texture oppositionCar;
	ShapeRenderer shapeRenderer;
	Car car;
	List<Car> opstacles;
	int numberOfCars = 4;
	Random randomGenerator;

	@Override
	public void create () {
		batch = new SpriteBatch();
		carImg = new Texture("car_Red.png");
		oppositionCar = new Texture("car_white.png");
		shapeRenderer = new ShapeRenderer();
		car = new Car(Gdx.graphics.getWidth()/2-carImg.getWidth()/2,0,carImg.getWidth(),carImg.getHeight());
		opstacles = new ArrayList<Car>();
		randomGenerator = new Random();
		for(int i = 0 ; i < numberOfCars;i++){
			int randomX = randomGenerator.nextInt(Gdx.graphics.getWidth());
			opstacles.add(new Car(randomX,Gdx.graphics.getHeight()+oppositionCar.getHeight()/2,oppositionCar.getWidth(),oppositionCar.getHeight()));
		}

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		batch.draw(carImg, car.x, car.y);
		for(Car car: opstacles){
			batch.draw(oppositionCar,car.x,car.y);
			car.move(-1);
		}
		car.move();
		shapeRenderer.end();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		carImg.dispose();
	}
}
