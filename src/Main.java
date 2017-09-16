import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main {
    private static HashMap<String, Integer> constructNodeMap(){

        HashMap<String, Integer> nodeStringtoIndex = new HashMap<String, Integer>();
        Integer index = 0;
        nodeStringtoIndex.put("D1",index);
        index++;
        nodeStringtoIndex.put("D2",index);
        String str ="Z0";
        for(Integer i=1;i<=6;i++){
            index++;
            nodeStringtoIndex.put(str+i.toString(),index);
        }
        str ="F0";
        for(Integer i=1;i<=9;i++){
            index++;
            nodeStringtoIndex.put(str+i.toString(),index);
        }
        str = "F";
        for(Integer i=10;i<=60;i++){
            index++;
            nodeStringtoIndex.put(str+i.toString(),index);
        }
        str ="J0";
        for(Integer i=1;i<=9;i++){
            index++;
            nodeStringtoIndex.put(str+i.toString(),index);
        }
        str = "J";
        for(Integer i=10;i<=62;i++){
            index++;
            nodeStringtoIndex.put(str+i.toString(),index);
        }
        return nodeStringtoIndex;
    }

    private static Edge[] inputFromFile(HashMap<String, Integer> nodeStringtoIndex){
        Edge[] edges= new Edge[175];
        try {
            BufferedReader br = new BufferedReader(new FileReader("edges.txt"));
            String line = br.readLine();
            int edgeNumber =0;
            while (line != null) {
                //System.out.println("One line: "+line);
                String[] result = line.split("\t");
                String from = result[0];
                String to = result[1];
                Double distance = Double.parseDouble(result[2]);
                edges[edgeNumber] = new Edge(nodeStringtoIndex.get(from), nodeStringtoIndex.get(to), distance);
                line = br.readLine();
                edgeNumber++;
            }
            br.close();
        } catch (Exception e){
        }
        return edges;
    }

    public static void main(String[] args) {
        /*
        Edge[] edges = {
                new Edge(0, 2, 1), new Edge(0, 3, 4), new Edge(0, 4, 2),
                new Edge(0, 1, 3), new Edge(1, 3, 2), new Edge(1, 4, 3),
                new Edge(1, 5, 1), new Edge(2, 4, 1), new Edge(3, 5, 4),
                new Edge(4, 5, 2), new Edge(4, 6, 7), new Edge(4, 7, 2),
                new Edge(5, 6, 4), new Edge(6, 7, 5)
        };
        Graph g = new Graph(edges);
        g.calculateShortestDistances(1);
        g.printResult(1); // let's try it !
*/


        HashMap<String, Integer> nodeStringtoIndex = constructNodeMap();


        String [] nodeIntToString = new String [130];

        for (HashMap.Entry<String, Integer> entry : nodeStringtoIndex.entrySet()) {
            nodeIntToString[entry.getValue()]= entry.getKey();
        }

        Edge [] edges = inputFromFile(nodeStringtoIndex);

        System.out.println(edges.length);

        Graph g = new Graph(edges);
        g.calculateShortestDistances(0);
        g.printResult(0); // let's try it !



        /*
        for(int i=0;i<130;i++){
            System.out.println(nodeIntToString[i]);
        }
        */



    }
}