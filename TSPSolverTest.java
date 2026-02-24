import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TSPSolverTest {

    @Test
    void testNearestNeighborLength(){
        List<AreaGridTile> cities = DummyData.cities();
        AreaGridTile start = cities.get(0);

        List<AreaGridTile> tour =
                TSPSolver.nearestNeighborTour(cities, start);

        int length = TSPSolver.tourLength(tour);

        assertEquals(30, length);
    }

}
