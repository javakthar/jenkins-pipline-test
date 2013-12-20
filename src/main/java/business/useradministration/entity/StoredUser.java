package business.useradministration.entity;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Petr Hunka (MiX-CZ)
 */
@Entity
@NamedQueries({
        @NamedQuery(name = StoredUser.FIND_BY_USERNAME, query = "select s from StoredUser s where s.username = :" + StoredUser.USERNAME),
        @NamedQuery(name = StoredUser.FIND_ALL, query = "select s from StoredUser s")
})
public class StoredUser implements User{
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(length = 64)
    @Size(min = 4, max = 64)
    @NotNull
    private String username;

    @Column(length = 64)
    @Size(min = 4, max = 8)
    @NotNull
    private String password;

    //==============================================================================
    //  STATIC
    //==============================================================================
    /**
     * Find StoredUser object by its username parameter in repository
     */
    public static final String FIND_BY_USERNAME = "StoredUser.findByUsername";

    /**
     * Find all StoredUser objects in DB
     */
    public static final String FIND_ALL = "StoredUser.findAll";

    /**
     * Query parameter
     */
    public static final String USERNAME = "username";

    //==============================================================================
    //  CONSTRUCTORS
    //==============================================================================
    public StoredUser() {
    }


    //==============================================================================
    //  PUBLIC
    //==============================================================================
    @Override
    public String toString() {
        return this.username + "@" + this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoredUser user = (StoredUser) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    //==============================================================================
    //  PRIVATE
    //==============================================================================
    @PostConstruct
    private void init(){
        username = null;
        password = null;
    }

    //==============================================================================
    //  GET & SET
    //==============================================================================
    public String getUsername() {
        return username;
    }

    public StoredUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public StoredUser setPassword(String password) {
        this.password = password;
        return this;
    }


}
