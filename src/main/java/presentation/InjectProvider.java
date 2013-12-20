package presentation;

import business.useradministration.boundary.UserStorage;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Petr Hunka (MiX-CZ)
 *
 *
 * Responsible for injecting EJB services into specific view presenter.
 * Reference on this Provider is passed via UI constructor and implementation
 * of a specific UIProvider.
 *
 */
@Singleton
public class InjectProvider {

    @Inject
    private UserStorage userStorage;

    //==============================================================================
    //  CONSTRUCTORS 
    //==============================================================================

    //==============================================================================
    //  PUBLIC  
    //==============================================================================
    public UserStorage inject(){
        return this.userStorage;
    }

    //==============================================================================
    //  PRIVATE 
    //==============================================================================


    //==============================================================================
    //  GET & SET 
    //==============================================================================

}
