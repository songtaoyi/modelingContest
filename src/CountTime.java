import java.io.*;
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

    public void outputTheLineToFile(){
        try {
            String carName = "A0";
            File writename = new File("Attachment2.txt");
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("The plan\n");
            for(int i=0;i<24;i++){
                out.write(carName(i)+"\t");
                //out.write();
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
                return "C02";
        }
        return null;
    }
}
