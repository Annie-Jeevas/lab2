/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Анюта
 */
public class ShootMarkTest {
    private ShootMark shoot;
    public ShootMarkTest() {
    }
    
    @Before
    public void setUp() {
        shoot = new ShootMark();
        shoot.setId(1L);
        shoot.setSlip(true);
        shoot.setTime(90800L);
    }

    @Test
    public void testSomeMethod() {
        assertEquals("", shoot.toJson());
    }
    
}
