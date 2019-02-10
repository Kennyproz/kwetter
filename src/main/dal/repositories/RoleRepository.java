package main.dal.repositories;

import main.dal.interfaces.RoleContext;
import main.models.Role;

import java.util.List;

public class RoleRepository {

    private RoleContext context;

    public RoleRepository(RoleContext context) {
        this.context = context;
    }

    boolean add(Role role){
        return context.add(role);
    }
    void remove(Role role){
        context.remove(role);
    }
    List<Role> roles(){
        return context.roles();
    }
}
