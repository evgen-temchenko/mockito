package org.mockito.internal.matchers;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;
import org.mockito.exceptions.Reporter;

@SuppressWarnings("unchecked")
public class CapturingMatcher<T> extends ArgumentMatcher<T> {
    
    private LinkedList<Object> arguments = new LinkedList<Object>();

    /* (non-Javadoc)
     * @see org.mockito.ArgumentMatcher#matches(java.lang.Object)
     */
    public boolean matches(Object argument) {
        this.arguments.add(argument);
        return true;
    }    

    /* (non-Javadoc)
     * @see org.mockito.ArgumentMatcher#describeTo(org.hamcrest.Description)
     */
    public void describeTo(Description description) {
        description.appendText("<Capturing argument>");
    }

    public T getLastValue() {
        if (arguments.isEmpty()) {
            new Reporter().noArgumentValueWasCaptured();
        } else {
            return (T) arguments.getLast();
        }
        return (T) arguments;
    }

    public List<T> getAllValues() {
        return (List) arguments;
    }
}