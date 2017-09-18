//这个类记录了走的路径的信息，从哪个点走，到哪个点去，出发时间，到达时间，和最后附件二的要求很相似
public class PathTimeRecord {
    private int fromIndex;
    private int toIndex;
    private double fromTime;
    private double ArriveTime;

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }

    public double getFromTime() {
        return fromTime;
    }

    public void setFromTime(double fromTime) {
        this.fromTime = fromTime;
    }

    public double getArriveTime() {
        return ArriveTime;
    }

    public void setArriveTime(double arriveTime) {
        ArriveTime = arriveTime;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    @Override
    public String toString() {
        return "PathTimeRecord{" +
                "fromIndex=" + fromIndex +
                ", toIndex=" + toIndex +
                ", fromTime=" + fromTime +
                ", ArriveTime=" + ArriveTime +
                '}';
    }
}
