import java.util.*;

/* Title: 
Abstract:
Name:
Date:
*/

public class DummyData {

    // A small set of "cities" (grid points) to visit
    public static List<AreaGridTile> cities() {
        return Arrays.asList(
            tile(1, 1),
            tile(4, 1),
            tile(2, 5),
            tile(7, 3),
            tile(6, 6),
            tile(0, 4),
            tile(3, 7),
            tile(8, 2)
        );
    }

    // Choose where the tour starts (can be any city or a separate point)
    public static AreaGridTile start() {
        return tile(1, 1);
    }

    private static AreaGridTile tile(int x, int y) {
        AreaGridTile t = new AreaGridTile();
        t.x = x;
        t.y = y;
        return t;
    }
}
