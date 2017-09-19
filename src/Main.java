import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //int[][] points = Problem1.distancePoints();
        CountTime countTime= new CountTime();
        Scheduling scheduling = new Scheduling();
        DealWithCollision dealWithCollision =new DealWithCollision();
        //countTime.setPlan(points);
        countTime.setPlanFromFile();
        //System.out.println("time = "+ countTime.naiveCalTime());
        //countTime.printPlan();
        ArrayList<PathTimeRecord>[] pathTimeRecordList = countTime.getPathInfo(1);

        //countTime.printPathTimeList(pathTimeRecordList);

        scheduling.schedulingByInverseOrder(pathTimeRecordList);
//        System.out.println("#############");
//        countTime.printPathTimeList(pathTimeRecordList);

        //dealWithCollision.printquery();
        for(int i=0;i<24;i++) {
            dealWithCollision.recordPathTimeByInverseOrder(pathTimeRecordList[i]);
        }

        dealWithCollision.printEdgeTrafficInfos();


    }
}
