package FT.presentation;

import business.useradministration.boundary.UserStorage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.inject.Inject;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Petr Hunka (MiX-CZ)
 */
@RunWith(Arquillian.class)
public class LoginScreenFT {

    static WebArchive microdeploy;

    //Create micro-deploy archive
    @Deployment(testable = false)
    public static WebArchive createDeployment() {

        // vaadin framework must be present in archive
        Collection<String> dependencies = Arrays.asList(new String[]{
                "com.vaadin:vaadin-server",
                "com.vaadin:vaadin-client-compiled",
                "com.vaadin:vaadin-shared",
                "com.vaadin:vaadin-themes"
        });

        File[] libs = Maven.resolver()
                // path is dependend on mvn or IDE (mvn is without vaadin-example)
                .loadPomFromFile("pom.xml")
                .resolve(dependencies)
                .withTransitivity().asFile();

        microdeploy =  ShrinkWrap.create(WebArchive.class, "vaadin.war")
                    .addPackages(true, "business")
                    .addPackages(true, "presentation")
                    .addAsLibraries(libs)
                    .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                    .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return microdeploy;

    }

    @Inject
    private UserStorage userStorage;

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;


    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy
    WebElement loginButt;

    @FindBy
    WebElement registerButt;

    @FindBy
    WebElement revealButt;

    //==============================================================================
    //  CONSTRUCTORS 
    //==============================================================================

    //==============================================================================
    //  PUBLIC  
    //==============================================================================
    @Test
    @InSequence(0)
    public void isDeployed(){
        Assert.assertThat(browser, notNullValue());
    }

    @Test
    @InSequence(1)
    public void registerNewUser(){
        // init app by browser url
        browser.get(deploymentUrl.toExternalForm());
        Graphene.waitForHttp(deploymentUrl.toExternalForm());
        // register new user
        Graphene.guardAjax(registerButt).click();
    }

    @Test
    @InSequence(2)
    public void doSuccesfullLogin() throws InterruptedException {
        // fill login form
        username.sendKeys("demo");
        password.sendKeys("demo");
        // click on sign in butt
        Graphene.guardAjax(loginButt).click();
        // click on sign in butt
        Graphene.guardAjax(revealButt).click();
        Assert.assertThat("demo@demo", is(username.getText()));
    }

    //==============================================================================
    //  PRIVATE 
    //==============================================================================

    //==============================================================================
    //  GET & SET 
    //==============================================================================

}
