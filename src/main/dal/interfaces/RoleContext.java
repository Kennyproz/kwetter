package main.dal.interfaces;

import main.models.Role;

import java.util.List;

public interface RoleContext {
    boolean add(Role role);
    void remove(Role role);
    List<Role> roles();

}
