package ambient_network_simulation;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author DTT
 */
public class VirtualNetwork {

    private int id;
    private Node members;
    private Policy policy;
    private double time;
    private Vertice vertice;
    private TreeVertice treeVertice;
    private boolean nextAction;

    /**
     * Ez a függvény kiértékeli a szomszédaival való viszonyát a policy(k) alapján, és mindegyikhez rendel egy értéket. Visszaadja a legjobb elemet.
     * @return a composition-re kiválasztott elem
     */
    private VirtualNetwork evaluateNeighbours() {
        Iterator<VirtualNetwork> itNeighbours = this.vertice.getNeighbours().iterator();
        VirtualNetwork result = itNeighbours.next();
        boolean check = this.policy.checkPolicy(result.policy);
        while (itNeighbours.hasNext()) {
            VirtualNetwork temp = itNeighbours.next();
            if (this.policy.checkPolicy(temp.policy) == check) {
                if (this.policy.evaluatePolicy(result.policy) >= this.policy.evaluatePolicy(temp.policy)) {
                    result = temp;

                }
            } else if (this.policy.checkPolicy(temp.policy)) {
                check = true;
                result = temp;
            }
        }
        return result;
    }

    private void absorbtion(VirtualNetwork in) {
        throw new UnsupportedOperationException();
    }

    private void gateway(VirtualNetwork in) {
        throw new UnsupportedOperationException();
    }

    public void vnComposition() {
        VirtualNetwork target = evaluateNeighbours();
        if (this.policy.checkPolicy(target.policy)) {
            target.absorbtion(this);
        } else {
            target.gateway(this);
        }
    }
}
