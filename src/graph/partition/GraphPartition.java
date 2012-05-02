package graph.partition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphPartition {
	private int n, k, distFunc;
	private ArrayList<Point3D> points;
	private ArrayList<ArrayList<Point3D>> solution;
	private double cost = 0;
	
	public GraphPartition(int n, int k, int distFunc, ArrayList<Point3D> points) {
		this.n = n;
		this.k = k;
		this.distFunc = distFunc;
		this.points = points;
		this.solution = new ArrayList<ArrayList<Point3D>>();
		solve();
	}
	
	private void solve() {
		if(k<=1) {
			solution.add(points);
			return;
		}
		
		Point3D temp1 =null, temp2 = null;
		double maxDist=0;
		
		for ( Point3D p1 : points) {
			for (Point3D p2 : points) {
				if(genDist(p1, p2) > maxDist) {
					maxDist = genDist(p1, p2);
					temp1 = p1;
					temp2 = p2;
				}
			}
		}
		
		ArrayList<Point3D> anchor1 = new ArrayList<Point3D>(); 
		ArrayList<Point3D> anchor2 = new ArrayList<Point3D>();
		
		anchor1.add(temp1);
		points.remove(temp1);
		anchor2.add(temp2);
		points.remove(temp2);
		
		solution.add(anchor1);
		solution.add(anchor2);
		
		//System.out.println("points left: " + points);
		//System.out.println("initial partitions(2): " + solution + "\n");
		
		for (int i = 2; i < k; i++) { // for the number of partitions. 
			ArrayList<Point3D> anchor = new ArrayList<Point3D>();
			temp1 = null;
			double maxMinDist = 0;
			for(Point3D p : points) { // for each of the un-partitioned points
				double minDist = Double.MAX_VALUE;
				for (ArrayList<Point3D> a : solution) {
					minDist = Math.min(minDist, genDist(p, a.get(0)));
				}
				if(minDist > maxMinDist){
					temp1 = p;
					maxMinDist = minDist;
				}
			}
			anchor.add(temp1); // add the anchor to a new partition
			solution.add(anchor); // add the new partition to the solution
			points.remove(temp1); // the point is no longer un-partitioned
		}
		
		//System.out.println("partitions complete: " + solution);
		//System.out.println("Points left to sort: " + points + "\n");
		
		//Our partitions have been initialized, now just populate
		for( Point3D p1 : points ) {
			double minMaxDist = Double.MAX_VALUE;
			int bestI = 0;
			maxDist = -1;
			for( int i = 0; i < k; i++) {
				for( Point3D p2 : solution.get(i)) {
					if(genDist(p1, p2) > maxDist) {
						maxDist = genDist(p1, p2);
					}
				}
				if(maxDist < minMaxDist) {
					bestI = i;
					minMaxDist = maxDist;
				}
				maxDist = -1;
				
			}
			solution.get(bestI).add(p1);
			if(minMaxDist > cost) {
				cost = minMaxDist;
			}
		}
	}
	
	private double genDist(Point3D p1, Point3D p2) {
		if ( distFunc == 0 ) return p1.eDist(p2);
		else if ( distFunc == 1) return p1.mDist(p2);
		else return -1; // TODO: handle error better
	}
	
	public String toString() {
		return 	"n: " + new Integer(this.n).toString() + '\n' +
				"k: " + new Integer(this.k).toString() + '\n' +
				"dist: " + new Integer(this.distFunc).toString() + '\n' +
				"points: " + solution + "\n" + 
				"cost: " + cost + "\n";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("USAGE: ./GraphPartition input_file");
		}
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.err.println("File Doesn't Exist");
		}
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int distFunc = scanner.nextInt();
		ArrayList<Point3D> points = new ArrayList<Point3D>();
		for(int i=0; i<n; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int z = scanner.nextInt();
			points.add(new Point3D(x, y, z));
		}
		System.out.println("Kicking ass...\n");
		GraphPartition problem = new GraphPartition(n, k, distFunc, points);
		System.out.println(problem);
	}
}
