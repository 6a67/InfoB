package visitor;

/**
 * Classes that implement the interface Visitor assure that the implement the
 * method {@link #visit(Object)}. The method {@link #visit(Object)} will be
 * called by a {@link Visitable} instance for every Object it visits during one
 * call of the method {@link Visitable#accept(Visitor)}.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 * @param <E>
 *           type of the elements which can be visited by this Visitor
 * 
 * @see Visitable
 */
public interface Visitor<E> {

   /**
    * Called by the method {@link Visitable#accept(Visitor)} for every element it
    * visits. The visiting can be stopped by returning <code>false</code>.
    * 
    * @param o
    *           the element that has just been visited by
    *           {@link Visitable#accept(Visitor)}
    * @return <code>true</code> if the visit should be continued until every
    *         element in a {@link Visitable} has been visited once, else
    *         <code>false</code>
    */
   public boolean visit(E o);

}
