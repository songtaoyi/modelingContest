import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Problem1 {
    private static final int FNUM = 60;
    private static final int MNUM = 24;
    private static final int LNUM = 2;
    private static final int ZNUM = 6;
    private static final int DNUM = 2;
    private static final int MAX_ZNUM = 5;


    private static int[] d = {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};


    public static void main(String[] args) {
        distancePoints();
    }

    public static int[][] distancePoints() {
        TopicDistanceGraph topicDistanceGraph = new TopicDistanceGraph();

        HashSet<Integer> F = new HashSet<Integer>();
        for (int i = 8; i < 8 + FNUM; i++)
            F.add(i);


        ArrayList<int[]> pqs = new ArrayList<int[]>();

        for (int d = 0; d < DNUM; d++) {
            int finalD = d;
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(MNUM / 2, ((o1, o2) -> (int)(topicDistanceGraph.calDistance(finalD, o2) - topicDistanceGraph.calDistance(finalD, o1))));
            Object[] FArray = F.toArray();
            for (Object o : FArray) {
                Integer i = (Integer) o;
                if (pq.size() < MNUM / 2) {
                    pq.add(i);
                    F.remove(i);
                } else {
                    int maxInQueue = pq.peek();
                    if (topicDistanceGraph.calDistance(d, maxInQueue) > topicDistanceGraph.calDistance(d, i)) {
                        System.out.println(d + " " + " " + maxInQueue + " " + topicDistanceGraph.calDistance(d, maxInQueue) );
                        System.out.println(d + " " + " " + i + " " + topicDistanceGraph.calDistance(d, i) );
                        F.add(pq.poll());
                        System.out.println(pq.size());
                        pq.add(i);
                        F.remove(i);
                    }
                }
            }

            int[] order = new int[MNUM / 2];
            for (int i = 0; i < MNUM / 2; i++) {
                order[i] = pq.poll();
                System.out.print(order[i] + " ");
            }
            System.out.println();
            System.out.println(F.size());
            pqs.add(order);
        }

        int[] first = new int[MNUM];
        for (int i = 0; i < 3; i++) {
            first[i] = pqs.get(0)[i];
        }
        for (int i = 3; i < 6; i++) {
            first[i] = pqs.get(1)[i - 3];
        }
        for (int i = 6; i < 9; i++) {
            first[i] = pqs.get(0)[i - 3];
        }
        for (int i = 9; i < 12; i++) {
            first[i] = pqs.get(1)[i - 6];
        }
        for (int i = 12; i < 18; i++) {
            first[i] = pqs.get(0)[i - 6];
        }
        for (int i = 18; i < 24; i++) {
            first[i] = pqs.get(1)[i - 12];
        }

        System.out.println("------------------------");
        for (int i : first
                ) {
            System.out.println(i);
        }

        int[] second = new int[MNUM];
        int[] znum = new int[ZNUM];
        for (int i = first.length - 1; i >= 0; i--) {
            double min = Double.MAX_VALUE;
            int minz = -1;
            for (int z = 2; z < 2 + ZNUM; z++) {
                if (znum[z - 2] >= MAX_ZNUM)
                    continue;
                if (topicDistanceGraph.calDistance(first[i], z) < min) {
                    min = topicDistanceGraph.calDistance(first[i], z);
                    minz = z;
                }
            }
            znum[minz - 2]++;
            second[i] = minz;

        }

        System.out.println("------------------------");
        for (int i : second
                ) {
            System.out.println(i);
        }

        System.out.println("------------------------");

        ArrayList<PriorityQueue<Integer>> pqsz = new ArrayList<PriorityQueue<Integer>>();
        for (int z = 0; z < ZNUM; z++) {
            if (znum[z] == 0) {
                pqsz.add(null);
                continue;
            }
            int finalZ = z;
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(znum[z], ((o1, o2) -> (int) topicDistanceGraph.calDistance(finalZ +2, o2) - (int) topicDistanceGraph.calDistance(finalZ +2, o1)));
            Object[] FArray = F.toArray();
            for (Object o : FArray) {
                Integer i = (Integer) o;
                if (pq.size() < znum[z]) {
                    pq.add(i);
                    F.remove(i);
                } else {
                    int maxInQueue = pq.peek();
                    if (topicDistanceGraph.calDistance(z + 2, maxInQueue) > topicDistanceGraph.calDistance(z + 2, i)) {
                        F.add(pq.poll());
                        pq.add(i);
                        F.remove(i);
                    }
                }
            }


            System.out.println();
            System.out.println(F.size());
            pqsz.add(pq);
        }

        int[] third = new int[MNUM];

        for (int i = 0; i < third.length; i++) {
            third[i] = pqsz.get(second[i] - 2).poll();
        }

        System.out.println("------------------------");
        for (int i : third
                ) {
            System.out.println(i);
        }

        int[][] points = new int[MNUM][4];
        for (int i = 0; i < MNUM; i++) {
            points[i][0] = d[i];
            points[i][1] = first[i];
            points[i][2] = second[i];
            points[i][3] = third[i];
            System.out.println(points[i][0] + "\t" + points[i][1] + "\t" + points[i][2] + "\t" + points[i][3]);
        }

        return points;

    }
}
