package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SimpleIntegration {

	@Test
	public void testSimpleIntegrationTest() {
		assertEquals(25, 26 - 1);
	}

	@Test
	public void testAnotherSimpleIntegrationTest() {
		assertEquals(25, 29 - 4);
	}
}
