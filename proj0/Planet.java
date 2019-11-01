
public class Planet{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double G = 6.67e-11;

	/** first constructor */
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** second constructor */
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass =  p.mass;
		imgFileName = p.imgFileName;
	}

    /** calculate the distance between two planets. */
	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos ;
		return Math.sqrt(dx * dx + dy * dy);

	}

	/** calculate the force exerted on this planet by the given planet*/
	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		return this.mass * p.mass * Planet.G / (distance* distance);

	}

	/**calculate the exerted force in x direction */
	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		double r = this.calcDistance(p);
		double Force = this.calcForceExertedBy(p);
		return Force * dx / r;

	}

	/**calculate the exerted force in y direction */
	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - this.yyPos;
		double r = this.calcDistance(p);
		double Force = this.calcForceExertedBy(p);
		return Force * dy / r;
		
	}

	/**calculate the net force exerted force in x direction */
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double xForce = 0;
		for (int i =0; i < allPlanets.length; i++){
			if(!this.equals(allPlanets[i])){
				xForce += this.calcForceExertedByX(allPlanets[i]);
			}
		}
		return xForce;
	}

	/**calculate the net force exerted force in y direction */
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double yForce = 0;
		for (int i =0; i < allPlanets.length; i++){
			if(!this.equals(allPlanets[i])){
				yForce += this.calcForceExertedByY(allPlanets[i]);
			}		 
		}
		return yForce;
	}

	/** draw each planet itself at an appropriate position */
	public void draw(){
		/* Stamps a copy of the planet at the position. the file of the planet image is under folder of "images".
		 * a mistake easily to be ignored here..*/
		String imageToDraw = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imageToDraw);

	}


	/**update position after extered by the force in a small period*/
	public void update(double dt, double fX, double fY){
		double xAce = fX / this.mass;
		double yAce = fY / this.mass;
		this.xxVel += xAce  * dt;
		this.yyVel += yAce  * dt;
		this.xxPos += xxVel * dt;
		this.yyPos += yyVel * dt;

	}
}