package com.users.boundary;

import com.users.JAXRSConfiguration;
import com.users.control.UsersService;
import com.users.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * @author nkengasong
 */
@RunWith(Arquillian.class)
public class UsersResourceITest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(JAXRSConfiguration.class)
                .addClass(UsersResource.class)
                .addClass(UsersService.class)
                .addClass(User.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
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

    @Test
    @RunAsClient
    public void testGetAllUsers(@ArquillianResource URL baseURL) throws URISyntaxException {
        int status = ClientBuilder.newClient().target(baseURL.toURI()).path("/resources/v1/users").request(MediaType.APPLICATION_JSON).get().getStatus();
        assertEquals(Status.OK.getStatusCode(), status);
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
