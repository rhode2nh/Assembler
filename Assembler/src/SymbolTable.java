import java.util.Hashtable;

/**
 * 1/20/2017
 *
 * @author Nicholas Rhodes and Maximilian Daggy
 */
public class SymbolTable {
    public Hashtable hashtable;

    public SymbolTable(){
        hashtable = new Hashtable();
    }

    public void addEntry(String symbol, int address){
        hashtable.put(symbol, address);
    }

    public boolean contains(String symbol){
        if(hashtable.containsKey(symbol))
            return true;
        return false;
    }

    public int getAddress(String symbol){
        return (int) hashtable.get(symbol);
    }
}
