import java.util.HashSet;

public class Game {
    public static void main(String[] args) {
        Board board = new Board(initialBoardState());
        BoardRenderer renderer = new BoardRenderer(board);

        while(true){
            renderer.render();
            board.performGameStep();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static HashSet<Point> initialBoardState() {
        return rPentomino();
    }

    private static HashSet<Point> blinker() {
        HashSet<Point> cells = new HashSet<>();

        cells.add(new Point(0, 0));
        cells.add(new Point(0, 1));
        cells.add(new Point(0, 2));

        return cells;
    }

    private static HashSet<Point> diehard() {
        HashSet<Point> cells = new HashSet<>();

        cells.add(new Point(1, 0));
        cells.add(new Point(5, 0));
        cells.add(new Point(6, 0));
        cells.add(new Point(7, 0));

        cells.add(new Point(0, 1));
        cells.add(new Point(1, 1));

        cells.add(new Point(6, 2));

        return cells;
    }

    private static HashSet<Point> rPentomino() {
        HashSet<Point> cells = new HashSet<>();

        cells.add(new Point(1, 0));

        cells.add(new Point(0, 1));
        cells.add(new Point(1, 1));

        cells.add(new Point(1, 2));
        cells.add(new Point(2, 2));

        return cells;
    }
}
