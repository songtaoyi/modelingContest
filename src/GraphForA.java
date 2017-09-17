import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GraphForA {
    private double distance[][] = new double[130][130];
    private List<Integer> pathMatrix [][] = new ArrayList[130][130];
    private ParseNodeName parseNodeName = new ParseNodeName();
    private Edge[] edges;

    public GraphForA(){

        edges = inputFromFile();
        for (int i = 0; i < 130; i++) {
            Graph g = new Graph(edges);
            g.calculateShortestDistances(i);
            //g.printResult(0); // let's try it !
            distance[i] = g.getDistance();
            pathMatrix[i] = g.getPath();

        }
    }

    private Edge[] inputFromFile() {
        Edge[] edges = new Edge[175];
        try {
            BufferedReader br = new BufferedReader(new FileReader("carA.txt"));
            String line = br.readLine();
            int edgeNumber = 0;
            while (line != null) {
                // System.out.println("One line: "+line);
                String[] result = line.split("\t");
                String from = result[0];
                String to = result[1];
                Double distance = Double.parseDouble(result[2]);
                edges[edgeNumber] = new Edge(parseNodeName.getIntFromString(from), parseNodeName.getIntFromString(to), distance);
                line = br.readLine();
                edgeNumber++;
            }
            br.close();
        } catch (Exception e) {
        }
        return edges;
    }

    public double calTime(int formIndex, int toIndex) {
        return distance[formIndex][toIndex];
    }
    public List<Integer> getPath(int fromIndex, int toIndex){
        return pathMatrix[fromIndex][toIndex];
    }
}
