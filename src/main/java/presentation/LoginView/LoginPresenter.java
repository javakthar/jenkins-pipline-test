package presentation.LoginView;

import business.useradministration.boundary.UserStorage;
import business.useradministration.entity.NullUser;
import business.useradministration.entity.StoredUser;
import business.useradministration.entity.User;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import presentation.RootUI;

/**
 * <p>
 * GEMALTO s.r.o., Prague, ICS <br/>
 * User: Petr Hunka #petr.hunka@gemalto.com <br/>
 * Date: 11/22/13 <br/>
 * Time: 4:29 PM <br/>
 * </p>
 */
public class LoginPresenter implements LoginViewHandler{

    private LoginView view;
    private UserStorage model;

    //==============================================================================
    //  CONSTRUCTORS 
    //==============================================================================


    public LoginPresenter(LoginView view, UserStorage model) {
        this.view = view;
        this.model = model;
        view.addViewHandler(this);
    }

    //==============================================================================
    //  PUBLIC  
    //==============================================================================
    @Override
    public void signIn() {
        User user = model.getUserByUsername(view.getUsername().getValue());
        if (!(user instanceof NullUser) || user.getPassword().equalsIgnoreCase(view.getPassword().getValue())){
            ((RootUI) UI.getCurrent()).setCurrUser(user);
            UI.getCurrent().getNavigator().navigateTo(RootUI.HOME_VIEW);
        } else{
            Notification.show("WRONG Credentials", Notification.Type.WARNING_MESSAGE);
        }
    }

    @Override
    public void registerNewUser() {
        //TODO: replace with register form in future
        // hard coded -> create new user with demo/demo
        model.store(new StoredUser().setUsername("demo").setPassword("demo"));
        Notification.show("Register Success", Notification.Type.HUMANIZED_MESSAGE);
    }

    //==============================================================================
    //  PRIVATE 
    //==============================================================================

    //==============================================================================
    //  GET & SET 
    //==============================================================================

    public LoginView getView() {
        return view;
    }
}
