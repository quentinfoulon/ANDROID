package ldp.ldp;

/**
 * Created by quentin on 14/06/2016.
 */
public class CodDec_Vigenere {
    private static boolean debug=false;
    private static int MAX = 26;
    private static int MAX_Lettres = 1000;

    Grille grille = new Grille(MAX,MAX);
    public char get(int x,int y){
        return grille.get(x,y);
    }
    public char CodeLettre(char x,char y){
        if(debug)
        {
            System.out.println("x = "+x+" y = "+y);
            System.out.println("At "+(int)(x-65)+" x "+(int)(y-65));
        }
        if((x==' ') &&(y==' '))
            return ' ';
        else
            return grille.get((int)(x-65),(int)(y-65));
    }

    public String Code(String message,String clef){
        char temp[] = new char[message.length()];
        message = message.toUpperCase();
        clef = clef.toUpperCase();
        String key = Arrange_Clef(message.toUpperCase(),clef.toUpperCase());
        if(debug)
        {
            System.out.println(key);
            System.out.println(message);
        }
        for(int i=0;i<message.length();i++)
        {
            try
            {
                temp[i]=CodeLettre(message.charAt(i),key.charAt(i));
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                temp[i]=message.charAt(i);
                if(debug)
                    System.out.println(message.charAt(i)+" or "+key.charAt(i)+" isn't A-Z letter");
            }
        }
        return String.valueOf(temp);
    }

    public char DecodeLettre(char x,char y){
        int i=-1;
        if((x==' ') &&(y==' '))
            return ' ';
        else
        {
            do
            {
                i++;
            }while(grille.get(i,(int)(y-65))!=x);
            return grille.get(i,0);
        }
    }
    public String Decode(String message,String clef){
        char temp[] = new char[message.length()];
        message = message.toUpperCase();
        clef = clef.toUpperCase();
        String key = Arrange_Clef(message.toUpperCase(),clef.toUpperCase());
        for(int i=0;i<message.length();i++)
        {
            try
            {
                temp[i]=DecodeLettre(message.charAt(i),key.charAt(i));
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                temp[i]=message.charAt(i);
                if(debug)
                    System.out.println(message.charAt(i)+" or "+key.charAt(i)+" isn't A-Z letter");
            }
        }
        return String.valueOf(temp);
    }

    public void Build_Grille(){
        for(int i=0;i<MAX;i++)
        {
            for(int j=0;j<MAX;j++)
            {
                grille.set(i,j,(char)(65+(i+j)%MAX));
            }
        }
    }

    public String Arrange_Clef(String mess,String clef){
        String t="";
        int j=0;
        for(int i=0;i<mess.length();i++)
        {
            if(mess.charAt(i)==' ')
            {
                t=t+" ";
            }
            else
            {
                t=t+String.valueOf(clef.charAt(j));
                j=(j+1)%clef.length();
            }
        }
        return t;
    }
    public CodDec_Vigenere(){
        Build_Grille();
    }
}
