package trains.routes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Neighbor {
    public int index;
    public int weight;
    public Neighbor next;

    public Neighbor(int vertexNum, int weight, Neighbor neighbor) {
        this.index = vertexNum;
        this.weight = weight;
        next = neighbor;
    }
}

class Vertex {
    char vertexName;
    Neighbor adjNeighbor;

    Vertex(char name, Neighbor neighbors) {
        this.vertexName = name;
        this.adjNeighbor = neighbors;
    }
}


public class Graph {

    Vertex[] adjLists;

    public Graph(String fileName) throws FileNotFoundException {

        char c, c1, c2;
        int  v1, v2, w;
        String route;

        adjLists = new Vertex[5]; //NOTE: the towns are named using the first few letters of the alphabet from A to E

        // initialing vertices
        for (int v = 0; v < adjLists.length; v++) {
            c = (char) (65 + v); // 'A' = 65;
            adjLists[v] = new Vertex(c, null);
        }

        try (Scanner scan = new Scanner(new File(fileName))) {

            scan.useDelimiter(",\\s");
            while (scan.hasNext()) {
                route = scan.next();

                c1 = route.charAt(0);
                c2 = route.charAt(1);
                w = Integer.parseInt(route.substring(2));

                // read edges
                v1 = indexForVertexName(c1); // read vertex names and translate to vertex numbers
                v2 = indexForVertexName(c2);

                adjLists[v1].adjNeighbor = new Neighbor(v2, w, adjLists[v1].adjNeighbor); // for directed graph
            }
        }
    }


    int indexForVertexName(char ch) {
        for (int v=0; v < adjLists.length; v++) {
            if (adjLists[v].vertexName == ch) {
                return v;
            }
        }
        return -1;
    }


    int connectedEdge(char A, char B) {
        int weight = 0;
        int v1 = indexForVertexName(A);
        int v2 = indexForVertexName(B);
        for (Neighbor nbr = adjLists[v1].adjNeighbor; nbr != null; nbr = nbr.next) {

            if (nbr.index == v2) {
                weight = nbr.weight;
                break;
            }
        }
        return weight;
    }


    public void calculateDistance(String routeString) {

        int totalDistance = 0, distance = 0;
        char A = ' ', B = ' ';

        for (int i = 0; i < routeString.length() - 1; i += 2) {

            A = routeString.charAt(i);
            B = routeString.charAt(i+2);

            distance = connectedEdge(A, B);
            if(distance <= 0) {
                totalDistance = 0;
                break;
            }
            else
                totalDistance += distance;
        }

        if (totalDistance == 0)
            System.out.println("NO SUCH ROUTE");
        else
            System.out.println(totalDistance);
    }


    public void print() {
        for (int v = 0; v < adjLists.length; v++) {
            System.out.print(adjLists[v].vertexName + "-->");

            for (Neighbor nbr = adjLists[v].adjNeighbor; nbr != null; nbr = nbr.next) {
                System.out.print(" " + adjLists[nbr.index].vertexName + nbr.weight);
            }
            System.out.println();
        }
    }

}