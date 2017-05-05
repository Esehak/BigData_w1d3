import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esehak on 5/2/2017.
 */
public class Reducer {

    public List<GroupByPair> groupByPairs;
    public Reducer(){
        this.groupByPairs= new ArrayList<>();

    }

    public void add(GroupByPair groupByPair){
        this.groupByPairs.add(groupByPair);
    }


}
