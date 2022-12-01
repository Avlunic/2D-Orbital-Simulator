public class Body {
	
	double x_coordinate;
	double y_coordinate;
	int mass;
	double x_accelaration;
	double y_accelaration;
	double x_velocity;
	double y_velocity;
	
	//G is 10000 times larger than real world G to account for mass/memory size limitations
	double G = 6.6743 * Math.pow(10, -11);
	double r;
	double x_distance;
	double y_distance;
	double Force;
	double x_Force;
	double y_Force;
    
	//The body is initialized
    public Body(double x_coordinate, double y_coordinate, int mass, double x_accelaration, double y_accelaration, double x_velocity, double y_velocity) {
    	
    	this.x_coordinate = x_coordinate;
    	this.y_coordinate = y_coordinate;
    	this.mass = mass;
    	this.x_accelaration = x_accelaration;
    	this.y_accelaration = y_accelaration;
    	this.x_velocity = x_velocity;
    	this.y_velocity = y_velocity;
    	
    }
    
    //The body moves as though it is in space and has no forces acting on it
    public void move() {
    	
    	this.x_velocity += this.x_accelaration;
    	this.y_velocity += this.y_accelaration;
    	this.x_coordinate += this.x_velocity;
    	this.y_coordinate += this.y_velocity;
    	
    }

	public double getX_coordinate() {
		
		return x_coordinate;
		
	}

	public void setX_coordinate(double x_coordinate) {
		
		this.x_coordinate = x_coordinate;
		
	}

	public double getY_coordinate() {
		
		return y_coordinate;
		
	}

	public void setY_coordinate(double y_coordinate) {
		
		this.y_coordinate = y_coordinate;
		
	}

	public int getMass() {
		
		return mass;
		
	}

	public void setMass(int mass) {
		
		this.mass = mass;
		
	}

	public double getX_accelaration() {
		
		return x_accelaration;
		
	}

	public void setX_accelaration(double x_accelaration) {
		
		this.x_accelaration = x_accelaration;
		
	}

	public double getY_accelaration() {
		
		return y_accelaration;
		
	}

	public void setY_accelaration(double y_accelaration) {
		
		this.y_accelaration = y_accelaration;
		
	}

	public double getX_velocity() {
		
		return x_velocity;
		
	}

	public void setX_velocity(double x_velocity) {
		
		this.x_velocity = x_velocity;
		
	}

	public double getY_velocity() {
		
		return y_velocity;
		
	}
	
	public void setG(double G) {
		
		this.G = G;
		
	}
	
	
	public double getG() {
		
		return G;
		
	}

	public void setY_velocity(double y_velocity) {
		
		this.y_velocity = y_velocity;
		
	}
	
	//The physics function is used for when there are more than two bodies, this is because it adds the body's accelerations
	public void physics(double x_coordinate, double y_coordinate, int mass) {

		x_distance = this.x_coordinate - x_coordinate;
		y_distance = this.y_coordinate - y_coordinate;
		
		r = Math.sqrt(Math.pow(x_distance, 2) + Math.pow(y_distance, 2));
		
		Force = 0;
		
		if (r > 10) {
			
			Force = -G * this.mass * mass / Math.pow(r, 2);
			
		}
		
		x_Force = Force * (x_distance / r);
		y_Force = Force * (y_distance / r);
		
		this.x_accelaration += x_Force / this.mass;
		this.y_accelaration += y_Force / this.mass;

	}
    
}