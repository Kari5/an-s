package ambient_network_simulation;

/**
 *
 * @author DTT
 */
public class TreeEdge {
    private TreeVertice verticeUp;
    private TreeVertice verticeDown;

    public TreeEdge(TreeVertice verticeUp, TreeVertice verticeDown) {
        this.verticeUp = verticeUp;
        verticeUp.setEdgeUp(this);
        this.verticeDown = verticeDown;
        verticeDown.addEdgeDown(this);
    }

    /**
     * A paraméterben megadott csúcs szomszédját adja vissza
     * @param v
     * @return
     */

    public TreeVertice getNeighbour(TreeVertice v){
        if (this.verticeUp == v){
            return this.verticeDown;
        }
        else{
            return this.verticeUp;
        }
    }

    /**
     * törli az élet: kitörli magát a két végpontjának az éllistájából.
     */
    public void delete(){
         this.verticeUp.removeEdge(this);
         this.verticeDown.removeEdge(this);
    }

    

}
