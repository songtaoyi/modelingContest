import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PushbackInputStream;
import java.util.ArrayList;


public class DealWithCollision {
    ParseNodeName parseNodeName = new ParseNodeName();
    EdgeTrafficInfo [] edgeTrafficInfos = new EdgeTrafficInfo[175];
    int queryOfEdgeTrafficInfoIndex[][] = new int [130][130];
    public void deal(PathTimeRecord pathTimeRecord){
    }

    public DealWithCollision(){
        for(int i=0;i<175;i++){
            edgeTrafficInfos[i] = new EdgeTrafficInfo();
        }
        for(int i=0;i<130;i++){
            for(int j=0;j<130;j++){
                queryOfEdgeTrafficInfoIndex[i][j] = -1;
            }
        }
        inputFromFile();

    }

    //double [] allowForwardAdjustment(pathTimeRecord);
    public void printquery(){
        for(int i=0;i<130;i++){
            for(int j=0;j<130;j++){
                System.out.print(queryOfEdgeTrafficInfoIndex[i][j]);
            }
            System.out.println();
        }
    }

    public void recordPathTimeByInverseOrder(ArrayList<PathTimeRecord> pathTimeRecordArrayList){
        //如果遇到冲突，向前移
        boolean delay =false;
        for(int i=pathTimeRecordArrayList.size()-1;i>=0;i--){
            PathTimeRecord pathTimeRecord= pathTimeRecordArrayList.get(i);
            int index = queryOfEdgeTrafficInfoIndex[pathTimeRecord.getFromIndex()][pathTimeRecord.getToIndex()];
            ArrayList<Double> timeEntry =new ArrayList<Double>();
            timeEntry.add(Double.valueOf(pathTimeRecord.getFromTime()) );
            timeEntry.add(Double.valueOf(pathTimeRecord.getArriveTime()) );
            //根据走的方向不同，添加不同的时间
            double offset =0;
            if(pathTimeRecord.getFromIndex() ==edgeTrafficInfos[index].getEdge().getFromNodeIndex() &&
                    pathTimeRecord.getToIndex() ==edgeTrafficInfos[index].getEdge().getToNodeIndex()){
                offset  = edgeTrafficInfos[index].addGoTimeList(timeEntry,delay);
            }

            if(pathTimeRecord.getFromIndex() ==edgeTrafficInfos[index].getEdge().getToNodeIndex() &&
                    pathTimeRecord.getToIndex() ==edgeTrafficInfos[index].getEdge().getFromNodeIndex() ) {
                offset = edgeTrafficInfos[index].addBackTimeList(timeEntry, delay);
            }
            if(offset !=0) {
                System.out.println(offset);
            }

        }
    }

    private void inputFromFile() {
        Edge[] edges = new Edge[175];
        try {
            BufferedReader br = new BufferedReader(new FileReader("edgesAndIsMainLoad.txt"));
            String line = br.readLine();
            int edgeNumber = 0;
            while (line != null) {
                //System.out.println("One line: "+line);
                String[] result = line.split("\t");
                String from = result[0];
                String to = result[1];
                Double distance = Double.parseDouble(result[2]);
                int isMainLoad = Integer.parseInt(result[3]);
                edges[edgeNumber] = new Edge(parseNodeName.getIntFromString(from), parseNodeName.getIntFromString(to), distance);
                queryOfEdgeTrafficInfoIndex[parseNodeName.getIntFromString(from)][parseNodeName.getIntFromString(to)] = edgeNumber;
                queryOfEdgeTrafficInfoIndex[parseNodeName.getIntFromString(to)][parseNodeName.getIntFromString(from)] = edgeNumber;
                line = br.readLine();

                edgeTrafficInfos[edgeNumber].setEdge(edges[edgeNumber]);
                if(isMainLoad ==0)
                    edgeTrafficInfos[edgeNumber].setIsMainLoad(false);
                else
                    edgeTrafficInfos[edgeNumber].setIsMainLoad(true);

                edgeNumber++;
            }
            br.close();
        } catch (Exception e) {
        }
        for(int i=0;i<175;i++){
            edgeTrafficInfos[i].setEdge(edges[i]);
        }
    }


    public void printEdgeTrafficInfos(){
        for(int i=0;i<175;i++){
            if(edgeTrafficInfos[i].getCollisionNumber()>0) {
                System.out.println("************************ Edge " + i + "   info*********");
                //System.out.println(edgeTrafficInfos[i].toString());
                edgeTrafficInfos[i].printCollistionNumber();
                System.out.println(edgeTrafficInfos[i].isMainLoad());
            }
        }
    }
}
