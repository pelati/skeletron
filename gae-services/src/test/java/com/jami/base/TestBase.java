package com.jami.base;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.cache.AsyncCacheFilter;
import com.googlecode.objectify.util.Closeable;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;

import java.util.logging.Logger;

/**
 * All tests should extend this class to set up the GAE environment.
 *
 * @author Jeff Schnitzer <jeff@infohazard.org>
 * @see <a href="http://code.google.com/appengine/docs/java/howto/unittesting.html">Unit Testing in Appengine</a>
 */
public class TestBase
{
    /** */
    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger(TestBase.class.getName());

    /** */
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
            // Our tests assume strong consistency
            new LocalDatastoreServiceTestConfig(),// .setDefaultHighRepJobPolicyUnappliedJobPercentage(100),
            new LocalMemcacheServiceTestConfig(), new LocalTaskQueueTestConfig());

    // New Objectify 5.1 Way. See https://groups.google.com/forum/#!topic/objectify-appengine/O4FHC_i7EGk
    protected Closeable session;

    @BeforeClass
    public static void setUpBeforeClass()
    {
        // Reset the Factory so that all translators work properly.
        ObjectifyService.setFactory(new ObjectifyFactory());
    }

    /** */
    @Before
    public void setUp()
    {
        // New Objectify 5.1 Way. See https://groups.google.com/forum/#!topic/objectify-appengine/O4FHC_i7EGk
        this.session = ObjectifyService.begin();

        this.helper.setUp();

        MockitoAnnotations.initMocks(this);
    }

    /** */
    @After
    public void tearDown()
    {
        AsyncCacheFilter.complete();

        // New Objectify 5.1 Way. See https://groups.google.com/forum/#!topic/objectify-appengine/O4FHC_i7EGk
        this.session.close();

        this.helper.tearDown();
    }

    /**
     * Utility methods that puts, clears the session, and immediately gets an entity
     */
    protected <T> T putClearGet(T saveMe)
    {

        Key<T> key = ObjectifyService.ofy().save().entity(saveMe).now();

        try
        {
            Entity ent = ds().get(null, key.getRaw());
            System.out.println(ent);
        }
        catch (EntityNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        ObjectifyService.ofy().clear();

        return ObjectifyService.ofy().load().key(key).now();
    }

    /**
     * Get a DatastoreService
     */
    protected DatastoreService ds()
    {

        return DatastoreServiceFactory.getDatastoreService();
    }

    /**
     * Useful utility method
     */
    protected void assertRefUninitialzied(Ref<?> ref)
    {

        try
        {
            ref.get();
            assert false;
        }
        catch (IllegalStateException ex)
        {
        }
    }

}