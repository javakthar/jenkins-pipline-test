package IT.business.useradministration.boundary;

import business.useradministration.boundary.UserStorage;
import business.useradministration.control.DBUserRepository;
import business.useradministration.control.UserRepository;
import business.useradministration.entity.NullUser;
import business.useradministration.entity.StoredUser;
import business.useradministration.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * Petr Hunka (MiX-CZ)
 */
@RunWith(Arquillian.class)
public class StorageIT {
    //Create micro-deploy archive
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "IT.jar")
                // add IT classes here
                .addClasses(UserStorage.class, UserRepository.class, DBUserRepository.class,
                        NullUser.class, StoredUser.class, User.class)
                //add persistence.xml of real DB
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                // add CDI descriptor
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    UserStorage userStorage;

    // Real IT objects (entities)
    private User wallace = new StoredUser().setUsername("wallace").setPassword("gemalto");
    private User gromit = new StoredUser().setUsername("gromit").setPassword("gemalto");

    @Test
    @InSequence(1)
    public void create_and_store() throws Exception {
        // store new users
        userStorage.store(wallace);
        userStorage.store(gromit);
    }

    @Test
    @InSequence(2)
    public void user_should_be_stored(){
        // check if its work
        Assert.assertThat(userStorage.getAllUsers().size(), is(2));
        Assert.assertThat(userStorage.getUserByUsername("wallace").getUsername(),
                is(wallace.getUsername()));
    }

    @Test
    @InSequence(3)
    public void user_already_in_repository_throw_exception(){
        try {
            userStorage.store(wallace);
            Assert.fail("Exception must be thrown!");
        } catch (Exception e) {
           Assert.assertThat(e.getCause(),instanceOf(IllegalArgumentException.class));
        }
    }
}
