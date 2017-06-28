package com.tfr.rulesEngine.example.test.model;

import com.tfr.rulesEngine.example.model.Beer;
import com.tfr.rulesEngine.example.model.Styles;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/27/2017.
 */
public class TestBeer {

    @Test
    public void testIsStyleIpa_GivenIpa_ExpectTrue() {
        Beer checksAndBalances = new Beer("Checks and Balances IPA" , 6.5, 65, 45, 1.065);
        assertTrue(checksAndBalances.isStyle(Styles.IPA));
    }

    @Test
    public void testIsStyleIpa_GivenRed_ExpectFalse() {
        Beer rosieRedAle = new Beer("Rosie's Red Ale" , 5.5, 35, 85, 1.055);
        assertFalse(rosieRedAle.isStyle(Styles.IPA));
    }

}
