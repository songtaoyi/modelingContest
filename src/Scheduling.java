import java.util.ArrayList;

public class Scheduling {
    public void schedulingByInverseOrder(ArrayList<PathTimeRecord>[] arrayListsOfPathTimeRecord){
        CountTime countTime= new CountTime();
        double [] pathTotalTime = countTime.getPathTotalTime(arrayListsOfPathTimeRecord);
        double max = 0;
        for(int i=0;i<pathTotalTime.length;i++){
            if(max <pathTotalTime[i])
                max = pathTotalTime[i];
        }

        for(int countPath=0;countPath<arrayListsOfPathTimeRecord.length;countPath++){
            double tempTime = max;
            for(int i = arrayListsOfPathTimeRecord[countPath].size()-1;i>=0;i--){
                PathTimeRecord pathTimeRecord = arrayListsOfPathTimeRecord[countPath].get(i);
                double internal = pathTimeRecord.getArriveTime()-pathTimeRecord.getFromTime();
                pathTimeRecord.setArriveTime(tempTime);
                tempTime -= internal;
                pathTimeRecord.setFromTime(tempTime);
            }
        }
    }
}
