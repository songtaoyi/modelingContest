public class Main {
    public static void main(String[] args){
        //int[][] points = Problem1.distancePoints();
        CountTime countTime= new CountTime();
        //countTime.setPlan(points);
        countTime.setPlanFromFile();
        System.out.println("time = "+ countTime.naiveCalTime());
        //countTime.printPlan();
        GraphForA graphForA =new GraphForA();
        System.out.println(graphForA.getPath(0,1).toString());
    }
}
