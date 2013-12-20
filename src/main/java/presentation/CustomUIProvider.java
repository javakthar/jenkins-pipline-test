package presentation;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

/**
 * Petr Hunka (MiX-CZ)
 */
public class CustomUIProvider extends UIProvider {

    private InjectProvider injectProvider;

    //==============================================================================
    //  CONSTRUCTORS 
    //==============================================================================
    public CustomUIProvider(InjectProvider injectProvider) {
        this.injectProvider = injectProvider;
    }

    //==============================================================================
    //  PUBLIC  
    //==============================================================================
    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent uiClassSelectionEvent) {
        return RootUI.class;
    }

    @Override
    public UI createInstance(UICreateEvent event) {
        return new RootUI(this.injectProvider);
    }
//==============================================================================
    //  PRIVATE 
    //==============================================================================

    //==============================================================================
    //  GET & SET 
    //==============================================================================

}
