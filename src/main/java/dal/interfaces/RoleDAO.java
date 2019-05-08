package dal.interfaces;

import Exceptions.RoleExistsException;
import models.Role;

import java.util.List;

public interface RoleDAO {
    Role add(Role role) throws RoleExistsException;
    void remove(long roleId);
    Role getRoleById(long roleId);
    List<Role> roles();
    List<Role> getRolesByUserId(long userId);
    void removeRoleFromUser(long userId, long roleId);
    boolean addRoleToUser(long userId, long roleId);


}
