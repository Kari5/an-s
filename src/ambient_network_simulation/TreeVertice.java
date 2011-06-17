package ambient_network_simulation;

import java.util.List;

/**
 *
 * @author DTT
 */
public class TreeVertice {

    private VirtualNetwork vn;
    private ReadWriteLock lock;
    private int hierarchieLevel;
    private TreeEdge edgeUp;
    private List<TreeEdge> edgesDown;

    public TreeVertice(VirtualNetwork vn, int h, TreeEdge up, List<TreeEdge> down) {
        this.vn = vn;
        this.hierarchieLevel = h;
        this.edgeUp = up;
        this.edgesDown = down;
        this.lock = new ReadWriteLock();

    }


    /**
     * Visszaadja a tartalmazott VirtualNetwork-öt
     * @return VirtualNetwork
     */
    public VirtualNetwork getVn() {
        return vn;
    }

    /**
     * Vissza adja, hogy melyik hierarchia szinten van.
     * @return int hierarchiLevel
     */
    public int getHierarchieLevel() {
        return hierarchieLevel;
    }

    /**
     * Beállítja a hierarchia szintet.
     * @param hierarchieLevel amire be akarjuk állítani
     */
    public void setHierarchieLevel(int hierarchieLevel) {
        this.hierarchieLevel = hierarchieLevel;
    }

    /**
     * A lefelé mutató élekhez tudunk ezzel hozzáadni egyet.
     * @param d a hozzáadandó él.
     */
    public void addEdgeDown(TreeEdge d) {
        this.edgesDown.add(d);
    }


    /**
     * Beállítja a felfelé mutató élet.
     * @param edgeUp a felfelé mutató él
     */
    public void setEdgeUp(TreeEdge edgeUp) {
        this.edgeUp = edgeUp;
    }



    /**
     * Visszadja, hogy a paraméterként kapott TreeVertice ezen csúcs felett van-e, mint gateway.
     * @param v TreeVertice, amiről tudni akarjuk, hogy ennek gateway-je-e
     * @return true, ha gateway, false, ha nincs felette.
     */
    public boolean isChild(TreeVertice v) {
        if (this.edgeUp != null) {
            if (this.edgeUp.getNeighbour(this) == v) {
                return true;
            } else {
                this.edgeUp.getNeighbour(this).isChild(v);
            }
        }
        return false;
    }

    /**
     * A paraméterként kapott élet eltávolítja a lefelé muatató éllistájából.
     * @param te az eltávolítandó él.
     */
    public void removeEdge(TreeEdge te) {
        if (te == this.edgeUp) {
            this.edgeUp = null;
        } else {
            edgesDown.remove(te);
        }
    }
}
