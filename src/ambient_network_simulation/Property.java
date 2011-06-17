package ambient_network_simulation;

/**
 * Ezek az objektumok egy-egy tulajdonságot tartalmaznak.
 * Ehhez a tulajdonsághoz tartozik egy név, egy szolgáltatott érték, és egy elvárt érték.
 * @author DTT
 */


public class Property {

    private String name;
    private int required;
    private int provided;

    /**
     * Ezt a konstruktort akkor használjuk, ha Gateway-t hozunk létre.
     * @param a egyik tul.
     * @param b másik tul.
     */
    public Property(Property a, Property b) {
        name = a.name;
        this.provided = (int) ((a.provided + b.provided) / 2);
        if (a.required < b.required) {
            this.required = a.required;
        } else {
            this.required = b.required;
        }
    }

    /**
     * Konstruktor
     * @param name
     * @param required
     * @param provided
     */
    public Property(String name, int required, int provided) {
        this.name = name;
        this.required = required;
        this.provided = provided;
    }

    /**
     * Név lekérése
     * @return tulajdonság neve
     */

    public String getName() {
        return name;
    }



    /**
     * Ellenörzi, hogy a paramétreként kapott tulajdonság milyen csatlakozáshoz elég.
     * @param p másik tulajdonsága, akihez csatlakozni akar a vn.
     * @return true ha abszorbcióra is jó, false ha csak gateway
     */
    public boolean check(Property p) {
        System.out.println("Check: "+ this.name + " és " + p.name);
        if (this.required >= p.provided && this.provided >= p.required && p.required >= this.provided && p.provided >= this.required) {

            System.out.println("Chechk lefutott false értékkel.");
            return false;
        } else {

            System.out.println("Chechk lefutott true értékkel.");
            return true;
        }


    }

    /**
     * Ez a függvény értékeli ki a tulajdonságokat egymáshoz viszonyítva
     * @param p másik tulajdonsága, akihez csatlakozni akar a vn.
     * @return a két tulajdonság átlaga
     */
    public int evaluate(Property p) {
        System.out.println("Eveluate: "+ this.name + " és " + p.name + " Értéke: " + ((int) ((this.provided + p.provided) / 2)));

        return (int) ((this.provided + p.provided) / 2);
    }

    /**
     * Ez a függvény állítja be az új tulajdonságot abszorbció után.
     * Ezt a függvényt azon vn tulajdonságaiban hívjuk meg, amelyhez csatlakoznak.
     * @param p a csatlakozni kívánó fél egyik tulajdonsága
     */
    public void updateAbs(Property p) {
        System.out.println("updateAbs: "+ this.name + " és " + p.name);
        if (this.required < p.required) {
            this.required = p.required;
        }
        this.provided = evaluate(p);
        System.out.println("Új értékek: szolgáltatott:"+ this.provided + ", elvárt:" + this.required);
    }
}
