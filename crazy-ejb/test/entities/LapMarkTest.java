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
public class LapMarkTest {
    private LapMark lap;
    
    
    public LapMarkTest() {
    }
    
    @Before
    public void setUp() {
        lap = new LapMark();
        lap.setTime(34700L);
       
    }

    @Test
    public void testSomeMethod() {
       assertEquals("", lap.toJson()); 
    }
    
}
