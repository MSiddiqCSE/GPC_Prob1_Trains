package trains.routes;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String fileName = "INPUT.txt";
        Graph graph = new Graph(fileName);
//        graph.print();

        System.out.print("Output #1: ");
        graph.calculateDistance("A-B-C");

        System.out.print("Output #2: ");
        graph.calculateDistance("A-D");

        System.out.print("Output #3: ");
        graph.calculateDistance("A-D-C");

        System.out.print("Output #4: ");
        graph.calculateDistance("A-E-B-C-D");

        System.out.print("Output #5: ");
        graph.calculateDistance("A-E-D");

//        System.out.print("Output #6: ");
//        System.out.print("Output #7: ");
//        System.out.print("Output #8: ");
//        System.out.print("Output #9: ");
//        System.out.print("Output #10: ");

    }
}
