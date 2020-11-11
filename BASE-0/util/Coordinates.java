package util;

public class Coordinates {

    String position;
    int depth;

    public Coordinates(String pos, int d) {

        position = pos;
        depth = d;
    }

    public int getDepth() {
        return depth;
    }

    public String getPosition() {
        return position;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}