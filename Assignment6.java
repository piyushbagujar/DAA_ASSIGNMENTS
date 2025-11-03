import java.util.*;

public class Assignment6 {

    static class CargoItem {
        String label;
        int weight;
        int profit;

        public CargoItem(String label, int weight, int profit) {
            this.label = label;
            this.weight = weight;
            this.profit = profit;
        }
    }

    static int[][] memo;

    public static int computeMaxValue(CargoItem[] items, int capacity, int count) {
        if (count == 0 || capacity == 0)
            return 0;

        if (memo[count][capacity] != -1)
            return memo[count][capacity];

        if (items[count - 1].weight > capacity)
            memo[count][capacity] = computeMaxValue(items, capacity, count - 1);
        else
            memo[count][capacity] = Math.max(
                items[count - 1].profit + computeMaxValue(items, capacity - items[count - 1].weight, count - 1),
                computeMaxValue(items, capacity, count - 1)
            );

        return memo[count][capacity];
    }

    public static void displayChosenItems(CargoItem[] items, int capacity, int count) {
        System.out.println("\nSelected Cargo Items:");
        int totalWeight = 0;
        while (count > 0 && capacity > 0) {
            if (memo[count][capacity] != memo[count - 1][capacity]) {
                System.out.println(" • " + items[count - 1].label + 
                    " (Weight: " + items[count - 1].weight + 
                    " kg, Value: " + items[count - 1].profit + ")");
                totalWeight += items[count - 1].weight;
                capacity -= items[count - 1].weight;
            }
            count--;
        }
        System.out.println("Total Weight: " + totalWeight + " kg");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Cargo Load Optimization using Dynamic Programming");
        System.out.print("Enter number of available cargo items: ");
        int n = input.nextInt();
        input.nextLine();

        CargoItem[] items = new CargoItem[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for item " + (i + 1) + ":");
            System.out.print("Item name: ");
            String label = input.nextLine();
            System.out.print("Weight (kg): ");
            int weight = input.nextInt();
            System.out.print("Utility Value: ");
            int profit = input.nextInt();
            input.nextLine();
            items[i] = new CargoItem(label, weight, profit);
        }

        System.out.print("\nEnter truck capacity (in kg): ");
        int capacity = input.nextInt();

        memo = new int[n + 1][capacity + 1];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        long startTime = System.nanoTime();
        int maxProfit = computeMaxValue(items, capacity, n);
        long endTime = System.nanoTime();

        displayChosenItems(items, capacity, n);
        System.out.println("\nMaximum Achievable Value: " + maxProfit);
        System.out.println("Computation Time: " + (endTime - startTime) / 1_000_000.0 + " ms");

        System.out.println("\nOptimization completed successfully — Safe Loading!");
        input.close();
    }
}
