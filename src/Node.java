import java.util.ArrayList;
import java.util.List;

public class Node {
    private double distanceFromSource = Integer.MAX_VALUE;
    private List<Integer> path=new ArrayList<Integer>();
    private boolean visited;
    private ArrayList<Edge> edges = new ArrayList<Edge>(); // now we must create edges

    public double getDistanceFromSource() {
        return distanceFromSource;
    }
    public void setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }
    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public ArrayList<Edge> getEdges() {
        return edges;
    }
    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
    public List<Integer> getPath() {
        return path;
    }
    public void setPath(List<Integer> path) {
        this.path = path;
    }
}