package service;

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

    public boolean add(Role role){
       return roleDAO.add(role);
    }

    public void remove(Role role){
        roleDAO.remove(role);
    }

    public List<Role> roles(){
        return roleDAO.roles();
    }
}
