public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double g = 6.67e-11;


	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}


	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;

	}

	public double calcDistance(Planet p){
		double dx;
		double dy;
		double distance;
		dx = (this.xxPos-p.xxPos);
		dy = (this.yyPos-p.yyPos);
		distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		double force;
		double distance;
		distance = this.calcDistance(p);
		force = g*this.mass*p.mass/(distance*distance);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double force;
		double distance;
		double dx;
		double forcex;
		dx = (p.xxPos-this.xxPos);
		distance = this.calcDistance(p);
		force = this.calcForceExertedBy(p);
		forcex = force*dx/distance;
		return forcex;
	}

	public double calcForceExertedByY(Planet p){
		double force;
		double distance;
		double dy;
		double forcey;
		dy = (p.yyPos-this.yyPos);
		distance = this.calcDistance(p);
		force = this.calcForceExertedBy(p);
		forcey = force*dy/distance;
		return forcey;
	}

	public double calcNetForceExertedByX(Planet[] array){
		double force;
		double distance;
		double dx;
		double forcex;
		double netforcex = 0;

		for(Planet p : array){
			if(this.equals(p)){
				continue;
			}
			dx = (p.xxPos-this.xxPos);
			distance = this.calcDistance(p);
			force = this.calcForceExertedBy(p);
			forcex = force*dx/distance;
			netforcex += forcex;

		}
		
		return netforcex;
	}

	public double calcNetForceExertedByY(Planet[] array){
		double force;
		double distance;
		double dy;
		double forcey;
		double netforcey = 0;
		
		for(Planet p : array){
			if(this.equals(p)){
				continue;
			}
			dy = (p.yyPos-this.yyPos);
			distance = this.calcDistance(p);
			force = this.calcForceExertedBy(p);
			forcey = force*dy/distance;
			netforcey += forcey;

		}
		
		return netforcey;
	}

	public void update(double dt,double fx, double fy){

		double ax;
		double ay;
		ax = fx / this.mass;
		ay = fy / this.mass;
		this.xxVel = this.xxVel + dt*ax;
		this.yyVel = this.yyVel + dt*ay;
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;

	}

	public void draw(){
		String imageToDraw = "images/";
		StdDraw.picture(this.xxPos, this.yyPos, imageToDraw+this.imgFileName);
	}




}







