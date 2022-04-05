package application;

public class Position {
	private  double xpos;
	private  double ypos;
	
	public Position(double xpos,double ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	} 
	public double getxpos() { return xpos;}
	public double getypos() { return ypos;}
	
	public void setxpos(double xpos) {
		this.xpos =xpos;
	}
	public void setypos(double ypos) {
		this.ypos = ypos;
	}

	@Override
	public String toString() {
		return "Position{"+
				"xpos ="+ xpos +
				", ypos =" +ypos+
				"}";
	}
	
}
