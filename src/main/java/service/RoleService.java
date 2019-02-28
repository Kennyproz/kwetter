package service;

import Exceptions.RoleExistsException;
import dal.interfaces.RoleDAO;
import models.Role;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
@Stateless
public class RoleService {

    @Inject
    private RoleDAO roleDAO;

    public Role add(Role role) throws RoleExistsException {
        roleDAO.add(role);
        return role;
    }

    public void remove(Role role){
        roleDAO.remove(role);
    }

    public List<Role> roles(){
        return roleDAO.roles();
    }

    public Role getRoleById(long id){
       return roleDAO.getRoleById(id);
    }
}
