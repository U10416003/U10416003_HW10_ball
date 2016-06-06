//U10416003

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.security.SecureRandom;

public class BallPane extends Pane {
	
	//set the ball radius
	private double radius = 15;
	
	//set balls appear position x
  	private double[] x = {
		radius, radius*2,radius*4,radius*6,radius*8, radius*10,radius*12
	};
	//set balls appear position y
	private double[] y = {
		radius*12, radius*10,radius*8,radius*6, radius*4,radius*2,radius
	};
	//x move the distance
	private double[] dx = {
		1,1,1,1,1,1,1
	};
	//y move the distance
	private double[] dy = {
		1,1,1,1,1,1,1
	};
	//create the ball
	private Circle[] circle = {
		new Circle(x[0], y[0], radius),new Circle(x[1], y[1], radius),
		new Circle(x[2], y[2], radius),new Circle(x[3], y[3], radius),
		new Circle(x[4], y[4], radius),new Circle(x[5], y[5], radius),
		new Circle(x[6], y[6], radius)
	};
	private Timeline animation;

	//constructor
	public BallPane() {
		//secureRandom
		SecureRandom random = new SecureRandom();
		//add balls into pane
		for(int i = 0; i < 7; i++){
			getChildren().add(circle[i]);
			//set ball color
			circle[i].setFill(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0));
		}

		//create an animation for moving the ball
		animation = new Timeline(
			new KeyFrame(Duration.millis(20), e -> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
	}

	//play method
	public void play() {
		animation.play();
	}

	//pause method
	public void pause() {
		animation.pause();
	}

	//increaseSpeed method
	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}

	//decreaseSpeed method
	public void decreaseSpeed() {
		animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
	}

	//rateProperty method
	public DoubleProperty rateProperty() {
		return animation.rateProperty();
	}
  
  	//moveBall method
	protected void moveBall() {
		// Check boundaries
		for(int i = 0; i < 7; i++){
			if (x[i] < radius || x[i] > getWidth() - radius) {
				dx[i] *= -1; // Change ball move direction
			}
			if (y[i] < radius || y[i] > getHeight() - radius) {
				dy[i] *= -1; // Change ball move direction
			}
			//let ball can bounce each other
			for(int k = 0; k < 7; k++){
				if(i == k){
				}
				else if(((x[i]-x[k]) * (x[i]-x[k]) + (y[i]-y[k]) * (y[i]-y[k])) <= 197.0) {
					dx[i] *= -1;
					dy[k] *= -1;
				}
			}
			//adjust ball position
			x[i] += dx[i];
			y[i] += dy[i];
			circle[i].setCenterX(x[i]);
			circle[i].setCenterY(y[i]);
		}
	}
}