package ambient_network_simulation;

/**
 *
 * @author Karcsi
 */
public class Edge {

    private Vertice verticeLeft;
    private Vertice verticeRight;
    private int counter;

    /**
     * Konstruktor
     * @param vertceLeft
     * @param verticeRight
     */
    public Edge(Vertice verticeLeft, Vertice verticeRight) {
        this.verticeLeft = verticeLeft;
        verticeLeft.addEdge(this);
        this.verticeRight = verticeRight;
        verticeRight.addEdge(this);
        this.counter = 1;
    }

    /**
     * A számlálóhoz hozzáad n-t
     * @param n a hozzáadandó szám
     */
    public void counterplus(int n){
        this.counter+=n;
        System.out.println("\t Él counter növelve "+ n +". Új érték: "+ this.counter);
    }


    /**
     * Visszaadja a számláló éretékét
     * @return int-ként a számláló értéke
     */
    public int getCounter() {
        return counter;
    }



    /**
     * összehasonlítja egy másik éllel.
     * @param e összehasonlítandó él
     * @return
     */

    public boolean equal(Edge e) {
        if ((this.verticeLeft == e.verticeLeft && this.verticeRight == e.verticeRight) || (this.verticeLeft == e.verticeRight && this.verticeRight == e.verticeLeft)) {
            System.out.println("\t Él equal lefutott true értékkel.");
            return true;
        }
        System.out.println("\t Él equal lefutott false értékkel.");
        return false;
    }

    /**
     * Az él bejegyzéseinek törlése a két végpontból
     * Ha több kapcsolat volt a kettő között akkor csak csökkentjuk a kapcsolatok számát,
     * de ha true értéket kap változóként, akkor számlálótól függetlenül törli.
     * @param violently igaz érték esetén erőszakosan törli az élet, false esetén csak ha a számláló kisebb mint 1.
     */

    public void delete(boolean violently) {
        if(this.counter == 0 || violently==true){
            verticeLeft.removeEdge(this);
            verticeRight.removeEdge(this);
            System.out.println("\t Él törölve.");
        }
        else{
            System.out.println("\t Él counter csökkent");
            counter--;
        }

    }
    
    /**
     * A paraméterben megadott csúcs szomszédját adja vissza
     * @param v
     * @return
     */

    public Vertice getNeighbour(Vertice v){
        if (this.verticeLeft == v){
            return this.verticeRight;
        }
        else{
            return this.verticeLeft;
        }
    }
}
