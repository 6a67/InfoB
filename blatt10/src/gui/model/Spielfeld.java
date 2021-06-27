package gui.model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Random;

public class Spielfeld extends Observable {
    Field[][] fields;

    int coveredElements = 0;

    int width;
    int height;
    int bombs;

    int markers;

    boolean over;

    public Spielfeld(int x, int y, int bombs) {
        this.width = x;
        this.height = y;
        this.bombs = bombs;
        generateGame();
    }

    /*private void generateProx() {
        prox = new int[width][height];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(mines[x][y]) {
                    prox[x][y] = 606;
                } else {
                    // neighbours
                    int neighbours = 0;
                    for(int o = x-1; o <= x+1; o++) {
                        for(int p = y-1; p <= y+1; p++) {
                            if(o >= 0 && p >= 0 && o < width && p < height && mines[o][p]) neighbours++;
                        }
                    }
                    prox[x][y] = neighbours;
                }
            }
        }
    }*/

    /**
     * Generates the array in which it is saved how many bombs there are in a 3x3 radius
     */
    private void generateProx() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(fields[x][y].isMine()) {
                    for(int o = x-1; o <= x+1; o++) {
                        for(int p = y-1; p <= y+1; p++) {
                            if(o >= 0 && p >= 0 && o < width && p < height) fields[o][p].increaseProx();
                        }
                    }
                }
            }
        }
    }

    /**
     * Randomly generates an array with bombs
     */
    private void generateMines() {
        int tmpBombs = bombs;

        Random r = new Random();

        while(tmpBombs != 0) {
            int rdmX = r.nextInt(width);
            int rdmY = r.nextInt(height);

            if(!fields[rdmX][rdmY].isMine()) {
                fields[rdmX][rdmY].setMine(true);
                tmpBombs--;
            }
        }
    }

    /**
     * Uncovers a given field and all fields around it if it is a field without any bombs surrounding it
     * @param x x of the field
     * @param y y of the field
     */
    public void uncover(int x, int y) {
        Queue<Integer> qX = new LinkedList<>();
        Queue<Integer> qY = new LinkedList<>();
        qX.add(x);
        qY.add(y);
        while(!qX.isEmpty()) {
            int tmpX = qX.remove();
            int tmpY = qY.remove();
            if(tmpX >= 0 && tmpY >= 0 && tmpX < width &&  tmpY < height && fields[tmpX][tmpY].isCovered()) {
                if(fields[tmpX][tmpY].isMine()) {
                    over = true;
                    break;
                }
                if(fields[tmpX][tmpY].getProx() == 0) {
                    for(int i = tmpX-1; i <= tmpX+1; i++) {
                        for(int j = tmpY-1; j <= tmpY+1; j++) {
                            qX.add(i);
                            qY.add(j);
                        }

                    }

                    /*qX.add(tmpX - 1);
                    qY.add(tmpY);

                    qX.add(tmpX + 1);
                    qY.add(tmpY);

                    qX.add(tmpX);
                    qY.add(tmpY - 1);

                    qX.add(tmpX);
                    qY.add(tmpY + 1);*/

                }
                fields[tmpX][tmpY].setCovered(false);
                coveredElements--;
            }
        }

        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Checks if the game is over
     * @return true if game is over, false if not
     */
    public boolean isOver() {
        return over || isWon();
    }

    /**
     * Checks if the game is won
     * @return true if the game is won, false if not
     */
    public boolean isWon() {
        return (coveredElements == bombs);
    }

    /**
     * Checks both the bomb and the prox array and returns what should be on the field
     * @param x x of the field
     * @param y y of the field
     * @return String of the char that should be displayed on the field
     */
    public String getField(int x, int y) {
        return fields[x][y].getField();
    }

    /**
     * Checks if a given field is covered
     * @param x x of the field
     * @param y y of the field
     * @return true if the field is covered, false if not
     */
    public boolean isCovered(int x, int y) {
        return fields[x][y].isCovered();
    }

    /**
     * Counts the marker value up by one
     */
    public void addMarker() {
        markers++;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Counts the marker value down by one
     */
    public void removeMarker() {
        markers--;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return marker value
     */
    public int getMarkers() {
        return markers;
    }

    /**
     * @return amount of bombs on the field
     */
    public int getBombs() {
        return bombs;
    }

    /**
     * Resets the game to its original state
     */
    public void restart() {
        for(int j = 0; j < height; j++) {
            for(int i = 0; i < width; i++) {
                fields[i][j].setCovered(true);
            }
        }
        markers = 0;
        coveredElements = width*height;
        over = false;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Generates a new field
     */
    public void generateGame() {
        fields = new Field[width][height];
        for(int j = 0; j < height; j++) {
            for(int i = 0; i < width; i++) {
                fields[i][j] = new Field();
            }
        }
        restart();
        generateMines();
        generateProx();
        printer();
        System.gc();
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Prints the uncovered field to the console
     */
    private void printer() {
        for(int j = 0; j < height; j++) {
            for(int i = 0; i < width; i++) {
                if(fields[i][j].isMine()) {
                    System.out.format("%-3s", "*");
                } else {
                    if(fields[i][j].getProx() > 0) {
                        System.out.format("%-3d", fields[i][j].getProx());
                    } else {
                        if(fields[i][j].isCovered()) {
                            System.out.format("%-3s", "0");
                        } else {
                            System.out.format("%-3s", "");
                        }

                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
