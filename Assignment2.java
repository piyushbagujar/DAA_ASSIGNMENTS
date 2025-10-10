import java.util.*;
class Movie{
    String name;
    double rating;
    int year;
    int popularity;
    Movie(String name,double rating,int year,int popularity){
        this.name=name;
        this.rating=rating;
        this.year=year;
        this.popularity=popularity;
    }
    public String toString() {
        return name + " | Rating: " + rating + " | Year: " + year + " | Popularity: " + popularity;
    }
}

public class Assignment2 {

    static void quickSort(Movie[] movies,int s,int e,String parameter){
        if(s>=e){
            return;
        }
        int pivot=partition(movies,s,e,parameter);
        quickSort(movies, s, pivot-1, parameter);
        quickSort(movies, pivot+1, e, parameter);
    }

    static int partition(Movie[] movies,int s,int e,String parameter){
        Movie pivot=movies[s];
        int cnt=0;
        for(int i=s+1;i<=e;i++){
            if(compare(movies[i],pivot,parameter)){
                cnt++;
            }
        }
        int pivotIndex=s+cnt;
        Movie temp=movies[s];
        movies[s]=movies[pivotIndex];
        movies[pivotIndex]=temp;
        int i=s;
        int j=e;
        while(i<pivotIndex && j>pivotIndex){
            while(compare(movies[i],pivot,parameter)){
                i++;
            }
            while(!compare(movies[j],pivot,parameter)){
                j--;
            }
            if(i<pivotIndex && j>pivotIndex){
                Movie t=movies[i];
                movies[i]=movies[j];
                movies[j]=t;
                i++;
                j--;
            }
        }
        return pivotIndex;
    }
    static boolean compare(Movie a,Movie b,String parameter){
        switch(parameter.toLowerCase()){
            case "rating":
                return a.rating<b.rating;
            case "year":
                return a.year<b.year;
            case "popularity":
                return a.popularity<b.popularity;
            default:
                return false;
        }
    }
    public static void main(String[] args) {
        Movie movies[] = {
            new Movie("Inception", 8.8, 2010, 95000),
            new Movie("Interstellar", 8.6, 2014, 120000),
            new Movie("The Dark Knight", 9.0, 2008, 150000),
            new Movie("Oppenheimer", 8.5, 2023, 200000),
            new Movie("Dune", 8.2, 2021, 110000)
        };
        System.out.println("Before Sorting:");
        for (Movie m : movies) {
            System.out.println(m);
        }
        System.out.println();
        quickSort(movies, 0, movies.length - 1, "rating");
        System.out.println("Sorted by Rating:");
        for (Movie m : movies) System.out.println(m);
        System.out.println();

        quickSort(movies, 0, movies.length - 1, "year");
        System.out.println("Sorted by Rating:");
        for (Movie m : movies) System.out.println(m);
        System.out.println();
        quickSort(movies, 0, movies.length - 1, "popularity");
        System.out.println("Sorted by Rating:");
        for (Movie m : movies) System.out.println(m);
    }
}
