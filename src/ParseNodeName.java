import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParseNodeName {
    HashMap<String, Integer> nodeStringtoIndex;
    String [] nodeIntToString = new String [130];
    private HashMap<String, Integer> constructNodeMap() {
        HashMap<String, Integer> nodeStringtoIndex = new HashMap<String, Integer>();
        Integer index = 0;
        nodeStringtoIndex.put("D1", index);
        index++;
        nodeStringtoIndex.put("D2", index);
        String str = "Z0";
        for (Integer i = 1; i <= 6; i++) {
            index++;
            nodeStringtoIndex.put(str + i.toString(), index);
        }
        str = "F0";
        for (Integer i = 1; i <= 9; i++) {
            index++;
            nodeStringtoIndex.put(str + i.toString(), index);
        }
        str = "F";
        for (Integer i = 10; i <= 60; i++) {
            index++;
            nodeStringtoIndex.put(str + i.toString(), index);
        }
        str = "J0";
        for (Integer i = 1; i <= 9; i++) {
            index++;
            nodeStringtoIndex.put(str + i.toString(), index);
        }
        str = "J";
        for (Integer i = 10; i <= 62; i++) {
            index++;
            nodeStringtoIndex.put(str + i.toString(), index);
        }
        return nodeStringtoIndex;
    }

    public ParseNodeName(){
        nodeStringtoIndex = constructNodeMap();
        for (HashMap.Entry<String, Integer> entry : nodeStringtoIndex.entrySet()) {
            nodeIntToString[entry.getValue()]= entry.getKey();
        }
    }
    public String getStringFromInt(int index){
        return nodeIntToString[index];

    }
    public int getIntFromString(String name){
        return nodeStringtoIndex.get(name);
    }
    public static void main(String [] args){
        ParseNodeName parseNodeName =new ParseNodeName();
        System.out.println(parseNodeName.getIntFromString("D2"));
    }
}
