package com.jpmc.theater;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Theater;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTests extends AbstractPojoTester {
    @Test
    public void testGettersAndSetters() {
        this.testGetterSetter(Customer.class);
    }

    @Test
    public void testEquals() {
        Customer cs = new Customer("Shane", "123" );
        assertEquals("Shane", cs.getName());
        assertTrue( cs.equals(cs) );
    }

    @Test
    public void testNotEquals() {
        Customer cs = new Customer("Shane", "123" );
        Customer cs2 = new Customer("John", "123" );

        assertFalse( cs.equals(cs2));
    }

    @Test
    public void testToString()
    {
        Customer cs = new Customer("Shane", "123" );
        String expected = "Shane"; // put the expected value here
        assertNotEquals(expected, cs.toString());
    }

    @Test
    public void testHash()
    {
        Customer cs = new Customer("Shane", "123" );
        int expected =-1819653360;
        assertEquals(expected, cs.hashCode());

    }
}


