import java.util.*;

/* Title: 
Abstract:
Name:
Date:
*/


public class TSPDemo {

    public static void main(String[] args) {

        List<AreaGridTile> cities = DummyData.cities();
        AreaGridTile start = DummyData.start();

        // 1) Build a tour with Nearest Neighbor
        List<AreaGridTile> tour = TSPSolver.nearestNeighborTour(cities, start);

        System.out.println("Nearest Neighbor tour:");
        printTour(tour);
        System.out.println("Length: " + TSPSolver.tourLength(tour));

        // 2) Optional: Improve with 2-opt
        TSPSolver.twoOptImprove(tour);

        System.out.println("\nAfter 2-opt improvement:");
        printTour(tour);
        System.out.println("Length: " + TSPSolver.tourLength(tour));
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
