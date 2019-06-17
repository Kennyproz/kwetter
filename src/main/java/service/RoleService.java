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


    /**
     * Returns all roles of the given user
     * @param userId
     * @return
     */
    public List<Role> userRoles(long userId) {
        return roleDAO.getRolesByUserId(userId);
    }


    /**
     * Returns the role with given id
     *
     * @param roleId
     * @return
     */
    public Role getRoleById(long roleId) {
        return roleDAO.getRoleById(roleId);
    }

    /**
     * Adds a role to the user with the given id
     *
     * @param userId
     * @param roleId
     * @return
     */
    public boolean addRoleToUser(long userId, long roleId){
        return roleDAO.addRoleToUser(userId,roleId);
    }

    /**
     * Removes a role of the user with the given id
     *
     * @param userId
     * @param roleId
     * @return
     */
    public void removeRoleFromUser(long userId, long roleId){
        roleDAO.removeRoleFromUser(userId,roleId);
    }
}
