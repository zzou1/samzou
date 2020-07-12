public class NBody{


	public static double readRadius(String args) {
		In in = new In(args);
		int num = in.readInt();
		double radius = in.readDouble();
		  		
		return radius;
	}

	public static Planet[] readPlanets(String args) {
		In in = new In(args);
		int num = in.readInt();
		double radius = in.readDouble();
		int count = 0;
		Planet[] array = new Planet[num];

		while(!in.isEmpty() && count < num) {
		/* Each line has the rank of a country, then its
		 * name, then its production in metric tons, and
		 * finally the fraction of world salt output it produces. */
		double xxPos = in.readDouble();
		double yyPos = in.readDouble();
		double xxVel = in.readDouble();
		double yyVel = in.readDouble();
		double mass = in.readDouble();
		String imgFileName = in.readString();

		
		array[count] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

		count++;
		;
		}
		System.out.println(array);
		return array;
	}

	public static void main(String[] args) {

		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] actualOutput = readPlanets(filename);
        double radius = readRadius(filename);

        
        StdDraw.enableDoubleBuffering();

        

		double ti = 0;

		while (ti < t){
			double[] xForces = new double[actualOutput.length];
			double[] yForces = new double[actualOutput.length];
			int i = 0;

			for(Planet p : actualOutput){
			xForces[i] = p.calcNetForceExertedByX(actualOutput);
			yForces[i] = p.calcNetForceExertedByY(actualOutput);
			i++;

		}
			int j = 0;
			for(Planet p : actualOutput){
			p.update(dt, xForces[j], yForces[j]);
			j++;

		}
		String imageToDraw = "images/starfield.jpg";
        StdDraw.clear();
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, imageToDraw);

        	for(Planet p : actualOutput){
			p.draw();

		}

		StdDraw.show();
		StdDraw.pause(10);

		ti += dt;
		}
        
		StdOut.printf("%d\n", actualOutput.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < actualOutput.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  actualOutput[i].xxPos, actualOutput[i].yyPos, actualOutput[i].xxVel,
                  actualOutput[i].yyVel, actualOutput[i].mass, actualOutput[i].imgFileName);   
		}


    }
}