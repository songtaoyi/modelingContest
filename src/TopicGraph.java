import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class TopicGraph {
    public static double distance[ ][ ] = new double[130][ ];


    private HashMap<String, Integer> constructNodeMap(){

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

    public TopicGraph() {
        for(int i=0;i<130;i++){
            distance[i] = new double [130];
        }
        HashMap<String, Integer> nodeStringtoIndex = constructNodeMap();
        /*
        String [] nodeIntToString = new String [130];
        for (HashMap.Entry<String, Integer> entry : nodeStringtoIndex.entrySet()) {
            nodeIntToString[entry.getValue()]= entry.getKey();
        }
        */
        Edge [] edges = inputFromFile(nodeStringtoIndex);


        for(int i=0;i<130;i++) {
            Graph g = new Graph(edges);
            g.calculateShortestDistances(i);
            //g.printResult(0); // let's try it !
            distance[i] = g.getDistance();
        }


        //System.out.println(g.getDistance().toString());
/*
        for(int i=0;i<1;i++) {
            g.calculateShortestDistances(i);
            double []tmp = g.getDistance();
            for(int j=0;j<130;j++){
                //System.out.println(tmp[j]);
            }
            distance[i] = g.getDistance();
        }
        /*
        for(int i=0;i<130;i++){
            System.out.println(distance[0][i]);
        }
        */
    }

    private Edge[] inputFromFile(HashMap<String, Integer> nodeStringtoIndex){
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

    public double calDistance(int formIndex,int toIndex){
        return distance[formIndex][toIndex];
    }

    public static void main(String[] args){
        TopicGraph topicGraph = new TopicGraph();

//        for(int i=0;i<130;i++){
//            System.out.println();
//            System.out.println("the distance List from "+i);
//            for(int j=0;j<130;j++){
//                System.out.print(topicGraph.distance[i][j]);
//                System.out.print(" ");
//            }
//        }
        System.out.println(topicGraph.calDistance(68,69));
    }
}
