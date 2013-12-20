package business.useradministration.boundary;

import business.useradministration.control.UserRepository;
import business.useradministration.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Petr Hunka (MiX-CZ)
 */
@Stateless
public class UserStorage {

    @Inject
    private UserRepository userRepository;

    //==============================================================================
    //  CONSTRUCTORS 
    //==============================================================================

    //==============================================================================
    //  PUBLIC  
    //==============================================================================

    /**
     * Async call for storing StoredUser object into repository
     * @param newUser
     */
//    @Asynchronous
    public void store(User newUser){
        userRepository.store(newUser);
        System.out.println("<<<< New user was stored: " + newUser);
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    //==============================================================================
    //  PRIVATE 
    //==============================================================================

    //==============================================================================
    //  GET & SET 
    //==============================================================================

}
