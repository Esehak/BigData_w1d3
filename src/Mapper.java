import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esehak on 5/4/2017.
 */
public class Mapper {
    public List<Pair> pairs;

    public Mapper(){
        this.pairs= new ArrayList<>();

    }

    public void add(Pair pair){
        if(!this.pairs.contains(new Pair(pair.word, pair.fre))){
            this.pairs.add(pair);
        }

        else {
            Pair p= this.pairs.stream().filter(a->a.word.equals(pair.word))
                    .findFirst().get();
            p.fre= p.fre + pair.fre;
        }
    }


}
