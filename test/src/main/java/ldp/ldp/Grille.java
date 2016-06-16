package ldp.ldp;

/**
 * Created by quentin on 14/06/2016.
 */
public class Grille {
    public ListeChar grille[];

    public char get(int x, int y) {
        return this.grille[x].get(y);
    }

    public void set(int x, int y, char val) {
        this.grille[x].set(y, val);
    }

    public Grille(int x, int y) {
        this.grille = new ListeChar[x];
        for (int i = 0; i < this.grille.length; i++) {
            this.grille[i] = new ListeChar(y);
        }

    }
}
