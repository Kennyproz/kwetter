package dal.interfaces;

import models.Role;

import java.util.List;

public interface RoleDAO {
    boolean add(Role role);
    void remove(Role role);
    Role getRoleById(int roleId);
    List<Role> roles();

}
