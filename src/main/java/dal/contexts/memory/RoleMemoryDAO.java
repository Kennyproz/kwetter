package dal.contexts.memory;

import dal.interfaces.RoleDAO;
import models.Role;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class RoleMemoryDAO implements RoleDAO {

    private List<Role> roles;

    public RoleMemoryDAO() {
        roles = new ArrayList<>();
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
    public List<Role> roles() {
        return roles;
    }
}
