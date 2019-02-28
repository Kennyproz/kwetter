package dal.interfaces;

import Exceptions.RoleExistsException;
import models.Role;

import java.util.List;

public interface RoleDAO {
    Role add(Role role) throws RoleExistsException;
    void remove(Role role);
    Role getRoleById(long roleId);
    List<Role> roles();

}
