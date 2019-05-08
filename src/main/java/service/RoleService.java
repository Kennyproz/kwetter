package service;

import Exceptions.RoleExistsException;
import dal.interfaces.RoleDAO;
import models.Role;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
@Stateless
public class RoleService {

    @Inject
    private RoleDAO roleDAO;

    /**
     * Creates a Role
     *
     * @param role
     * @return
     * @throws RoleExistsException
     */
    public Role add(Role role) throws RoleExistsException {
        roleDAO.add(role);
        return role;
    }

    /**
     * Removes the role with given roleId
     *
     * @param roleId
     */
    public void remove(long roleId) {
        roleDAO.remove(roleId);
    }

    /**
     * Returns all roles
     *
     * @return
     */
    public List<Role> roles() {
        return roleDAO.roles();
    }

    public List<Role> userRoles(long userId) {
        return roleDAO.getRolesByUserId(userId);
    }


    /**
     * Returns the role with given id
     *
     * @param id
     * @return
     */
    public Role getRoleById(long id) {
        return roleDAO.getRoleById(id);
    }
}
