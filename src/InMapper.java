import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Esehak on 5/4/2017.
 */
public class InMapper {
    public List<Pair> pairs;

    public HashMap<Pair, Integer> map;
    public InMapper(){
        this.pairs= new ArrayList<>();
        this.map=new HashMap<>();
    }

    public void add(Pair pair){
        int count=map.getOrDefault(pair,0)+1;
        String w=pair.word;
        Pair p=new Pair(w,count);
        map.put(p,count);

    }
    void cleanup(){
        pairs= new ArrayList<>(map.keySet());
    }




}
