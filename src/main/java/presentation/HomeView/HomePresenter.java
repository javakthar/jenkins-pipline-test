package presentation.HomeView;

import business.useradministration.boundary.UserStorage;
import com.vaadin.ui.UI;
import presentation.RootUI;

import javax.inject.Inject;

/**
 * Petr Hunka (MiX-CZ)
 */
public class HomePresenter implements HomeViewHandler{

    private HomeView view;
    private UserStorage model;

    //==============================================================================
    //  CONSTRUCTORS 
    //==============================================================================

    @Inject
    public HomePresenter(HomeView view, UserStorage model) {
        this.view = view;
        this.model = model;
        view.addViewHandler(this);
    }

    //==============================================================================
    //  PUBLIC  
    //==============================================================================
    @Override
    public void whoAmI() {
        System.out.println("SIGN OUT");
        view.setUsername(((RootUI) UI.getCurrent()).getCurrUser().toString());
    }

    //==============================================================================
    //  PRIVATE 
    //==============================================================================

    //==============================================================================
    //  GET & SET 
    //==============================================================================

    public HomeView getView() {
        return view;
    }
}
