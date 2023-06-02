import java.util.HashSet;

public class Board {
    private HashSet<Point> liveCells;
    private HashSet<Point> cellsToKill;
    private HashSet<Point> cellsToCreate;
    private HashSet<Point> cellsAlreadyChecked;

    public Board(HashSet<Point> initialLiveCells) {
        liveCells = initialLiveCells;
        cellsToKill = new HashSet<>();
        cellsToCreate = new HashSet<>();
        cellsAlreadyChecked = new HashSet<>();
    }

    public HashSet<Point> getLiveCells() {
        return this.liveCells;
    }

    public void performGameStep() {
        prepareCellChanges();
        executeCellChanges();
    }

    private void prepareCellChanges() {
        for(Point liveCell : liveCells) {
            this.processLiveCell(liveCell);
            
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
        int liveNeighboursCount = this.countLiveNeighbours(cell);
        
        if(liveNeighboursCount < 2) { // underpopulation
            cellsToKill.add(cell);
        } else if (liveNeighboursCount > 3) { // overpopulation
            cellsToKill.add(cell);
        }

        cellsAlreadyChecked.add(cell);
    }

    private void processDeadCell(Point cell) {
        int liveNeighboursCount = this.countLiveNeighbours(cell);
        if(liveNeighboursCount == 3) {
            cellsToCreate.add(cell);
        }

        cellsAlreadyChecked.add(cell);
    }

    private int countLiveNeighbours(Point position) {
        int liveNeighboursCount = 0;
        Point[] neighbours = position.getNeighbours();

        for(Point neighbour : neighbours) {
            if(this.liveCells.contains(neighbour)) {
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
