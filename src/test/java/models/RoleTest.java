package models;

import Exceptions.RoleExistsException;
import dal.contexts.memory.RoleMemoryDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.relation.RoleNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private RoleMemoryDAO roleMemoryContext;
    private Role role;

    @BeforeEach
    void setUp() {
        roleMemoryContext = new RoleMemoryDAO();
        role = new Role("Admin");
    }

    @Test
    void addRole() throws RoleExistsException {
        assertEquals(0,roleMemoryContext.roles().size());
        assertEquals(role,roleMemoryContext.add(role));
        assertEquals(1,roleMemoryContext.roles().size());
        try {
            roleMemoryContext.add(role);
        } catch (RoleExistsException e) {
        }
        assertThrows(RoleExistsException.class, () -> {
            roleMemoryContext.add(role);
        });
        assertEquals(1,roleMemoryContext.roles().size());
    }

    @Test
    void removeRole(){
        roleMemoryContext.remove(role);
        assertEquals(0,roleMemoryContext.roles().size());
    }
}