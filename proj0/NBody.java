public class NBody{

	private static String imageToDraw = "images/starfield.jpg";

	/** return the radius of the universe, which is the second item in the file*/
	public static double readRadius(String filename){
		In in = new In(filename);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
				
		}
	/** return a array, including all planets in the file */
	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int arraySize = in.readInt();
		double universeRadius = in.readDouble();
		Planet [] planets = new Planet[arraySize];

		for(int i =0; i < arraySize; i++){
			double firstItemInRow  = in.readDouble();
			double secondItemInRow = in.readDouble();
			double thirdItemInRow  = in.readDouble();
			double fourthItemInRow = in.readDouble();
			double fifthItemInRow  = in.readDouble();
			String sixthItemInRow  = in.readString();
			planets[i] = new Planet(firstItemInRow, secondItemInRow, thirdItemInRow, fourthItemInRow, fifthItemInRow, sixthItemInRow);
		}

		return planets;
	}

	private static void drawBackground(double r){
		StdDraw.setScale(-r, r);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		StdDraw.show();
	}

	public static void main(String[] args) {
		double t = 0;
		double T  = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radiusUniverse = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		StdDraw.enableDoubleBuffering();
		while(t < T){
			
		    for(int i=0; i< planets.length; i++){
		    	xForces[i] = planets[i].calcNetForceExertedByX(planets);
		    	yForces[i] = planets[i].calcNetForceExertedByY(planets);

		    }

			for(int i= 0; i < planets.length; i++){
				/**call update on each of the planet */
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			drawBackground(radiusUniverse);
			for(int i= 0; i < planets.length; i++){
				/**call update on each of the planet */
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radiusUniverse);
		for (int i= 0; i < planets.length; i++) {
   			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
   		}
   	}
}



