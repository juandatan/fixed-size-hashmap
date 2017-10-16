package hashmap;

import static org.junit.Assert.*;

import org.junit.Test;

public class FixedSizeHashMapTester {

	@Test
	public void simpleAddAndRetrieve() {
		FixedSizeHashMap hm = new FixedSizeHashMap(10);
		hm.set("Harry", "Potter");
		assertEquals(hm.get("Harry"), "Potter");
	}
	
	@Test
	public void multipleAddAndRetrieve() {
		FixedSizeHashMap hm = new FixedSizeHashMap(10);
		hm.set("Harry", "Potter");
		hm.set("Hermione", "Granger");
		hm.set("Ron", "Weasley");
		assertEquals(hm.get("Hermione"), "Granger");
		hm.set("Albus", "Dumbledore");
		hm.set("Severus", "Snape");
		assertEquals(hm.get("Albus"), "Dumbledore");
		assertEquals(hm.get("Harry"), "Potter");
	}
	
	@Test
	public void addToFullHashmap() {
		FixedSizeHashMap hm = new FixedSizeHashMap(2);
		hm.set("Harry", "Potter");
		hm.set("Hermione", "Granger");
		assertFalse(hm.set("Ron", "Weasley"));
		assertNull(hm.get("Ron"));
	}

	@Test
	public void addExistingKey() {
		FixedSizeHashMap hm = new FixedSizeHashMap(10);
		hm.set("Harry", "Potter");
		hm.set("Hermione", "Granger");
		assertEquals(hm.get("Hermione"), "Granger");
		assertTrue(Math.abs(hm.load() - 0.2) < 1e-6);
		hm.set("Hermione", "Weasley");
		assertEquals(hm.get("Hermione"), "Weasley");
		assertTrue(Math.abs(hm.load() - 0.2) < 1e-6);
	}
	
	@Test
	public void simpleDelete() {
		FixedSizeHashMap hm = new FixedSizeHashMap(10);
		hm.set("Harry", "Potter");
		hm.set("Hermione", "Granger");
		assertTrue(Math.abs(hm.load() - 0.2) < 1e-6);
		hm.delete("Hermione");
		assertNull(hm.get("Hermione"));
		assertTrue(Math.abs(hm.load() - 0.1) < 1e-6);
	}
	
	@Test
	public void getFromEmptyHashmap() {
		FixedSizeHashMap hm = new FixedSizeHashMap(10);
		assertNull(hm.get("Hermione"));
	}
	
	@Test
	public void getOrDeleteNonExistingKey() {
		FixedSizeHashMap hm = new FixedSizeHashMap(10);
		hm.set("Harry", "Potter");
		assertNull(hm.get("Hermione"));
		assertNull(hm.delete("Hermione"));
	}
	
	@Test
	public void balanceTest() {
		FixedSizeHashMap hm = new FixedSizeHashMap(20);
		hm.set("Harry", "Potter");
		hm.set("Hermione", "Granger");
		hm.set("Ron", "Weasley");
		hm.set("Albus", "Dumbledore");
		hm.set("Severus", "Snape");
		hm.set("Draco", "Malfoy");
		hm.set("Sirius", "Black");
		hm.set("Cho", "Chang");
		hm.set("Minerva", "McGonagall");
		hm.set("Peter", "Pettigrew");
		hm.set("Remus", "Lupin");
		hm.set("Neville", "Longbottom");
		hm.set("Alastor", "Moody");
		hm.set("Rubeus", "Hagrid");
		assertTrue(Math.abs(hm.load() - 0.7) < 1e-6);
		assertTrue(hm.depth(hm.getRoot()) >= 4);
		hm.set("Tom", "Riddle");
		assertTrue(Math.abs(hm.load() - 0.75) < 1e-6);
		assertTrue(hm.depth(hm.getRoot()) == 4);
		
		assertEquals(hm.get("Harry"), "Potter");
		assertEquals(hm.get("Hermione"), "Granger");
		assertEquals(hm.get("Ron"), "Weasley");
		assertEquals(hm.get("Albus"), "Dumbledore");
		assertEquals(hm.get("Severus"), "Snape");
		assertEquals(hm.get("Draco"), "Malfoy");
		assertEquals(hm.get("Sirius"), "Black");
		assertEquals(hm.get("Cho"), "Chang");
		assertEquals(hm.get("Minerva"), "McGonagall");
		assertEquals(hm.get("Peter"), "Pettigrew");
		assertEquals(hm.get("Remus"), "Lupin");
		assertEquals(hm.get("Neville"), "Longbottom");
		assertEquals(hm.get("Alastor"), "Moody");
		assertEquals(hm.get("Rubeus"), "Hagrid");
	}

}
