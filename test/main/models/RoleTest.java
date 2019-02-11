package main.models;

import main.dal.contexts.memory.RoleMemoryContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private RoleMemoryContext roleMemoryContext;
    private Role role;

    @BeforeEach
    void setUp() {
        roleMemoryContext = new RoleMemoryContext();
        role = new Role("Admin");
    }

    @Test
    void addRole() {
        assertEquals(0,roleMemoryContext.roles().size());
        assertTrue(roleMemoryContext.add(role));
        assertEquals(1,roleMemoryContext.roles().size());
        assertFalse(roleMemoryContext.add(role));
        assertEquals(1,roleMemoryContext.roles().size());
    }

    @Test
    void removeRole(){
        roleMemoryContext.remove(role);
        assertEquals(0,roleMemoryContext.roles().size());
    }
}