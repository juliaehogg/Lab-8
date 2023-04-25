package utilities;
import java.util.Comparator;
import java.util.LinkedList;

public class LinkedEquivalenceClass <T> {

	protected T _canonical;
	protected Comparator<T> _comparator;
	protected LinkedList<T> _rest;
    
    public LinkedEquivalenceClass(Comparator<T> comparator) {
        _canonical = null;
    	_comparator = comparator;
        _rest = new LinkedList<T>();
    }
    
    public T canonical() {
        return _canonical;
    }

    public boolean isEmpty() {
        return _canonical == null && _rest.isEmpty();
    }

    public void clear() {
        _canonical = null;
        _rest.clear();
    }

    public void clearNonCanonical() {
        _rest.clear();
    }

    public int size() {
    	// _rest size plus the canonical element
        return _rest.size() + 1;
    }


    public boolean add(T element) {
    	
    	// check if element is null
    	if (element == null) return false;
    	
    	// check if canonical exist
        if (_canonical == null) {
        	
        	//if it does not then element is the new canonical
        	_canonical = element;
            return true;
        }
        
        // check if element belongs to the canonical
        if (_comparator.compare(_canonical, element) == 0) {
        	_rest.add(element);
        	return true;
        }
        return false;
       
    }

    public boolean contains(T target) {
    	//canonical does not exist
        if (_canonical == null) {
            return false;
        }
        //if it exist, check if canonical is comparator to target
        if (_comparator.compare(_canonical, target) == 0) {
            return true;
        }
        //check if target is contained in the rest
        return _rest.contains(target);
    }

    public boolean belongs(T target) {
        if (_canonical == null) {
            return false;
        }
        //canonical compared to the target
        return _comparator.compare(_canonical, target) == 0;
    }

 	public boolean remove(T target) {
 		return _rest.remove(target);
 	}

    public boolean removeCanonical() {
    	if (_canonical.equals(null)) return false;
    	_canonical = null;
    	return true;
    }


    public boolean demoteAndSetCanonical(T element) {
    	if (contains(element)) {
    		//save the previous canonical
    		T prev_Can = _canonical;
    		//set the new element equal to canonical
    		_canonical = element;
    		//add the removed canonical to the rest
    		add(prev_Can);

    		return true;
    	}
    	return false;
    }

    public String toString() {
    	return _canonical + " | " + _rest.toString();
    }
}
