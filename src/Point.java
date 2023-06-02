public class Point {

    // 0,0 is assumed to be the bottom left corner
    // x increase towards the right, y increase upwards

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Point[] getNeighbours() {
        Point[] neighbours = {
            // Bottom row
            new Point(this.x - 1, this.y - 1),  // Left column
            new Point(this.x, this.y -1),       // Center column
            new Point(this.x + 1, this.y - 1),  // Right column

            // Center row
            new Point(this.x - 1, this.y),      // Left column
            new Point(this.x + 1, this.y),      // Right column

            // Upper row
            new Point(this.x - 1, this.y + 1),  // Left column
            new Point(this.x, this.y + 1),      // Center column
            new Point(this.x + 1, this.y + 1),  // Right column
        };

        return neighbours;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Point point = (Point) obj;
        return this.x == point.x && this.y == point.y;
    }
    
    @Override
    public int hashCode() {
        return (31 * x) + y;
    }
}
