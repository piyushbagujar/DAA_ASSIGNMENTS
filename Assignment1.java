class CustomerOrder{
    private String orderId;
    private long timestamp;
    public CustomerOrder(String orderId, long timestamp){
        this.orderId = orderId;
        this.timestamp = timestamp;
    }

    public String getOrderId(){
        return orderId;
    }

    public long getTimestamp(){
        return timestamp;
    }
    public String toString() {
        return "OrderID: " + orderId + ", Timestamp: " + timestamp;
    }
}

public class Assignment1{
    public static void main(String[] args) {
       CustomerOrder[] orders ={
            new CustomerOrder("A101", 1695379200000L),
            new CustomerOrder("A102", 1695292800000L),
            new CustomerOrder("A103", 1695465600000L),
            new CustomerOrder("A104", 1695206400000L)
       };
       System.out.println("Before Sorting:");
        for (CustomerOrder o : orders) {
            System.out.println(o);
        }
        sortOrders(orders);

        System.out.println("\nAfter Sorting:");
        for (CustomerOrder o : orders) {
            System.out.println(o);
        }
    }
     public static void sortOrders(CustomerOrder[] orders) {
        mergeSort(orders, 0, orders.length - 1);
    }

    public static void mergeSort(CustomerOrder[] orders,int s,int e){
        if(s<e){
            int mid=s+(e-s)/2;
            mergeSort(orders, s, mid);
            mergeSort(orders, mid+1, e);
            merge(orders,s,e,mid);
        }
    }
    public static void merge(CustomerOrder[] orders,int s,int e,int mid){
        int len1=mid-s+1;
        int len2=e-mid;
        CustomerOrder[] first=new CustomerOrder[len1];
        CustomerOrder[] second=new CustomerOrder[len2];
        int mainArrayIndex=s;
        for (int i = 0; i < len1; i++) {
        first[i] = orders[s + i];
    }
    // Copy second half
    for (int i = 0; i < len2; i++) {
        second[i] = orders[mid + 1 + i];
    }
        int index1=0;
        int index2=0;
        while(index1<len1 && index2<len2){
            if(first[index1].getTimestamp()<second[index2].getTimestamp()){
                orders[mainArrayIndex++]=first[index1++];   
            }else{
                orders[mainArrayIndex++]=second[index2++];
            }
        }
        while (index1 < len1) {
            orders[mainArrayIndex++] = first[index1++];
        }
        while (index2 < len2) {
            orders[mainArrayIndex++] = second[index2++];
        }
    }
}