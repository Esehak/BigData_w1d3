import java.util.Comparator;

/**
 * Created by Esehak on 5/4/2017.
 */
public class Compare implements Comparator<Pair> {
    @Override
    public int compare(Pair o1, Pair o2){
        return o1.word.compareToIgnoreCase(o2.word);
    }
}
