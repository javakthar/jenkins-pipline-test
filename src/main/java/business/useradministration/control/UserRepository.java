package business.useradministration.control;

import business.useradministration.entity.User;

import java.util.List;

/**
 * Petr Hunka (MiX-CZ)
 */
public interface UserRepository {
    /**
     * Save user object to DB
     */
    public void store(User user);

    /**
     * Find StoredUser object with specified username. If username is
     * not find, NullUser is return.
     *
     * @param username String
     * @return StoredUser or NullUser (if it's find or not)
     */
    public User getUserByUsername(String username);

    /**
     * Get all User objects from repository
     * @return List<User>
     */
    public List<User> getAllUsers();
}
