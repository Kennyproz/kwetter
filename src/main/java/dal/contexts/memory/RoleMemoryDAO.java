package dal.contexts.memory;

import Exceptions.RoleExistsException;
import dal.interfaces.RoleDAO;
import models.Role;

import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Alternative
@Stateful
public class RoleMemoryDAO implements RoleDAO {

    private List<Role> roles = new ArrayList<>();

    public RoleMemoryDAO() {
    }

    @Override
    public Role add(Role role) throws RoleExistsException{
        if(roles.contains(role)){
            throw new RoleExistsException("Er bestaat al een rol met de naam: " + role.getName());
        } else{
             roles.add(role);
             return role;
        }
    }

    @Override
    public void remove(long roleId) {
        Role roleToDelete = null;
        for (Role r : roles){
            if (r.getId() == roleId){
                roleToDelete = r;
            }
        }
        roles.remove(roleToDelete);
    }

    @Override
    public Role getRoleById(long roleId) {
        for (Role r : roles){
            if(r.getId() == roleId){
                return r;
            }
        }
        return null;
    }

    @Override
    public List<Role> roles() {
        return roles;
    }

    @Override
    public List<Role> getRolesByUserId(long userId) {
        return null;
    }

    @Override
    public void removeRoleFromUser(long userId, long roleId) {

    }

    @Override
    public boolean addRoleToUser(long userId, long roleId) {
        return false;
    }
}
