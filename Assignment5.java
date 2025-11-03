import java.util.*;

class Link {
    int target;
    int weight;

    Link(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

public class Assignment5 {

    public static int[] computeShortestPath(List<List<Link>> network, int totalNodes, int start, int end) {
        int[] distance = new int[totalNodes];
        int[] nextStep = new int[totalNodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(nextStep, -1);

        distance[end] = 0;

        for (int i = totalNodes - 2; i >= 0; i--) {
            for (Link link : network.get(i)) {
                if (distance[link.target] != Integer.MAX_VALUE && link.weight + distance[link.target] < distance[i]) {
                    distance[i] = link.weight + distance[link.target];
                    nextStep[i] = link.target;
                }
            }
        }
        return nextStep;
    }

    public static void showPath(int start, int[] nextStep) {
        int node = start;
        System.out.print("Optimized delivery path: ");
        System.out.print(node);
        while (nextStep[node] != -1) {
            node = nextStep[node];
            System.out.print(" -> " + node);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("RouteMaster Logistics Optimization System");
        System.out.print("Enter the total number of hubs in the network: ");
        int totalNodes = input.nextInt();

        List<List<Link>> network = new ArrayList<>();
        for (int i = 0; i < totalNodes; i++) network.add(new ArrayList<>());

        System.out.print("Enter number of available routes: ");
        int edges = input.nextInt();

        System.out.println("Enter route info as: <From> <To> <Cost/Time>");
        for (int i = 0; i < edges; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            int cost = input.nextInt();
            network.get(u).add(new Link(v, cost));
        }

        System.out.print("Enter warehouse node (start): ");
        int start = input.nextInt();

        System.out.print("Enter destination node (end): ");
        int end = input.nextInt();

        long startTime = System.nanoTime();
        int[] nextStep = computeShortestPath(network, totalNodes, start, end);
        long endTime = System.nanoTime();

        System.out.println();
        System.out.println("Processing optimal route...");
        showPath(start, nextStep);

        System.out.println("Route computation completed successfully.");
        System.out.println("Computation time: " + (endTime - startTime) / 1_000_000.0 + " ms");

        System.out.println("RouteMaster AI has determined the best delivery path.");
        input.close();
    }
}
