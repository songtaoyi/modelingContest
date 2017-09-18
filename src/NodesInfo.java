import java.io.BufferedReader;
import java.io.FileReader;

public class NodesInfo {
    int [][] coordinate = new int[130][2];
    public NodesInfo() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("NodesInfo.txt"));
            String line = br.readLine();
            int lineNumber = 0;
            while (line != null) {
                //System.out.println("One line: "+line);
                String[] result = line.split("\t");
                coordinate[lineNumber][0] = Integer.parseInt(result[0]);
                coordinate[lineNumber][1] = Integer.parseInt(result[1]);
                line = br.readLine();
                lineNumber++;
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public int [] getCoordinate(int x){
        return coordinate[x];
    }
}
