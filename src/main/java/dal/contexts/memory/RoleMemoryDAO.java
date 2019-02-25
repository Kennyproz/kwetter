package dal.contexts.memory;

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
    public boolean add(Role role) {
        if(roles.contains(role)){
            return false;
        } else{
            return roles.add(role);
        }
    }

    @Override
    public void remove(Role role) {
        roles.remove(role);
    }

    @Override
    public Role getRoleById(int roleId) {
        for (Role r : roles){
            if(r.getId() == roleId){
                return r;
            }
        }
        return null;
    }

//    @Override
//    public Role getRole(int roleId) {
//        for (Role role : roles){
//            if (role.getId().equals(roleId)) {
//                return role;
//            }
//        }
//        return null;
   // }

    @Override
    public List<Role> roles() {
        return roles;
    }
}
