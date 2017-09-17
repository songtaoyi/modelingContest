import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class CountTime {
    private int[][] plan =new int [24][4];
    private TopicDistanceGraph topicDistanceGraph = new TopicDistanceGraph();
    private ParseNodeName parseNodeName =new ParseNodeName();
    private GraphForA graphForA= new GraphForA();
    private GraphForB graphForB= new GraphForB();
    private GraphForC graphForC= new GraphForC();

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
            temDistance += graphForA.calTime(plan[i][0],plan[i][1]);
            temDistance += graphForA.calTime(plan[i][1],plan[i][2]);
            temDistance += graphForA.calTime(plan[i][2],plan[i][3]);
            //time += temDistance/35;
            time += temDistance;
        }
        for(int i=12;i<24;i++){
            double temDistance = 0;
            temDistance += graphForA.calTime(plan[i][0],plan[i][1]);
            temDistance += graphForA.calTime(plan[i][1],plan[i][2]);
            temDistance += graphForA.calTime(plan[i][2],plan[i][3]);
            //time += temDistance/30;
            time += temDistance;
        }
        return time;
    }
}
