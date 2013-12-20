package presentation;

import business.useradministration.entity.User;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import presentation.HomeView.HomePresenter;
import presentation.HomeView.HomeView;
import presentation.LoginView.LoginPresenter;
import presentation.LoginView.LoginView;

/**
 * Petr Hunka (MiX-CZ)
 */
@Title("Arquillian IT")
public class RootUI extends UI {
    // inject provider
    private InjectProvider injectProvider;
    // view presenters
    private LoginPresenter loginPresenter;
    private HomePresenter homePresenter;
    // view shortcuts
    public static final String LOGIN_VIEW = "loginView";
    public static final String HOME_VIEW = "homeView";
    // app navigator
    protected Navigator navigator;
    // user saved in session
    private User currUser;




    public RootUI() {
    }

    public RootUI(InjectProvider injectProvider) {
        this();
        this.injectProvider = injectProvider;
    }

    protected void init(VaadinRequest request) {
        preparePresenters();
        createNavigator();

    }


    private void preparePresenters(){
        loginPresenter = new LoginPresenter(new LoginView(), injectProvider.inject());
        homePresenter = new HomePresenter(new HomeView(), injectProvider.inject());
    }

    private void createNavigator() {
        navigator = new Navigator(this, this);
        getCurrent().getNavigator().addView(LOGIN_VIEW, loginPresenter.getView());
        getCurrent().getNavigator().addView(HOME_VIEW, homePresenter.getView());
        navigator.navigateTo(LOGIN_VIEW);
    }

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }
}
