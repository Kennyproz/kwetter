package dal.interfaces;

import models.Role;

import java.util.List;

public interface RoleDAO {
    boolean add(Role role);
    void remove(Role role);
    List<Role> roles();

}
