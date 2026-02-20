import java.util.*;

/* Title: 
Abstract:
Name:
Date:
*/

public class TSPSolver {

    // Manhattan distance for grid-based TSP
    public static int dist(AreaGridTile a, AreaGridTile b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    // Nearest Neighbor heuristic: easy to explain + fast
    public static List<AreaGridTile> nearestNeighborTour(List<AreaGridTile> cities, AreaGridTile start) {
        List<AreaGridTile> unvisited = new ArrayList<>(cities);
        List<AreaGridTile> tour = new ArrayList<>();

        AreaGridTile current = start;
        tour.add(current);
        removeFirstMatching(unvisited, current);

        while (!unvisited.isEmpty()) {
            int bestIdx = 0;
            int bestDist = dist(current, unvisited.get(0));

            for (int i = 1; i < unvisited.size(); i++) {
                int d = dist(current, unvisited.get(i));
                if (d < bestDist) {
                    bestDist = d;
                    bestIdx = i;
                }
            }

            current = unvisited.remove(bestIdx);
            tour.add(current);
        }

        // Return to start to complete the cycle
        tour.add(start);

        return tour;
    }

    // 2-opt improvement: iteratively swap edges to reduce tour length
    public static void twoOptImprove(List<AreaGridTile> tour) {
        // tour is assumed to be a cycle (first == last)
        boolean improved = true;

        while (improved) {
            improved = false;

            // Skip index 0 (start) and last index (same as start)
            for (int i = 1; i < tour.size() - 2; i++) {
                for (int k = i + 1; k < tour.size() - 1; k++) {

                    int delta = gainIf2OptSwap(tour, i, k);
                    if (delta < 0) { // negative delta means improvement (shorter)
                        reverseSegment(tour, i, k);
                        improved = true;
                    }
                }
            }
        }
    }

    // Calculate total length of the tour
    public static int tourLength(List<AreaGridTile> tour) {
        int sum = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            sum += dist(tour.get(i), tour.get(i + 1));
        }
        return sum;
    }

    // Calculate the change in tour length if we swap edges (i-1, i) and (k, k+1)
    private static int gainIf2OptSwap(List<AreaGridTile> tour, int i, int k) {
        AreaGridTile a = tour.get(i - 1);
        AreaGridTile b = tour.get(i);
        AreaGridTile c = tour.get(k);
        AreaGridTile d = tour.get(k + 1);

        int before = dist(a, b) + dist(c, d);
        int after  = dist(a, c) + dist(b, d);
        return after - before;
    }

    // Reverse the segment of the tour between indices i and k (inclusive)
    private static void reverseSegment(List<AreaGridTile> tour, int i, int k) {
        while (i < k) {
            AreaGridTile tmp = tour.get(i);
            tour.set(i, tour.get(k));
            tour.set(k, tmp);
            i++;
            k--;
        }
    }

    // Helper to remove the first occurrence of a tile matching the target (by coordinates)
    private static void removeFirstMatching(List<AreaGridTile> list, AreaGridTile target) {
        for (int i = 0; i < list.size(); i++) {
            AreaGridTile t = list.get(i);
            if (t.x == target.x && t.y == target.y) {
                list.remove(i);
                return;
            }
        }
    }
}
