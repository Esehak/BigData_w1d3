/**
 * Created by Esehak on 5/1/2017.
 */
public class Pair  {

public String word;
public int fre;
    public Pair(){}

    public Pair(String word, int fre){
        this.word = word;
        this.fre = fre;
    }



//    @Override
//    public String toString(){
//        return "("+word + ", " + fre + ")";
//
//    }
//
//    @Override
//    public int compareTo(Pair o) {
//        return word.compareTo(o.word);
//    }
//
//    public String getWord() {
//        return word;
//    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        if (!(obj instanceof Pair))
        {
            return false;
        }

        Pair pair = (Pair)obj;

        return this.word.equals(pair.word);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + (this.word != null ? this.word.hashCode() : 0);
        return hash;
    }
}
