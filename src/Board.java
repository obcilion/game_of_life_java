import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Board {
    private Set<Point> liveCells;
    private final Set<Point> cellsToKill = new HashSet<>();
    private final Set<Point> cellsToCreate = new HashSet<>();
    private final Set<Point> cellsAlreadyChecked = new HashSet<>();

    public Board(Set<Point> initialLiveCells) {
        liveCells = initialLiveCells;
    }

    public Set<Point> getLiveCells() {
         return Collections.unmodifiableSet(liveCells);
    }

    public void performGameStep() {
        prepareCellChanges();
        executeCellChanges();
    }

    private void prepareCellChanges() {
        for(Point liveCell : liveCells) {
            processLiveCell(liveCell);
            
            Point[] neighbourCells = liveCell.getNeighbours();
            for(Point neighbourCell : neighbourCells) {
                boolean isLive = liveCells.contains(neighbourCell);
                boolean alreadyChecked = cellsAlreadyChecked.contains(neighbourCell);
                
                if(!isLive && !alreadyChecked) {
                    processDeadCell(neighbourCell);
                }
            }
        }

        cellsAlreadyChecked.clear();
    }

    private void processLiveCell(Point cell) {
        int liveNeighboursCount = countLiveNeighbours(cell);
        
        if(liveNeighboursCount < 2) { // underpopulation
            cellsToKill.add(cell);
        } else if (liveNeighboursCount > 3) { // overpopulation
            cellsToKill.add(cell);
        }

        cellsAlreadyChecked.add(cell);
    }

    private void processDeadCell(Point cell) {
        int liveNeighboursCount = countLiveNeighbours(cell);
        if(liveNeighboursCount == 3) {
            cellsToCreate.add(cell);
        }

        cellsAlreadyChecked.add(cell);
    }

    private int countLiveNeighbours(Point position) {
        int liveNeighboursCount = 0;
        Point[] neighbours = position.getNeighbours();

        for(Point neighbour : neighbours) {
            if(liveCells.contains(neighbour)) {
                liveNeighboursCount++;
            }
        }

        return liveNeighboursCount;
    }

    private void executeCellChanges() {
        for(Point cell : cellsToKill) {
            liveCells.remove(cell);
        }

        for(Point cell : cellsToCreate) {
            liveCells.add(cell);
        }

        cellsToCreate.clear();
        cellsToKill.clear();
    }
}
