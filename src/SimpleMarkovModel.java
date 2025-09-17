import java.io.IOException;


/**
 *
 * @author YOUR NAME GOES HERE
 */


public class SimpleMarkovModel extends BaseMarkovModel  {

    public SimpleMarkovModel(int size){
        super(size);
    }

    public SimpleMarkovModel(){
        this(3);
    }

    public static void main(String[] args) throws IOException {
        SimpleMarkovModel mm = new SimpleMarkovModel(2);
        String dirName = "data/shakespeare";

        int size = 500;
        mm.trainDirectory(dirName);
        System.out.printf("seq size: %d\n",mm.getSequence().size());
        String text = mm.generate(size);

        System.out.printf("size of generated text = %d\n",text.length());
        System.out.printf("# different contexts possible = %d\n",mm.differentContexts());
    }
}
