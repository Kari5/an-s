package ambient_network_simulation;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Karcsi
 */
public class Graph {

    private List<Vertice> vertices;
    private List<Edge> edges;

    /**
     * Konstruktor
     * @param vertices
     * @param edges
     */
    public Graph(List<Vertice> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }


    /**
     * A paraméterként kapott b csúcs éleit a megfelelő módon hozzáadja az a csúcshoz.
     * @param a amelyikhez hozzá akarjuk adni b éleit
     * @param b amelyik éleit hozzáadjuk a-hoz
     */
    public void unio(Vertice a, Vertice b) {
        a.join(b);
    }

    /**
     * Kiszed egy csúcsot a gráfból
     * @param v
     */
    public void removeVertice(Vertice v) {
        Iterator<Edge> it = this.edges.iterator();
        while (it.hasNext()) {
            it.next().delete(true);
        }
        this.vertices.remove(v);
    }

    public Vertice getRandomV(int seed) {
        System.out.println("Random él visszaadása.");
        int numberOfVertices=this.vertices.size();
        int random;
        Random rand= new Random(seed);
        random=rand.nextInt(numberOfVertices+1);
        return this.vertices.get(random);
    }
}
