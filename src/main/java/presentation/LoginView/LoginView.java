package presentation.LoginView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * GEMALTO s.r.o., Prague, ICS <br/>
 * User: Petr Hunka #petr.hunka@gemalto.com <br/>
 * Date: 11/22/13 <br/>
 * Time: 4:29 PM <br/>
 * </p>
 */
public class LoginView extends VerticalLayout implements View {

    private List<LoginViewHandler> handlers;

    private TextField username;
    private PasswordField password;
    private Button singIn;
    private Button registerButt;

    //==============================================================================
    //  CONSTRUCTORS 
    //==============================================================================

    public LoginView() {
        initLayoutComponents();
        createLoginFormLayout();
        createEmptyHandlersList();
    }




    //==============================================================================
    //  PUBLIC  
    //==============================================================================
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}

    public void addViewHandler(LoginViewHandler loginViewHandler){
        handlers.add(loginViewHandler);
    }


    //==============================================================================
    //  PRIVATE 
    //==============================================================================
    private void initLayoutComponents(){
        singIn = new Button("Sign In");
        singIn.setId("loginButt");
        singIn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                for (LoginViewHandler handler : handlers) {
                    handler.signIn();
                }
            }
        });
        registerButt = new Button("Register new user");
        registerButt.setId("registerButt");
        registerButt.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                for (LoginViewHandler handler : handlers) {
                    handler.registerNewUser();
                }
            }
        });
        username = new TextField("Username");
        username.setId("username");
        password = new PasswordField("Password");
        password.setId("password");
    }

    private void createLoginFormLayout(){
        // create login form
        FormLayout fl = new FormLayout();
        fl.setMargin(true);
        fl.addComponent(username);
        fl.addComponent(password);
        fl.addComponent(singIn);
        fl.addComponent(registerButt);
        // add login form to panel
        Panel loginPanel = new Panel("Login panel");
        loginPanel.setContent(fl);
        loginPanel.setSizeUndefined();
        // wrapp all with main layout
        addComponent(loginPanel);
        setSizeFull();
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    private void createEmptyHandlersList() {
        handlers = new ArrayList<LoginViewHandler>();
    }

    //==============================================================================
    //  GET & SET 
    //==============================================================================
    public TextField getUsername() {
        return username;
    }

    public PasswordField getPassword() {
        return password;
    }
}
