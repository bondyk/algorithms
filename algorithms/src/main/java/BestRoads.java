import java.util.*;

/**
 * There are N cities numbered 0 to N-1. The j-th character of the i-th element of roads is 'Y'
 * if there is a bidirectional road between cities i and j, and 'N' otherwise.
 * The road connecting cities A and B, where A < B, has a higher priority than the road connecting
 * cities C and D, where C < D, if either A < C or (A = C and B < D). A set of roads is a list of
 * one or more roads sorted from highest to lowest priority. A set S1 has a higher priority
 * than set S2 if road S1[i] has a higher priority than road S2[i], where i is the earliest index at which the two sets differ.
 * A set of roads is called connected if there's a path between any pair of cities containing only the roads from this set.
 * Your task is to find the connected set with the highest priority containing exactly M roads.
 * Return a int[] where the i-th element is the number of roads in that set containing city i as an endpoint.
 * Return an empty int[] if there is no such set.
 */
public class BestRoads {


    public static void main(String[] args) {
        BestRoads task = new BestRoads();
        System.out.println("Expected {4, 4, 4, 4, 4 }. Actual: " + Arrays.toString(task.numberOfRoads(
            new String[] {
                "NYYYY",
                "YNYYY",
                "YYNYY",
                "YYYNY",
                "YYYYN"
            }, 10)));
    }

    public int[] numberOfRoads(String[] roads, int M) {

        Cluster[] clusters = new Cluster[roads.length];
        for (int i = 0; i < roads.length; i++) {
            clusters[i] = new Cluster();
        }

        //Initialise edges
        for (int i = 0; i < roads.length; i++) {
            char[] rs = roads[i].toCharArray();
            for (int j = 0; j < rs.length; j++) {
                if (rs[j] == 'Y') {
                    Road e = new Road(i, j);
                    clusters[e.start].add(e);
                    if (clusters[e.start] != clusters[e.end]) {
                        clusters[e.start].addAll(clusters[e.end].roads);
                        clusters[e.end] = clusters[e.start];
                    }
                }
            }
        }

        boolean isOk = false;
        int[] result = new int[roads.length];
        for (int i = 0; i < roads.length; i++) {
            System.out.println(clusters[i]);
            if (!clusters[i].isProcessed() && clusters[i].size() == M) {
                for (Road e : clusters[i]) {
                    result[e.start]++;
                    result[e.end]++;
                }
                clusters[i].markProcessed();
                isOk = true;
            }
        }

        return isOk ? result : new int[0];
    }

    private static class Cluster implements Iterable<Road> {
        private Set<Road> roads = new TreeSet<Road>();
        private boolean processed = false;

        public void add(Road road) {
            roads.add(road);
        }

        public void addAll(Collection<Road> roads) {
            this.roads.addAll(roads);
        }

        public int size() {
            return roads.size();
        }

        public void markProcessed() {
            this.processed = true;
        }

        public boolean isProcessed() {
            return processed;
        }

        @Override
        public Iterator<Road> iterator() {
            return roads.iterator();
        }
    }

    private static class Road implements Comparable<Road> {
        private final int start;
        private final int end;

        private Road(int start, int end) {
            this.start = Math.min(start, end);
            this.end = Math.max(start, end);
        }

        @Override
        public int compareTo(Road o) {

            if (o == null) {
                return -1;
            }

            if (o == this || start == o.start && end == o.end) {
                return 0;
            }

            if (start < o.start || (start == o.start && end < o.end)) {
                return 1;
            }
            return -1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Road)) return false;

            Road road = (Road) o;

            if (end != road.end) return false;
            if (start != road.start) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            return result;
        }

        @Override
        public String toString() {
            return "{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
