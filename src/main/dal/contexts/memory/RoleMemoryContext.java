package main.dal.contexts.memory;

import main.dal.interfaces.RoleContext;
import main.models.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleMemoryContext implements RoleContext {

    private List<Role> roles;

    public RoleMemoryContext() {
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
