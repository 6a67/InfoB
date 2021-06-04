package visitor;

/**
 * Classes implementing the Visitable interface assure that they accept a
 * Visitor and show that Visitor every element hold by this Visitable. For
 * example a List, which implements the Visitable interface would traverse
 * through every element after its {@link #accept(Visitor)} method has been
 * called. It then calls for every of its elements {@link Visitor#visit(Object)}
 * .
 * 
 * Thus, the {@link Visitable} interface is together with the {@link Visitor}
 * interface another way to iterate through a group of elements.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 * @param <E>
 *           type of the elements which will be visited
 * 
 * @see Visitor
 */
public interface Visitable<E> {

   /**
    * Iterates through every element of this instance and calls for every
    * element {@link Visitor#visit(Object)}. Stops visiting every element if
    * there are no more elements to be visited or if
    * {@link Visitor#visit(Object)} returns <code>false</code>
    * 
    * @param v
    *           the Visitor which should be called for every element in this
    *           Visitable instance
    */
   public void accept(Visitor<E> v);

}
