import java.util.ArrayList;

//这个类描述了在175条边上的交通信息，是否有车走，有车走，记录哪辆车，记录来的时间走的时间，记录方向，记录
public class EdgeTrafficInfo {
    private Edge edge = new Edge(-1,-1,0);
    private ArrayList<ArrayList<Double>> goTimeList = new ArrayList<ArrayList<Double>>();
    private ArrayList<ArrayList<Double>> backTimeList =new ArrayList<ArrayList<Double>>();
    private boolean isMainLoad = false;
    private int collisionNumber =0;

    public Edge getEdge() {
        return edge;
    }

    public boolean isMainLoad() {
        return isMainLoad;
    }

    public int getCollisionNumber() {
        return collisionNumber;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public ArrayList<ArrayList<Double>> getGoTimeList() {
        return goTimeList;
    }

    public double addGoTimeList(ArrayList<Double> timeEntry,boolean delay){
        double offset = 0;
        double addTime1 = timeEntry.get(0);
        double addTime2 = timeEntry.get(1);
        for(int i=0;i<goTimeList.size();i++){
            if(isCollisionInSameDirection(goTimeList.get(i),timeEntry)) {
                ArrayList<Double> exist = goTimeList.get(i);
                double existTime1 = exist.get(0).doubleValue();
                double existTime2 = exist.get(1).doubleValue();
                //延时是按照
                if (delay) {
                    offset = Math.abs(existTime2 - addTime2);
                    timeEntry.set(0, timeEntry.get(0) + offset);
                    timeEntry.set(1, timeEntry.get(1) + offset);
                    return offset + addBackTimeList(timeEntry, delay);
                } else {
                    offset = Math.abs(addTime1 - existTime1);
                    timeEntry.set(0, timeEntry.get(0) - offset);
                    timeEntry.set(1, timeEntry.get(1) - offset);
                    return offset + addGoTimeList(timeEntry, delay);
                }
            }
        }
        for(int i=0;i<backTimeList.size();i++){
            if(isCollisionInDifferentDirection(backTimeList.get(i),timeEntry)) {
                if(!isMainLoad) {
                    double existTime1 = backTimeList.get(i).get(0).doubleValue();
                    double existTime2 = backTimeList.get(i).get(1).doubleValue();

                    if(delay) {
                        offset = Math.abs(existTime2 - addTime1);
                        timeEntry.set(0, timeEntry.get(0) + offset);
                        timeEntry.set(1, timeEntry.get(1) + offset);
                        return offset + addBackTimeList(timeEntry, delay);
                    }
                    else {
                        offset = Math.abs(addTime2 - existTime1);
                        timeEntry.set(0, timeEntry.get(0) - offset);
                        timeEntry.set(1, timeEntry.get(1) - offset);
                        return offset + addGoTimeList(timeEntry, delay);
                    }
                }
            }
        }
        backTimeList.add(timeEntry);
        return offset;
    }

    //返回一个偏移量

    public double addBackTimeList(ArrayList<Double> timeEntry,boolean delay){
        double offset = 0;
        double addTime1 = timeEntry.get(0);
        double addTime2 = timeEntry.get(1);
        for(int i=0;i<backTimeList.size();i++){
            if(isCollisionInSameDirection(backTimeList.get(i),timeEntry)) {
                ArrayList<Double> exist = backTimeList.get(i);
                double existTime1 = exist.get(0).doubleValue();
                double existTime2 = exist.get(1).doubleValue();
                //延时是按照
                if (delay) {
                    offset = Math.abs(existTime2 - addTime2);
                    timeEntry.set(0, timeEntry.get(0) + offset);
                    timeEntry.set(1, timeEntry.get(1) + offset);
                    return offset + addBackTimeList(timeEntry, delay);
                } else {
                    offset = Math.abs(addTime1 - existTime1);
                    timeEntry.set(0, timeEntry.get(0) - offset);
                    timeEntry.set(1, timeEntry.get(1) - offset);
                    return offset + addBackTimeList(timeEntry, delay);
                }
            }
        }
        for(int i=0;i<goTimeList.size();i++){
            if(isCollisionInDifferentDirection(goTimeList.get(i),timeEntry)) {
                if(!isMainLoad) {
                    double existTime1 = goTimeList.get(i).get(0).doubleValue();
                    double existTime2 = goTimeList.get(i).get(1).doubleValue();

                    if(delay) {
                        offset = Math.abs(existTime2 - addTime1);
                        timeEntry.set(0, timeEntry.get(0) + offset);
                        timeEntry.set(1, timeEntry.get(1) + offset);
                        return offset + addBackTimeList(timeEntry, delay);
                    }
                    else {
                        offset = Math.abs(addTime2 - existTime1);
                        timeEntry.set(0, timeEntry.get(0) - offset);
                        timeEntry.set(1, timeEntry.get(1) - offset);
                        return offset + addBackTimeList(timeEntry, delay);
                    }
                }
            }
        }
        backTimeList.add(timeEntry);
        return offset;
    }

    public void setGoTimeList(ArrayList<ArrayList<Double>> goTimeList) {
        this.goTimeList = goTimeList;
    }

    public ArrayList<ArrayList<Double>> getBackTimeList() {
        return backTimeList;
    }

    public void setBackTimeList(ArrayList<ArrayList<Double>> backTimeList) {
        this.backTimeList = backTimeList;
    }

    public boolean getIsMainLoad() {
        return isMainLoad;
    }

    public void setIsMainLoad(boolean mainLoad) {
        isMainLoad = mainLoad;
    }


    private boolean isCollisionInSameDirection(ArrayList<Double> time1, ArrayList<Double> time2){
        int time1in =(int)(time1.get(0).doubleValue()*100);
        int time1out =(int)(time1.get(1).doubleValue()*100);
        int time2in =(int)(time2.get(0).doubleValue()*100);
        int time2out =(int)(time2.get(1).doubleValue()*100);
        if(time1in < time2in && time1out > time2out) {
            System.out.println("Collition in same Direction");
            return true;
        }
        if(time1in > time2in && time1out < time2out) {
            System.out.println("Collition in same Direction");
            return true;
        }
        return false;
    }
    private boolean isCollisionInDifferentDirection(ArrayList<Double> time1, ArrayList<Double> time2) {
        int time1in =(int)(time1.get(0).doubleValue()*100);
        int time1out =(int)(time1.get(1).doubleValue()*100);
        int time2in =(int)(time2.get(0).doubleValue()*100);
        int time2out =(int)(time2.get(1).doubleValue()*100);
        if(time1out <= time2in)
            return false;
        if(time2out <= time1in)
            return false;
        System.out.println("Collition in different Direction");
        return true;
    }


    public void printCollistionNumber(){
        System.out.println("CollisionNumber is "+ collisionNumber);
    }

    @Override
    public String toString() {
        return "EdgeTrafficInfo{" +
                "edge=" + edge +
                ", goTimeList=" + goTimeList +
                ", backTimeList=" + backTimeList +
                ", isMainLoad=" + isMainLoad +
                '}';
    }



}
