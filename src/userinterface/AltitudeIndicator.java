package userinterface;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import util.Entity;
import rocket.Rocket;

public class AltitudeIndicator extends Entity {
	
	private Rocket rocket;
	private double maxAltitude;
	
	public AltitudeIndicator(double xOffset, double yOffset, 
			double width, double height, Rocket rocket, 
			double initialAltitude) {
		
		super(xOffset, yOffset, width, height);
		this.rocket = rocket;

		this.maxAltitude = initialAltitude;
		
	}

	public AltitudeIndicator(double xOffset,
			double width, double height, Rocket rocket, 
			double initialAltitude) {
		
		super(xOffset, 0, width, height);
		this.rocket = rocket;

		this.maxAltitude = initialAltitude;
		
	}

	private Rocket getRocket() {
		return rocket;
	}

	public void setRocket(Rocket rocket) {
		this.rocket = rocket;
	}

	public double getMaxAltitude() {
		return maxAltitude;
	}

	public void setMaxAltitude(double maxAltitude) {
		this.maxAltitude = maxAltitude;
	}
	
	public void drawTickMarks(GraphicsContext gc) {
		
		gc.setFill(Color.BLACK);
		
		int numTickMarks = 3;
		
		double majorTickWidth = getWidth() / 1.5;
		double majorTickHeight = 5;
		
		double minorTickWidth = majorTickWidth / 1.5;
		double minorTickHeight = majorTickHeight / 1.5;
		
		// Top and bottom 'lines'
		gc.fillRect(getX() - majorTickWidth / 2, getY(), majorTickWidth, majorTickHeight);
		gc.fillRect(getX() - majorTickWidth / 2, getY() + getHeight() - majorTickHeight, 
				majorTickWidth, majorTickHeight);
		
		double insideHeight = getHeight() - majorTickHeight * 2;
		double spaceBetweenTicks = insideHeight / (numTickMarks + 1);
		
		for (int i = 1; i <= numTickMarks; i++) {
			
			gc.fillRect(getX() - minorTickWidth / 2, 
					getY() + majorTickHeight + (i * spaceBetweenTicks), 
					minorTickWidth, minorTickHeight);
			
		}
		
		
		
	}
	
	public void drawRocketAltitudePoint(GraphicsContext gc) {

		double pointRadius = 3;

		double currentAltitudeProportion = getRocket().getManeuverCalculator().calculateAltitude()
				/ maxAltitude;

		double scaledAltitudeY = getY() + getHeight() - pointRadius
				- (currentAltitudeProportion * (getHeight() - pointRadius));

		// Check to make sure that the dot will be drawn within the bounds
		// of the AltitudeIndicator (give or take 2 pixels)
		if (scaledAltitudeY - pointRadius >= getY() - 2 && scaledAltitudeY + pointRadius <= getY() + getHeight() + 2) {

			gc.setFill(Color.CORNFLOWERBLUE);
			gc.fillOval(getX() - pointRadius, scaledAltitudeY - pointRadius, pointRadius * 2, pointRadius * 2);

		}
		
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		if (isVisible()) {
			
			gc.setFill(Color.LIGHTGRAY);
			gc.fillRoundRect(getX() - getWidth() / 2, getY(), getWidth(), getHeight(), 10, 10);

			drawTickMarks(gc);
			
			drawRocketAltitudePoint(gc);
		}
		
		
	}
	
	@Override
	public void tick(double timeElapsed) {
		
		double rocketAltitude = 
				getRocket().getManeuverCalculator().calculateAltitude();
		if (rocketAltitude > getMaxAltitude()) {
			
			setMaxAltitude(getMaxAltitude() * 2);
			
		}
		
	}
	
	
	
}
