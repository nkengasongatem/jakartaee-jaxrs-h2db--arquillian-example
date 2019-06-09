package com.users.boundary;

import com.users.JAXRSConfiguration;
import com.users.control.UsersService;
import com.users.entity.User;
import java.net.URISyntaxException;
import java.net.URL;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author nkengasong
 */
@RunWith(Arquillian.class)
public class UsersResourceITest {

    @Deployment(testable = false)
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(JAXRSConfiguration.class, UsersResource.class, UsersService.class, User.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllUsers method, of class UsersResource.
     *
     * @param baseURL
     */
    @Test
    public void testGetAllUsers(@ArquillianResource URL baseURL) throws URISyntaxException  {
//        int status = ClientBuilder.newClient().target(baseURL.toURI()).path("resources/v1/users").request(MediaType.APPLICATION_JSON).get().getStatus();
//        assertEquals(Status.OK.getStatusCode(), status);
    }

    /**
     * Test of getOneUser method, of class UsersResource.
     */
    @Test
    public void testGetOneUser() {
    }

    /**
     * Test of addUser method, of class UsersResource.
     */
    @Test
    public void testAddUser() {
    }

    /**
     * Test of updateUser method, of class UsersResource.
     */
    @Test
    public void testUpdateUser() {
    }
}
