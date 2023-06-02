import java.util.ArrayList;
import java.util.Set;

public class BoardRenderer {
    private Board board;

    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;

    int minY = Integer.MAX_VALUE;
    int maxY = Integer.MIN_VALUE;


    public BoardRenderer(Board board) {
        this.board = board;
    }

    public void render() {
        Set<Point> liveCells = board.getLiveCells();

        // Get the size of the game board
        for(Point cell : liveCells) {
            
            int cellX = cell.getX();            
            int cellY = cell.getY();

            if(cellX < minX) {
                minX = cellX;
            } else if(cellX > maxX) {
                maxX = cellX;
            }

            if(cellY < minY) {
                minY = cellY;
            } else if(cellY > maxY) {
                maxY = cellY;
            }
        }

        ArrayList<String> rows = new ArrayList<>();
        // Build the rows to render
        int currentY = maxY;
        int width = 0;
        do {
            String currentRowCharacters = "";
    
            int currentX = minX;
            do {
                Point currentPoint = new Point(currentX, currentY);
                boolean currentCellIsLive = liveCells.contains(currentPoint);
                currentRowCharacters += currentCellIsLive ? "x" : " ";

                currentX++;
            } while(currentX <= maxX);
            rows.add(currentRowCharacters);
            if(width < currentRowCharacters.length()) { width = currentRowCharacters.length(); }

            currentY--;
            currentRowCharacters = "";
        } while(currentY >= minY);
    
        String verticalBar = "+" + "-".repeat(width + 2) + "+";

        System.out.println(verticalBar);
        for(String row : rows) {
            System.out.println("| " + row + " |");
        }
        System.out.println(verticalBar);
    }
} 