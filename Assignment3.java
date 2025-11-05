// Scenario: Emergency Relief Supply Distribution 
// A devastating flood has hit multiple villages in a remote area, and the government, along 
// with NGOs, is organizing an emergency relief operation. A rescue team has a limited
// capacity boat that can carry a maximum weight of W kilograms. The boat must transport 
// critical supplies, including food, medicine, and drinking water, from a relief center to the 
// affected villages. 
// Each type of relief item has: 
// ● A weight (wi) in kilograms. 
// ● Utility value (vi) indicating its importance (e.g., medicine has higher value than food). 
// ● Some items can be divided into smaller portions (e.g., food and water), while others must 
// be taken as a whole (e.g., medical kits). 
// As the logistics manager, you must: 
// 1. Implement the Fractional Knapsack algorithm to maximize the total utility value of the 
// supplies transported. 
// 2. Prioritize high-value items while considering weight constraints. 
// 3. Allow partial selection of divisible items (e.g., carrying a fraction of food packets). 
// 4. Ensure that the boat carries the most critical supplies given its weight limit W. 

//Piyush Chandrakant Badgujar- 123B1F003

import java.util.*;

class ReliefItem{
    String name;
    int value;
    int weight;
    ReliefItem(String name,int value,int weight){
        this.name=name;
        this.value=value;       
        this.weight=weight;
    }
}


public class Assignment3 {

    static double getMaxUtility(int capacity,ReliefItem items[],int n){
        Arrays.sort(items,(a,b)->Double.compare(
            (double)b.value/b.weight,
            (double)a.value/a.weight
        ));
        int curweight=0;
        double finalweight=0.0;

        for(int i=0;i<n;i++){
            if(curweight+items[i].weight<=capacity){
                curweight+=items[i].weight;
                finalweight+=items[i].value;
            }else{
                int remain=capacity-curweight;
                finalweight+=(double)items[i].value/items[i].weight*remain;
                break;
            }
        }
        return finalweight;
    }
    public static void main(String[] args) {
        int n=4;
        int capacity=50;
        ReliefItem items[]={
            new ReliefItem("Medical kits",100,20),
            new ReliefItem("Food packets",60,10),
            new ReliefItem("Water bottles",120,30),
            new ReliefItem("Blankets",70,15)
        };
        double maxUtility=getMaxUtility(capacity,items,n);
        System.out.println("capacity is: "+capacity);
        System.out.println("\nMaximum utility we can obtain = "+maxUtility);
    }
}

