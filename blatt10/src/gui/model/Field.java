package gui.model;

public class Field {

    boolean mine;
    int prox;
    boolean covered;

    public Field() {
        mine = false;
        prox = 0;
        covered = true;
    }

    public void increaseProx() {
        prox++;
    }

    public void setCovered(boolean b) {
        covered = b;
    }

    public void setMine(boolean b) {
        mine = b;
    }

    public boolean isCovered() {
        return covered;
    }

    public boolean isMine() {
        return mine;
    }

    public int getProx() {
        return prox;
    }

    public String getField() {
        if(mine) {
            return "*";
        } else {
            if(prox > 0) {
                return String.valueOf(prox);
            } else {
                return "";
            }
        }
    }

}
