import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountTime {
    private int[][] plan =new int [24][4];
    private TopicDistanceGraph topicDistanceGraph = new TopicDistanceGraph();
    private ParseNodeName parseNodeName =new ParseNodeName();
    private GraphForA graphForA= new GraphForA();
    private GraphForB graphForB= new GraphForB();
    private GraphForC graphForC= new GraphForC();
    private NodesInfo nodesInfo =new NodesInfo();

    public void setPlan(int[][] plan) {
        this.plan = plan;
    }
    public void printPlan(){
        for(int i=0;i<24;i++){
            for(int j=0;j<4;j++){
                System.out.print(plan[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void setPlanFromFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("plan.txt"));
            String line = br.readLine();
            int lineNumber = 0;
            while (line != null) {
                String[] result = line.split("\t");
                String base = result[0];
                String firstShoot = result[1];
                String reload = result[2];
                String secondShoot = result[3];
                plan[lineNumber][0] = parseNodeName.getIntFromString(base);
                plan[lineNumber][1] = parseNodeName.getIntFromString(firstShoot);
                plan[lineNumber][2] = parseNodeName.getIntFromString(reload);
                plan[lineNumber][3] = parseNodeName.getIntFromString(secondShoot);
                line = br.readLine();
                lineNumber++;
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void getPathOfCertainCar(int number){
        System.out.println("From ready to first shoot:");
        System.out.println(topicDistanceGraph.getPath(plan[number][0],plan[number][1]).toString());
        System.out.println("From first shoot to reload:");
        System.out.println(topicDistanceGraph.getPath(plan[number][1],plan[number][2]).toString());
        System.out.println("From reload to second shoot:");
        System.out.println(topicDistanceGraph.getPath(plan[number][2],plan[number][3]).toString());

    }

    //Do not concern about the different speed of road.
    public double naiveCalTime(){
        double time = 0;
        for(int i=0;i<6;i++){
            double temDistance = 0;
            temDistance += graphForA.calTime(plan[i][0],plan[i][1]);
            temDistance += graphForA.calTime(plan[i][1],plan[i][2]);
            temDistance += graphForA.calTime(plan[i][2],plan[i][3]);
            //time += temDistance/45;
            time += temDistance;
        }
        for(int i=6;i<12;i++){
            double temDistance = 0;
            temDistance += graphForB.calTime(plan[i][0],plan[i][1]);
            temDistance += graphForB.calTime(plan[i][1],plan[i][2]);
            temDistance += graphForB.calTime(plan[i][2],plan[i][3]);
            //time += temDistance/35;
            time += temDistance;
        }
        for(int i=12;i<24;i++){
            double temDistance = 0;
            temDistance += graphForC.calTime(plan[i][0],plan[i][1]);
            temDistance += graphForC.calTime(plan[i][1],plan[i][2]);
            temDistance += graphForC.calTime(plan[i][2],plan[i][3]);
            //time += temDistance/30;
            time += temDistance;
        }
        return time;
    }


    public ArrayList<PathTimeRecord> [] getPathInfo(int path1OrPath2OrPath3) {
        ArrayList[] pathTimeRecordList = new ArrayList[24];
        for (int i = 0; i < 24; i++) {
            List<Integer> path =new ArrayList<Integer>();
            if (i < 6) {
                path = graphForA.getPath(plan[i][path1OrPath2OrPath3-1], plan[i][path1OrPath2OrPath3]);
            }
            if(i>=6 &&i<12) {
                path = graphForB.getPath(plan[i][path1OrPath2OrPath3-1], plan[i][path1OrPath2OrPath3]);
            }
            if(i>=12 && i<24) {
                path = graphForC.getPath(plan[i][path1OrPath2OrPath3-1], plan[i][path1OrPath2OrPath3]);
            }
                pathTimeRecordList[i] = new ArrayList<PathTimeRecord>();
                double time = 0;
                for (int j = 0; j < path.size() - 1; j++) {
                    PathTimeRecord pathTimeRecord = new PathTimeRecord();
                    pathTimeRecord.setFromIndex(path.get(j));
                    pathTimeRecord.setToIndex(path.get(j + 1));
                    pathTimeRecord.setFromTime(time);
                    if (i < 6) {
                        time += graphForA.calTime(path.get(j), path.get(j + 1));
                    }
                    if(i>=6 &&i<12) {
                        time += graphForB.calTime(path.get(j), path.get(j + 1));
                    }
                    if(i>=12 && i<24) {
                        time += graphForC.calTime(path.get(j), path.get(j + 1));
                    }
                    pathTimeRecord.setArriveTime(time);
                    pathTimeRecordList[i].add(pathTimeRecord);
                }
            }
        return pathTimeRecordList;
    }

    public double [] getPathTotalTime(ArrayList<PathTimeRecord> [] arrayListsOfPathTimeRecord){
        double[] time = new double[arrayListsOfPathTimeRecord.length];
        for(int i=0;i<arrayListsOfPathTimeRecord.length;i++){
            time[i] = arrayListsOfPathTimeRecord[i].get(arrayListsOfPathTimeRecord[i].size()-1).getArriveTime();
        }
        return time;
    }
    public void printPathTimeList(ArrayList<PathTimeRecord> [] arrayListsOfPathTimeRecord){
        for(int i=0;i<arrayListsOfPathTimeRecord.length;i++){
            System.out.print("Path of car "+i+" : ");
            System.out.println(arrayListsOfPathTimeRecord[i].toString());
        }
    }


    public void outputPathDataToFileForMatlabDraw(){
        try {
            File writename = new File("path-FirstPart.txt");
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("The path From waiting area to first shoot\n");
            for(int i=0;i<24;i++) {
                //out.write();
                List<Integer> path = graphForA.getPath(plan[i][0], plan[i][1]);
                out.write(carName(i) + " x Coordinate\t");
                for (int j = 0; j < path.size(); j++) {
                    out.write(nodesInfo.getCoordinate(path.get(j))[0] + "\t");
                }
                out.write("\n" + carName(i) + " y Coordinate\t");
                for (int j = 0; j < path.size(); j++) {
                    out.write(nodesInfo.getCoordinate(path.get(j))[1] + "\t");
                }
                out.write("\n");
            }
            out.flush();
            out.close();
        }catch (Exception e){
            System.out.println("EXCEPIYON !!!");
        }
        /********************************************************************************************/
        try {
            File writename = new File("path-SecondPart.txt");
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("The path From first shoot to re-load area\n");
            for(int i=0;i<24;i++) {
                //out.write();
                List<Integer> path = graphForA.getPath(plan[i][1], plan[i][2]);
                out.write(carName(i) + " x Coordinate\t");
                for (int j = 0; j < path.size(); j++) {
                    out.write(nodesInfo.getCoordinate(path.get(j))[0] + "\t");
                }
                out.write("\n" + carName(i) + " y Coordinate\t");
                for (int j = 0; j < path.size(); j++) {
                    out.write(nodesInfo.getCoordinate(path.get(j))[1] + "\t");
                }
                out.write("\n");
            }
            out.flush();
            out.close();
        }catch (Exception e){
            System.out.println("EXCEPIYON !!!");
        }
        /********************************************************************************************/
        try {
            File writename = new File("path-ThirdPart.txt");
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("The path from re-load area to second shoot\n");
            for(int i=0;i<24;i++) {
                //out.write();
                List<Integer> path = graphForA.getPath(plan[i][2], plan[i][3]);
                out.write(carName(i) + " x Coordinate\t");
                for (int j = 0; j < path.size(); j++) {
                    out.write(nodesInfo.getCoordinate(path.get(j))[0] + "\t");
                }
                out.write("\n" + carName(i) + " y Coordinate\t");
                for (int j = 0; j < path.size(); j++) {
                    out.write(nodesInfo.getCoordinate(path.get(j))[1] + "\t");
                }
                out.write("\n");
            }
            out.flush();
            out.close();
        }catch (Exception e){
            System.out.println("EXCEPIYON !!!");
        }


    }
    String carName(int number){
        switch(number){
            case 0:
                return "A01";
            case 1:
                return "A02";
            case 2:
                return "A03";
            case 3:
                return "A04";
            case 4:
                return "A05";
            case 5:
                return "A06";
            case 6:
                return "B01";
            case 7:
                return "B02";
            case 8:
                return "B03";
            case 9:
                return "B04";
            case 10:
                return "B05";
            case 11:
                return "B06";
            case 12:
                return "C01";
            case 13:
                return "C02";
            case 14:
                return "C03";
            case 15:
                return "C04";
            case 16:
                return "C05";
            case 17:
                return "C06";
            case 18:
                return "C07";
            case 19:
                return "C08";
            case 20:
                return "C09";
            case 21:
                return "C10";
            case 22:
                return "C11";
            case 23:
                return "C12";
        }
        return null;
    }
}
