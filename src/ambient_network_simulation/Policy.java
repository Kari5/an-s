package ambient_network_simulation;

import java.util.Iterator;
import java.util.List;

/**
 * Ezek az objektumok egy policy-t reprezentálnak: több tulajdonságot tartalmazhatnak.
 * @author DTT
 */
public class Policy {

    private List<Property> properties;
    
    /**
     * Konstruktor
     * @param properties tulajdonság lista
     */

    public Policy(List<Property> properties) {
        this.properties = properties;
    }

    /**
     * Konstruktor Gateway esetén.
     * A két átadott policy tualjdonságai közül kiválasztja a megegyező nevűeket.
     * @param a
     * @param b
     */

    public Policy(Policy a, Policy b) {

        Iterator<Property> itA = a.properties.iterator();
        Iterator<Property> itB = b.properties.iterator();

        while(itA.hasNext()){
            Property tempA = itA.next();
            while(itB.hasNext()){
                Property tempB = itB.next();
                if(tempA.getName().equals(tempB.getName())){
                    this.properties.add(new Property(tempA, tempB));
                }
            }
            itB= b.properties.iterator();
        }
    }




    /**
     * minden tulajdonságot kiértékelha megfelelő abszorbcióra, akkor true értékkel,
     * különben false értékkel tér vissza.
     * @param In a célpont policy-ja
     * @return ha megfelelő abszorbcióra, akkor true értékkel, különben false értékkel tér vissza.
     */

    public boolean checkPolicy(Policy In) {
        System.out.print("CheckPolicy: ");

        /**
         * Ha nem egyezik meg a két policy tulajdonság-listájának a mérete akkor gateway lesz
         */
        if (this.properties.size() != In.properties.size()){ System.out.println("különböző méretű tul. listák."); return false;}

        Iterator<Property> itThis = this.properties.iterator();
        Iterator<Property> itIn = In.properties.iterator();
        boolean check = false;

        /**
         * Végigmegyünk a két policy tulajdonságlistáján és megkeressük a megegyezű nevű elemeket.
         * Ha talákunk ilyet kiértékeljük. Amennyiben egy esetben false-t kapunk akkor vége.
         * Ha nem találunk egyező nevű tulajdonságot akkor is false-al vége.
         */
        while(itThis.hasNext()){
            Property tempThis = itThis.next();
            while(itIn.hasNext()){
                Property tempIn = itIn.next();
                if(tempThis.getName().equals(tempIn.getName())){
                    check = tempThis.check(tempIn);
                    break;
                }
                
            }
            itIn=In.properties.iterator();
            if(!check) return false;
        }

        System.out.println(" lefutott "+ check +" értékkel.");
        return check;
    }

    /**
     * ez a fv. kiértékeli a kapcsolat értékét.
     * @param In a célpont policy-ja
     * @return a policy értéke
     */

    public int evaluatePolicy(Policy In) {

        Iterator<Property> itThis = this.properties.iterator();
        Iterator<Property> itIn = In.properties.iterator();
        int result = 0;

        while(itThis.hasNext()){
            Property tempThis = itThis.next();
            while(itIn.hasNext()){
                Property tempIn = itIn.next();
                if(tempThis.getName().equals(tempIn.getName())){
                    result += tempThis.evaluate(tempIn);
                }
            }
            itIn=In.properties.iterator();
        }
        System.out.print("evaluatePolicy lefutott, értéke: " + result);
        return result;
    }

    /**
     * a abszorbció esetén a célpont policy értékét módosítja
     * @param In a csatlakozni kívánó fél policy-je
     */

    public void updatePolicy(Policy In) {

        Iterator<Property> itThis = this.properties.iterator();
        Iterator<Property> itIn = In.properties.iterator();

        while(itThis.hasNext()){
            Property tempThis = itThis.next();
            while(itIn.hasNext()){
                Property tempIn = itIn.next();
                if(tempThis.getName().equals(tempIn.getName())){
                    tempThis.updateAbs(tempIn);
                }
            }
            itIn=In.properties.iterator();
        }
    }
}
