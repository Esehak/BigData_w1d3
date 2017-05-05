import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Esehak on 5/3/2017.
 */
public class Main {

    public static int m;
    public static int r;
    //this is lab----
    // this is also comment

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("How Many InMapper that u need: ");
        m = reader.nextInt();

        Scanner reader1 = new Scanner(System.in);  // Reading from System.in
        System.out.println("How many reducer that u need: ");
        r = reader1.nextInt();

        WordCount wc = new WordCount(m, r);
        String[] data = new String[3];
        data[0] = ReadFile.readFile("files.txt");
        data[1] = ReadFile.readFile("files1.txt");
        data[2] = ReadFile.readFile("files2.txt");




        for (int i = 0; i < data.length; i++) {
            String[] words = data[i].split("[\\s\\-\\,\\'\\.\"]");
            for (String word : words) {
                if (Pattern.matches("[a-zA-Z]+", word)) {
                    wc.inMappers[i].add(new Pair(word.toLowerCase(), 1));
                }
            }
        }

        for (InMapper inMapper : wc.inMappers) {
            inMapper.cleanup();
            Collections.sort(inMapper.pairs, new Compare());
        }


        addToReducer(wc);
        printResult(wc);
    }
    private static void addToReducer(WordCount wc) {
        for (InMapper inMapper : wc.inMappers) {
            for (Pair pair : inMapper.map.keySet()) {
                int partition = wc.getPartition(pair.word);
                if (!(wc.reducers[partition].groupByPairs.
                        contains(new GroupByPair(pair.word, pair.fre))
                )) {
                    wc.reducers[partition].groupByPairs.add(new
                            GroupByPair(pair.word, pair.fre));


                } else {
                    GroupByPair groupByPair = wc.reducers[partition].groupByPairs
                            .stream().filter(x -> x.key.equals(pair.word))
                            .findFirst().get();
                    groupByPair.addValues(pair.fre);
                }
            }

        }
    }

    private static void printResult(WordCount wc) {
        for (int i = 0; i < wc.mapperCount; i++) {
            System.out.println("InMapper " + i + "output");
            for (Pair p : wc.inMappers[i].pairs) {
                System.out.println("< " + p.word + ", " + p.fre + "  >");
            }

        }

        for (int i = 0; i < wc.reducerCount; i++) {
            System.out.println("Reducer " + " Output");
            for (GroupByPair p : wc.reducers[i].groupByPairs) {
                System.out.println("< " + p.key + ", " + p.values + " >");

            }
        }

        for (int i = 0; i < wc.reducerCount; i++) {
            System.out.println("Reducer " + i + " OutPut");
            for (GroupByPair p : wc.reducers[i].groupByPairs) {
                System.out.println("< " + p.key + ", " +
                        p.sum() + "  >");
            }
        }

    }
}
