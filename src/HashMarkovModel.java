import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HashMarkovModel extends BaseMarkovModel
{

    private HashMap<List<String>,List<String>> myMap;

    public HashMarkovModel(int size){
        super(size);
        myMap = new HashMap<>();
    }

    public HashMarkovModel(){
        this(3);
        myMap = new HashMap<>();
    }



   
    @Override 
    public void processTraining(){

        myMap.clear();
        List<String> list = myWordSequence.subList(0,myModelSize);

        for (int i = myModelSize; i < myWordSequence.size(); i++) 
            {
                if(myMap.containsKey(list))
                {
                    myMap.get(list).add(myWordSequence.get(i));
                }
                else
                {
                    List<String> newList = new ArrayList<>();
                    newList.add(myWordSequence.get(i));
                    myMap.put(list, newList);
                }
                //String next = randomNextString(list);
                list = createNewContext(list, myWordSequence.get(i));
            }

    }

    @Override 
    protected List<String> getFollows(List<String> context) {
        return  myMap.getOrDefault(context, new ArrayList<>());
    }



}