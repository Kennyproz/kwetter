package dal.interfaces;

import Exceptions.RoleExistsException;
import models.Role;

import java.util.List;

public interface RoleDAO {
    Role add(Role role) throws RoleExistsException;
    Role getRoleById(long roleId);
    void remove(long roleId);
    void removeRoleFromUser(long userId, long roleId);
    boolean addRoleToUser(long userId, long roleId);
    List<Role> roles();
    List<Role> getRolesByUserId(long userId);


}
