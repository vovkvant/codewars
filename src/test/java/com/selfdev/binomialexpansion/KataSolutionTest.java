package com.selfdev.binomialexpansion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KataSolutionTest {

    @Test
    public void expandTest() {

        assertEquals("x^2+2x+1", KataSolution.expand("(x+1)^2"));
        assertEquals("p^3-3p^2+3p-1", KataSolution.expand("(p-1)^3"));
        assertEquals("64f^6+768f^5+3840f^4+10240f^3+15360f^2+12288f+4096", KataSolution.expand("(2f+4)^6"));
        assertEquals("1", KataSolution.expand("(-2a-4)^0"));
        assertEquals("144t^2-1032t+1849", KataSolution.expand("(-12t+43)^2"));
        assertEquals("r^203", KataSolution.expand("(r+0)^203"));
        assertEquals("x^2+2x+1", KataSolution.expand("(-x-1)^2"));

    }
}
