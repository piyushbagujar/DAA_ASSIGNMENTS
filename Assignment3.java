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
