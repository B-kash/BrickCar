package com.car.brick;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.car.brick.entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BrickCar extends ApplicationAdapter {
	SpriteBatch batch;
	Texture carImg;
	Texture oppositionCar;
	ShapeRenderer shapeRenderer;
	Car car;
	List<Car> opstacles;
	int numberOfCars = 6;
	Random randomGenerator;
	int gameState = 1;
	BitmapFont font;
	int score;

	@Override
	public void create () {
		batch = new SpriteBatch();
		carImg = new Texture("car_Red.png");
		oppositionCar = new Texture("car_white.png");
		shapeRenderer = new ShapeRenderer();
		opstacles = new ArrayList<Car>();
		randomGenerator = new Random();
		font = new BitmapFont();
		font.setColor(Color.YELLOW);
		font.getData().setScale(10);
		resetGame();
	}

	@Override
	public void render () {
		gameLoop();
	}


	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		carImg.dispose();
		oppositionCar.dispose();

	}

	public void gameLoop(){
		clearScreen();
		beginRendering();
		if(gameState!=0){
			drawOurCar();
			drawOppositionCar();
			moveOurCar();
			checkOppositionCarCollision();
			showScore();
		}else{
			showGameOver();
		}

		endRendering();
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void beginRendering() {
		batch.begin();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
	}

	private void drawOppositionCar() {
		for (Car car : opstacles) {
			drawCar(car);
			batch.draw(oppositionCar, car.x, car.y);
			car.move();
		}
	}
	private void drawCar(Car car) {
		if(checkWallCollision(car)){
			giveNewPositionToCar(car);
			increaseScore();
		}batch.draw(oppositionCar, car.x, car.y);
	}

	private boolean checkWallCollision(Car car) {
		if(car.y < -oppositionCar.getHeight() )
			return true;
		return false;
	}

	private void giveNewPositionToCar(Car car) {
		car.x = randomGenerator.nextInt(Gdx.graphics.getWidth()-oppositionCar.getWidth()/2);
		car.y = Gdx.graphics.getHeight();
		if(car.getSpeedY()<=-40)
			car.setSpeedY(car.getSpeedY()-1);

	}

	private void increaseScore() {
		score++;
	}

	private void checkOppositionCarCollision() {
		for(Car opposition:opstacles){
			if(car.overlaps(opposition)){
				gameState = 0;
			}
		}
	}

	private void moveOurCar() {
		if(Gdx.input.isTouched()){
			if(Gdx.input.getX() > car.getX() && Gdx.input.getX() < car.getX()+carImg.getWidth()){
				car.setX(Gdx.input.getX()-carImg.getWidth()/2);
			}
		}
	}

	private void drawOurCar() {
		batch.draw(carImg, car.x, car.y);
	}

	private void showScore() {
		font.draw(batch,String.valueOf("Score: "+score),100,100);
	}

	private void showGameOver() {
		font.draw(batch,String.valueOf("Game Over"),Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()*2/3);
		font.draw(batch,String.valueOf("Score: "+score),Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()*3/4);
		if(Gdx.input.justTouched()){
			resetGame();
		}
	}

	private void endRendering() {
		shapeRenderer.end();
		batch.end();
	}

	private void resetGame() {
		car = new Car(Gdx.graphics.getWidth()/2-carImg.getWidth()/2,0,carImg.getWidth(),carImg.getHeight(),0,10);
		opstacles.clear();
		for(int i = 0 ; i < numberOfCars;i++){
			int randomX = randomGenerator.nextInt(Gdx.graphics.getWidth()-oppositionCar.getWidth()/2);
			opstacles.add(new Car(randomX,i>0?opstacles.get(i-1).getY()+oppositionCar.getHeight():Gdx.graphics.getHeight(),oppositionCar.getWidth(),oppositionCar.getHeight(),0,-10));
		}
		score=0;
		gameState = 1;
	}

}
