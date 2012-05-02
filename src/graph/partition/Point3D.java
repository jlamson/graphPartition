package graph.partition;

public class Point3D {
	private int x, y, z, id;
	
	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double eDist(Point3D p) {
		return Math.sqrt( 
				Math.pow((x-p.getX()),2) + 
				Math.pow((y-p.getY()),2) + 
				Math.pow((z-p.getZ()),2) );
	}
	
	public int mDist(Point3D p) {
		return (Math.abs(x-p.getX()) +
				Math.abs(y-p.getY()) +
				Math.abs(z-p.getZ()));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public String toString() {
		return "(" + new Integer(x).toString() + ", " +
				new Integer(y).toString() + ", " +
				new Integer(z).toString() + ")";
	}
}
