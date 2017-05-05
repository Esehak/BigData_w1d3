import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esehak on 5/3/2017.
 */
public class WordCount {


    public int mapperCount;
    public int reducerCount;
    public Mapper[] mappers;
    public  Reducer[] reducers;
    public static List<Pair> r1 = new ArrayList<Pair>();
    public static List<Pair> r2 = new ArrayList<Pair>();
    public static List<Pair> r3 = new ArrayList<Pair>();
    public static List<Pair> r4 = new ArrayList<Pair>();


    public WordCount(int mapperCount, int reducerCount) {
        this.reducerCount=reducerCount;
        this.mapperCount= mapperCount;
        mappers = new Mapper[this.mapperCount];
        reducers = new Reducer[this.reducerCount];
        this.initialize();

    }

    public void initialize(){
        for(int i=0; i< this.mapperCount; i++){
            this.mappers[i] = new Mapper();
        }
        for(int i =0; i <this.reducerCount; i++){
            this.reducers[i]= new Reducer();
        }
    }




    public int getPartition(String key) {
        return (int) key.hashCode() % reducerCount;
    }

}
