import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Esehak on 5/3/2017.
 */
public class Main {

    public static int m;
    public static int r;

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("How Many Mapper that u need: ");
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
                    wc.mappers[i].add(new Pair(word.toLowerCase(), 1));
                }
            }
        }

        for (Mapper mapper : wc.mappers) {
            Collections.sort(mapper.pairs, new Compare());
        }


        addToReducer(wc);
        printResult(wc);
    }
    private static void addToReducer(WordCount wc) {
        for (Mapper mapper : wc.mappers) {
            for (Pair pair : mapper.pairs) {
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
            System.out.println("Mapper " + i + "output");
            for (Pair p : wc.mappers[i].pairs) {
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
