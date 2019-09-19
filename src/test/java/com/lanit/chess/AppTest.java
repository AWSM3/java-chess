package com.lanit.chess;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue(true);
    }

    @Test
    public void shoulCalcIntegral() {
    	double expected = 16.666666666666668;
    	UnivariateFunction function = v -> v;
		UnivariateIntegrator integrator = new SimpsonIntegrator(1.0e-12, 1.0e-8, 1, 32);
		double i = integrator.integrate(100, function, 0, 10);
		assertTrue(i == expected);
    }
}
