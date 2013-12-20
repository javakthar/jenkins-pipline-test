package presentation.HomeView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Petr Hunka (MiX-CZ)
 */
public class HomeView extends VerticalLayout implements View {
    private List<HomeViewHandler> handlers;
    private Label question;
    private Label username;
    private Button revealButt;

    //==============================================================================
    //  CONSTRUCTORS 
    //==============================================================================
    public HomeView() {
        initLayoutComponents();
        createHomeViewLayout();
        createEmptyHandlersList();
    }



    //==============================================================================
    //  PUBLIC  
    //==============================================================================
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}

    public void addViewHandler(HomeViewHandler homeViewHandler){
        handlers.add(homeViewHandler);
    }

    //==============================================================================
    //  PRIVATE 
    //==============================================================================
    private void initLayoutComponents(){
        // label
        question = new Label("Who am I?");
        question.setStyleName("h2");
        // username label
        username = new Label();
        username.setId("username");
        // buttn
        revealButt = new Button("Reveal me");
        revealButt.setId("revealButt");
        revealButt.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                for (HomeViewHandler handler : handlers) {
                    handler.whoAmI();
                }
            }
        });
    }

    private void createHomeViewLayout(){
        //fill form
        FormLayout fl = new FormLayout();
        fl.setMargin(true);
        fl.addComponent(question);
        fl.addComponent(username);
        fl.addComponent(revealButt);
        // form
        Panel loginPanel = new Panel("Home panel");
        loginPanel.setContent(fl);
        loginPanel.setSizeUndefined();
        // layout
        addComponent(loginPanel);
        setSizeFull();
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    private void createEmptyHandlersList() {
        handlers = new ArrayList<HomeViewHandler>();
    }

    //==============================================================================
    //  GET & SET 
    //==============================================================================

    public void setUsername(String username) {
        this.username.setValue(username);
    }
}
