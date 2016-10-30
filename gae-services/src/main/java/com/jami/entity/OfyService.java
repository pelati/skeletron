package com.jami.entity;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.util.Closeable;

public class OfyService {
    
    static {
        ObjectifyService.setFactory(new OfyFactory());
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

    /**
     * Wrapper for {@link ObjectifyService#begin(Work)}: this is needed so Objectify can correctly use the custom factory, defined in the <code>static</code> block.
     * <hr>
     * <p>
     * An alternative to run() which is somewhat easier to use with testing (ie, @Before and @After) frameworks. You
     * must close the return value at the end of the request in a finally block. It's better/safer to use run().
     * </p>
     *
     * <p>
     * This method is not typically necessary - in a normal request, the ObjectifyFilter takes care of this housekeeping
     * for you. However, in unit tests or remote API calls it can be useful.
     * </p>
     */
    public static Closeable begin() {
        return ObjectifyService.begin();
    }

    /**
     * Only for unit tests!
     * 
     * @param session
     */
    public static void close(Closeable session) {
        if (session != null) {
            session.close();
        }
    }

    /**
     * Wrapper for {@link ObjectifyService#run(Work)}: this is needed so Objectify can correctly use the custom factory, defined in the <code>static</code> block.<br>
     * You can also pass an {@link OfyWork} instance to this method. If the <code>OfyWork</code> will throw any exceptions, it will be packed in a <code>RuntimeException</code>. 
     * <hr>
     * <p>
     * Runs one unit of work, making the root Objectify context available. This does not start a transaction, but it
     * makes the static ofy() method return an appropriate object.
     * </p>
     *
     * <p>
     * Normally you do not need to use this method. When servicing a normal request, the ObjectifyFilter will run this
     * for you. This method is useful for using Objectify outside of a normal request - using the remote api, for
     * example.
     * </p>
     *
     * <p>
     * Alternatively, you can use the begin() method and close the session manually.
     * </p>
     *
     * @return the result of the work.
     */
    public static <R> R run(Work<R> work) {
        return ObjectifyService.run(work);
    }
}
