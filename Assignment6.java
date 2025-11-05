// Scenario: Disaster Relief Resource Allocation 
// A massive earthquake has struck a remote region, and a relief organization is transporting 
// essential supplies to the affected area. The organization has a limited-capacity relief truck that 
// can carry a maximum weight of W kg. They have N different types of essential items, each 
// with a specific weight and an associated utility value (importance in saving lives and meeting 
// urgent needs). 
// Since the truck has limited capacity, you must decide which items to include to maximize the 
// total utility value while ensuring the total weight does not exceed the truck's limit. 
// Your Task as a Logistics Coordinator: 
// 1. Model this problem using the 0/1 Knapsack approach, where each item can either be 
// included in the truck (1) or not (0). 
// 2. Implement an algorithm to find the optimal set of items that maximizes utility while 
// staying within the weight constraint. 
// 3. Analyze the performance of different approaches (e.g., Brute Force, Dynamic 
// Programming, and Greedy Algorithms) for solving this problem efficiently. 
// 4. Optimize for real-world constraints, such as perishable items (medicines, food) having 
// priority over less critical supplies. 
// Extend the model to consider multiple trucks or real-time decision-making for dynamic supply 
// chain management. 

//Piyush Chandrakant Badgujar- 123B1F003

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

