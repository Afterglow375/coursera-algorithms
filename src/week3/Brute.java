package week3;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;

public class Brute {
    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        // Read input
        String filename = "src/week3/" + args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] arr = new Point[N];

        // initialize array to contain points
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            arr[i] = new Point(x, y);
            arr[i].draw();
        }

        // Check for 4 collinear points
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    for (int l = k+1; l < N; l++) {
                        // If we have a collinear line, draw a line between the farthest points
                        if (arr[i].slopeTo(arr[j]) == arr[j].slopeTo(arr[k])
                                && arr[j].slopeTo(arr[k]) == arr[k].slopeTo(arr[l])) {
                            
                        }
                    }
                }
            }
        }

        StdDraw.show(0);
        StdDraw.setPenRadius();
    }
}
