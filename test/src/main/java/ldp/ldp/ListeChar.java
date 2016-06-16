package ldp.ldp;

/**
 * Created by quentin on 14/06/2016.
 */
public class ListeChar {
    public char liste[];
    public char get(int x){
        return this.liste[x];
    }
    public void set(int x, char val){
        this.liste[x] = val;
    }
    public ListeChar(int taille){
        this.liste = new char[taille];
        for(int i=0;i<taille;i++)
        {
            this.liste[i]='A';
        }
    }
}
