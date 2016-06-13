package ldp.ldp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
   private static HashMap<String, String> listlien;
    public static HashMap<String, List<String>> getData(List<String> listStringBDD,String theme) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        listlien=new HashMap<String, String>();

        String tabResume[][]=new String[listStringBDD.size()][4];
        int indice =0 ,i=0 ;
        for(String elem: listStringBDD)
        {
            String tabSplit[]=  elem.split(";");
            for(i = 0 ; i < 4 ; i++){
                tabResume[indice][i]=tabSplit[i];
                if(i==1 && tabSplit[0].equals(theme)){
                    if(!expandableListDetail.containsKey(tabSplit[i])){
                        expandableListDetail.put(tabSplit[i], new ArrayList<String>());
                        List<String> temp= expandableListDetail.get(tabSplit[i]);
                        temp.add(tabSplit[2]);
                        expandableListDetail.put(tabSplit[i],temp);
                    }else{
                        List<String> temp= expandableListDetail.get(tabSplit[i]);
                        temp.add(tabSplit[2]);
                        expandableListDetail.put(tabSplit[i],temp);
                    }
                       // alTitre.add(tabSplit[i]);
                    listlien.put(tabSplit[2],tabSplit[3]);

                }
            }
               indice++;
        }
        i=0;
        //probleme sur le getString arraylistextra
        // la récuperation du arraylist ne marche pas
        //ListView mListView = (ListView)findViewById(R.id.listViewnumerique);


        return expandableListDetail;
    }
    public static HashMap<String, String> getlien(){

        return listlien;
    }
}
