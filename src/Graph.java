import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.List;

// now we must create graph object and implement dijkstra algorithm
public class Graph {
    private Node[] nodes;
    private int noOfNodes;
    private Edge[] edges;
    private int noOfEdges;
    public Graph(Edge[] edges) {
        this.edges = edges;
        // create all nodes ready to be updated with the edges
        this.noOfNodes = calculateNoOfNodes(edges);
        this.nodes = new Node[this.noOfNodes];
        for (int n = 0; n < this.noOfNodes; n++) {
            this.nodes[n] = new Node();
        }
        // add all the edges to the nodes, each edge added to two nodes (to and from)
        this.noOfEdges = edges.length;
        for (int edgeToAdd = 0; edgeToAdd < this.noOfEdges; edgeToAdd++) {
            this.nodes[ edges[edgeToAdd].getFromNodeIndex() ].getEdges().add(edges[edgeToAdd]);
            this.nodes[ edges[edgeToAdd].getToNodeIndex() ].getEdges().add(edges[edgeToAdd]);
        }
    }
    // calculate the number of nodes
    private int calculateNoOfNodes(Edge[] edges) {
        int noOfNodes = 0;
        for (Edge e : edges) {
            if (e.getToNodeIndex() > noOfNodes)
                noOfNodes = e.getToNodeIndex();
            if (e.getFromNodeIndex() > noOfNodes)
                noOfNodes = e.getFromNodeIndex();
        }
        noOfNodes++;
        return noOfNodes;
    }
    // next video to implement the Dijkstra algorithm !!!
    public void calculateShortestDistances(int source) {
        // node 0 as source

        List<Integer> tmp = new ArrayList<>();
//        for (int i = 0; i < this.nodes.length; i++){
//            //nodes[i].setPath(null);
//            nodes[i].setDistanceFromSource(Integer.MAX_VALUE);
//            nodes[i].setPath(tmp);
//        }

        this.nodes[source].setDistanceFromSource(0);

        nodes[source].getPath().add(source);
        int nextNode = source;
        // visit every node
        for (int i = 0; i < this.nodes.length; i++) {
            //System.out.println("Count "+i);
            // loop around the edges of current node
            ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
            for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
                int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode);
                // only if not visited
                if (!this.nodes[neighbourIndex].isVisited()) {
                    double tentative = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdges.get(joinedEdge).getLength();
                    if (tentative < nodes[neighbourIndex].getDistanceFromSource()) {
                        nodes[neighbourIndex].setDistanceFromSource(tentative);

                        List<Integer> path = new ArrayList<>(nodes[nextNode].getPath());
                        path.add(neighbourIndex);
                        nodes[neighbourIndex].setPath(path);
                    }
                }
            }
            //System.out.println(currentNodeEdges.toString());
            // all neighbours checked so node visited
            nodes[nextNode].setVisited(true);
            // next node must be with shortest distance
            nextNode = getNodeShortestDistanced(nextNode);
        }
    }
    // now we're going to implement this method in next part !
    private int getNodeShortestDistanced(int nextNode) {
        int storedNodeIndex = 0;
        double storedDist = Integer.MAX_VALUE;
        for (int i = 0; i < this.nodes.length; i++) {
            double currentDist = this.nodes[i].getDistanceFromSource();
            if (!this.nodes[i].isVisited() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }
    // display result
    public void printResult(int source) {
        String output = "Number of nodes = " + this.noOfNodes;
        output += "\nNumber of edges = " + this.noOfEdges;
        for (int i = 0; i < this.nodes.length; i++) {
            output += ("\nThe shortest distance from node "+ source + " to node " + i + " is " + nodes[i].getDistanceFromSource());
            output +=("\n" + nodes[i].getPath().toString());
        }
        System.out.println(output);
    }
    public double[] getDistance(){
        double [] distanceList = new double[130];
        for(int i=0;i<130;i++){

            distanceList[i] = nodes[i].getDistanceFromSource();
            //System.out.println(distanceList[i]);
            //System.out.println(distanceList.toString());
            //System.out.println(i);
        }
        //System.out.println("hi");
        //System.out.println(distanceList.toString());
//        for(int i=0;i<130;i++){
//            System.out.println("dis = "+i+" "+distanceList[i]);
//        }
        return distanceList;
    }

    public List<Integer>[] getPath(){
        List<Integer> pathList []= new ArrayList[130];
        for(int i=0;i<130;i++){
            pathList[i] = nodes[i].getPath();
        }
        return pathList;
    }

    public Node[] getNodes() {
        return nodes;
    }
    public int getNoOfNodes() {
        return noOfNodes;
    }
    public Edge[] getEdges() {
        return edges;
    }
    public int getNoOfEdges() {
        return noOfEdges;
    }
}