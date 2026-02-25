/*
 * Title: Group Homework - 10 Extra Points
 * Abstract: Drone route-planning program that shows how to visit multiple target locations efficiently, using the TSP algorithm  (AI/agentic programming utilized)
 * Link to demo video: https://www.youtube.com/watch?v=JS6kR18svo8
 * Group Names: Brandon Evans, Wootark Kim (Tom), Valentina Waltman
 * Date: 02/22/2026
 */

// Imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<AreaGridTile> points = new ArrayList<>();

        // 1. Parse coordinates from System.in
        while (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            if (scanner.hasNextInt()) {
                int y = scanner.nextInt();
                AreaGridTile tile = new AreaGridTile();
                tile.x = x;
                tile.y = y;
                points.add(tile);
            }
        }
        scanner.close();

        if (points.isEmpty()) return;

        // The first point in the file is our starting point
        AreaGridTile start = points.get(0);

        // 2. Generate Nearest Neighbor Tour using your TSPSolver
        List<AreaGridTile> tour = TSPSolver.nearestNeighborTour(points, start);

        System.out.println("Nearest Neighbor tour:");
        printTour(tour);
        System.out.println("Length: " + TSPSolver.tourLength(tour));
        System.out.println(); // Required blank line for diff

        // 3. Improve with 2-opt using your TSPSolver
        TSPSolver.twoOptImprove(tour);

        System.out.println("After 2-opt improvement:");
        printTour(tour);
        // Use print instead of println to avoid trailing newline for diff match
        System.out.print("Length: " + TSPSolver.tourLength(tour));
    }

    private static void printTour(List<AreaGridTile> tour) {
        for (int i = 0; i < tour.size(); i++) {
            AreaGridTile t = tour.get(i);
            System.out.print("(" + t.x + "," + t.y + ")");
            if (i < tour.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
}