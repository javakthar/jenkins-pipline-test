package business.useradministration.entity;

/**
 * Petr Hunka (MiX-CZ)
 *
 *
 * Implementation of User interface (NullObject pattern). This class should be utilized as
 * return value instead of null.
 */
public class NullUser implements User{
    private String username;
    private String password;
    //==============================================================================
    //  CONSTRUCTORS 
    //==============================================================================
    public NullUser() {
        username = "NULL";
        password = "NULL";
    }


    //==============================================================================
    //  PUBLIC  
    //==============================================================================

    //==============================================================================
    //  PRIVATE 
    //==============================================================================

    //==============================================================================
    //  GET & SET 
    //==============================================================================
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
