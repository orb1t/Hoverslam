package util;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Entity {
	
	private double x; 
	private double y; 
	private double width = 0;
	private double height = 0;
	private double xOffset = 0;
	private double yOffset = 0;
	private double direction = 90;
	private Color color = Color.WHITE;
	
	private Vector2D velocity = new Vector2D();
	private Vector2D acceleration = new Vector2D();
	
	private boolean visible = true;
	
	public Entity() {}
	
	/**
	 * Creates an Entity with arguments for x and y, and the default width, 
	 * height, and color.
	 * @param x the middle x coordinate of the Entity
	 * @param y the top y coordinate of the Entity
	 */
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Creates an Entity with arguments for position, size, and color.
	 * @param x the middle x coordinate of the Entity
	 * @param y the top y coordinate of the Entity
	 * @param color the color of the Entity
	 */
	public Entity(double x, double y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public Entity(double x, double y, Color color, double xOffset, double yOffset) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public Entity(double x, double y, double width, double height, Color color, double xOffset, double yOffset) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}


	public Entity(double xOffset, double yOffset, double width, double height) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
	}
	

	/**
	 * Draws the Entity on a Canvas
	 * @param gc the Canvas's GraphicsContext
	 */
	public abstract void draw(GraphicsContext gc);
	
	/**
	 * Updates the Entity
	 * @param timeElapsed the time, in seconds, since the last tick
	 */
	public abstract void tick(double timeElapsed);

	/**
	 * Gets the middle x coordinate of the Entity
	 * @return the x of the Entity
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the middle x coordinate of the Entity
	 * @param x the new x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Gets the top y coordinate of the Entity
	 * @return the y of the Entity
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the top y coordinate of the Entity
	 * @param y the new y
	 */
	public void setY(double y) {
		this.y = y;
	}


	public double getWidth() {
		return this.width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}


	public double getxOffset() {
		return xOffset;
	}

	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	public double getyOffset() {
		return yOffset;
	}

	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}

	/**
	 * Gets the angle from the positive x-axis counterclockwise to the top of
	 * the Entity
	 * @return the angle between the Entity and the x-axis
	 */
	public double getDirection() {
		return direction;
	}


	/**
	 * Sets the angle from the positive x-axis counterclockwise to the top of
	 * the Entity
	 * @param direction the new angle between the Entity and the x-axis
	 */
	public void setDirection(double direction) {
		this.direction = direction;
	}


	/**
	 * Gets the Vector2D object representing the Entity's 2D velocity
	 * @return
	 */
	public Vector2D getVelocity() {
		return velocity;
	}


	/**
	 * Sets a new Vector2D object for the Entity's velocity
	 * @param velocity the Entity's new velocity vector
	 */
	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public Vector2D getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * Adds the x and y components of the Entity's velocity vector to its x and
	 * y position.
	 * @param timeElapsed the time, in seconds, since the last tick
	 */
	public void applyVelocity(double timeElapsed) {
		
		setX(getX() + getVelocity().getX() * timeElapsed);
		setY(getY() + getVelocity().getY() * timeElapsed);
		
	}
	
	/**
	 * Adds the x and y components of the Entity's acceleration vector to its 
	 * velocity's x and y components.
	 * @param timeElapsed the time, in seconds, since the last tick
	 */
	public void applyAcceleration(double timeElapsed) {
		
		getVelocity().setX(getVelocity().getX() + 
				getAcceleration().getX() * timeElapsed);
		getVelocity().setY(getVelocity().getY() + 
				getAcceleration().getY() * timeElapsed);
		
	}
	
	public void applyForces(double timeElapsed) {
		
		applyVelocity(timeElapsed);
		applyAcceleration(timeElapsed);
		
	}
	
	/**
	 * Gets the color of the Entity
	 * @return the color of the Entity
	 */
	public Color getColor() {
		return color;
	}

	/** Sets the color of the Entity
	 * @param color the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void alignWith(Entity target) {

		setX(target.getX() + getxOffset());
		setY(target.getY() + getyOffset());
		setVelocity(target.getVelocity());
		setDirection(target.getDirection());

	}

	public void alignWith(double x, double y) {

		setX(x + getxOffset());
		setY(y + getyOffset());

	}

}
