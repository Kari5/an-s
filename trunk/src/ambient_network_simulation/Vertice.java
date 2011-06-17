package ambient_network_simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Csúcsok osztálya
 * @author DTT
 */
public class Vertice {

    private VirtualNetwork vn;
    private List<Edge> edges;
    private ReadWriteLock lock;

    /**
     * Konstruktor
     * @param vn
     */
    public Vertice(VirtualNetwork vn) {
        this.vn = vn;
        this.edges = new ArrayList<Edge>();
        this.lock = new ReadWriteLock();
    }

    /**
     * Megadja a csúcshoz tartozó VirtualNetwork-öt
     * @return
     */
    public VirtualNetwork getVn() {
        return vn;
    }

    /**
     * Él eltávolítása
     * @param e
     */
    public void removeEdge(Edge e) {
        edges.remove(e);

        System.out.println("Csúcs éllistájából él törölve.");
    }

    /**
     * Él hozzáadása a listához.
     * Amennyiben már szerepelt akkor csak az él számlálóját növeljük.
     * @param e
     */
    public void addEdge(Edge e) {
        Iterator<Edge> it = this.edges.iterator();
        while (it.hasNext()) {
            Edge temp = it.next();
            if (temp.equal(e)) {
                temp.counterplus(e.getCounter());
                return;
            }
        }

        edges.add(e);
        System.out.println("Csúcs éllistájához él hozzáadva.");
    }

    /**
     * A csúcs szomszédait adja meg egy listában. (VirtualNetwork lista)
     * @return
     */
    public List<VirtualNetwork> getNeighbours() {

        List<VirtualNetwork> result = new ArrayList<VirtualNetwork>();
        Iterator<Edge> it = this.edges.iterator();

        while (it.hasNext()) {
            result.add(it.next().getNeighbour(this).getVn());
        }

        return result;
    }

    /**
     * Egy másik csúcs szomszédait hozzáadja ehhez a csúcshoz.
     * @param v a másik csúcs
     */
    public void join(Vertice v) {
        System.out.println("Csúcs join elindult.");
        Iterator<Edge> itThis = this.edges.iterator();
        Iterator<Edge> itV = v.edges.iterator();

        /**
         * Végigiterálunk ennek és a paraméterként kapott csúcsnak az élein, és a kettő közötti éleket töröljük,
         * illetve az azonos egyéb csúcsra mutató éleknél ennek a csúcsnak a megfelelő élének beállítjuk a számlálóját,
         * majd a másik csúcs listájából töröljük.
         * Így ez a részlet lefutása után v-nek csak azok az élei maradnak, amikhez ennek a csúcsnak eddig semmi köze nem volt.
         */
        while (itThis.hasNext()) {
            Edge tempThis = itThis.next();
            while (itV.hasNext()) {
                Edge tempV = itV.next();
                if (tempV.equal(tempThis)) {
                    tempV.delete(true);
                } else if (this == tempV.getNeighbour(v)) {
                    if (tempThis.getNeighbour(this) == tempV.getNeighbour(v)) {
                        tempThis.counterplus(tempV.getCounter());
                        tempV.delete(true);
                    }
                }
            }
            itV = v.edges.iterator();
        }

        /**
         * Visszaállítjuk az iterátorokat a lista elejére.
         */
        itV = v.edges.iterator();

        /**
         * v maradék éleit hozzáadjuk this-hez. Ezt úgy oldja meg, hogy mindig létrehoz egy új élet,
         * ami a konstruktorából fakadóan rögtön bejegyzi magát a megfelelő helyekre.
         */
        while (itV.hasNext()) {
            Edge tempV = itV.next();
            new Edge(this, tempV.getNeighbour(v));
        }
        System.out.println("Csúcs join véget ért.");

    }
}
