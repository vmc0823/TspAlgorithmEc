# Travel Salesman Algorithm Implementation

## Drone Route-Planning Demo:
The app is a drone route-planning demo that shows how to visit multiple target locations efficiently on a grid. It takes a set of “target tiles” (dummy data) and treats them as cities in a Traveling Salesman Problem (TSP). Using a TSP heuristic (Nearest Neighbor, optionally improved with 2-opt), it generates an ordered route that minimizes travel distance/time compared to the original serpentine scan approach.

Upgrade: Serpentine Scan → TSP Route Planning
Original approach (Serpentine)

The first version of the initial Drone Service program used a serpentine scan: move left-to-right across a row, then right-to-left on the next row, repeating downward. It’s simple and guarantees full grid coverage, but it’s not optimized, the drone follows the pattern even if targets are clustered or only a subset of tiles matters.

Upgrade (Traveling Salesman Problem)

We upgraded the route planner to a TSP-style algorithm. Each target tile is treated as a “city” with coordinates (x, y), and travel cost represents time (using grid distance like Manhattan distance). The goal is to visit every target once (and optionally return to start) while minimizing total travel cost. To keep it practical and fast, the solution uses a Nearest Neighbor tour, with an optional 2-opt improvement to shorten the route further.

Why it’s beneficial:

Compared to serpentine, the TSP route:
- reduces total distance/time by avoiding unnecessary back-and-forth scanning,
- adapts to target layout (clusters get visited efficiently),
- provides a clear “before vs after” comparison: a baseline scan pattern versus an optimized route-planning algorithm.