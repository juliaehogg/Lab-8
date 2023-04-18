package utilities;
import java.util.Comparator;
import java.util.LinkedList;

public class LinkedEquivalenceClass <T> {

    private T canonical;
    private Comparator<T> comparator;
    private LinkedList<T> rest;
    
    public LinkedEquivalenceClass(Comparator<T> comparator) {
        this.comparator = comparator;
        rest = new LinkedList<>();
    }
    
    public T canonical() {
        return canonical;
    }

    public boolean isEmpty() {
    	//true if canonical and rest are both empty
        return canonical == null && rest.isEmpty();
    }

    public void clear() {
    	//clear both null and rest
        canonical = null;
        rest.clear();
    }

    public void clearNonCanonical() {
    	//clear the rest
        this.rest.clear();
    }

    public int size() {
    	//size = rest size plus the canonical element
        return rest.size() + 1;
    }

    public boolean add(T element) {
    	//check if canonical exist
        if (canonical == null) {
        	//if it does not then element is the new canonical
            canonical = element;
            return true;
        }
        //check if element belongs to the canonical
        if (comparator.compare(canonical, element) == 0) {
        	rest.add(element);
        	return true;
        }
        return false;
       
    }

    public boolean contains(T target) {
    	//canonical does not exist
        if (canonical == null) {
            return false;
        }
        //if it exist, check if canonical is comparator to target
        if (comparator.compare(canonical, target) == 0) {
            return true;
        }
        //check if target is contained in the rest
        return rest.contains(target);
    }

    public boolean belongs(T target) {
        if (canonical == null) {
            return false;
        }
        //canonical compared to the target
        return comparator.compare(canonical, target) == 0;
    }

    // Removes a value from _rest
 	public boolean remove(T target) {
 		return rest.remove(target);
 	}

    public boolean removeCanonical() {
 		if (canonical.equals(null)) return false;
 		canonical = null;
 		return true;
 	}
    

    public boolean demoteAndSetCanonical(T element) {
        if (contains(element)) {
        	//save the previous canonical
            T prev_Can = canonical;
          //set the new element equal to canonical
            canonical = element;
            //add the removed canonical to the rest
            add(prev_Can);

            return true;
        }
        return false;
    }

    public String toString() {
    	return canonical + " | " + rest.toString();
    }
}
