import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esehak on 5/2/2017.
 */
public class GroupByPair {

    public String key;
    public List<Integer> values;



    public GroupByPair(String key, int value) {
        this.key = key;
        this.values = new ArrayList<Integer>();
        this.values.add(value);
    }

    public int sum() {
        int sum = 0;
        for (Integer value : values) {
            sum = sum + value;
        }
        return sum;
    }

    public void addValues(int value) {
        this.values.add(value);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof GroupByPair)) {
            return false;
        }

        GroupByPair groupByPair = (GroupByPair) obj;

        return this.key.equals(groupByPair.key);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.key != null ? this.key.hashCode() : 0);
        return hash;
    }


}
