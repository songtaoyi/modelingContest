import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //int[][] points = Problem1.distancePoints();
        CountTime countTime= new CountTime();
        //countTime.setPlan(points);
        countTime.setPlanFromFile();
        System.out.println("time = "+ countTime.naiveCalTime());
        //countTime.printPlan();
        GraphForA graphForA =new GraphForA();
        //System.out.println(graphForA.getPath(0,1).toString());
        // ;
        NodesInfo nodesInfo =new NodesInfo();
        //System.out.println(nodesInfo.getCoordinate(1)[0]+"   "+nodesInfo.getCoordinate(1)[1]);
        ArrayList<PathTimeRecord>[] pathTimeRecordList = countTime.getPathInfo();
        System.out.println(pathTimeRecordList.length);
        System.out.println(pathTimeRecordList[1].toString());
        double time =0;
        for(int i=0;i<24;i++){
            time+=pathTimeRecordList[i].get(pathTimeRecordList[i].size()-1).getArriveTime();
        }
        System.out.println(time/60);
    }
}
