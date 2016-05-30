package collatz;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class CollatzTest {

	@Test
	public void testSimpleComputeSequenceLengths() {
		int [] arr_1 = {0, 1, 2, 8};
		int [] arr_2 = {0, 1, 2, 8, 3, 6, 9, 17};
		
		assertArrayEquals (arr_1, Collatz.simpleComputeSequenceLengths(3));
		assertArrayEquals (arr_2, Collatz.simpleComputeSequenceLengths(7));
	}

	@Test
	public void testMemoizedComputeSequenceLengths() {
		int [] arr_1 = {0, 1, 2, 8};
		int [] arr_2 = {0, 1, 2, 8, 3, 6, 9, 17};
		
		assertArrayEquals (arr_1, Collatz.memoizedComputeSequenceLengths(3));
		assertArrayEquals (arr_2, Collatz.memoizedComputeSequenceLengths(7));
	}

	@Test
	public void testCollatz_1() {
		assertEquals(22, Collatz.collatz_1(7));
		assertEquals(5, Collatz.collatz_1(10));
	}

	@Test
	public void testSequenceOf() {
		List<Long> list_1 = new ArrayList<Long>();
		list_1.add(20L);
		list_1.add(10L);
		list_1.add(5L);
		list_1.add(16L);
		list_1.add(8L);
		list_1.add(4L);
		list_1.add(2L);
		list_1.add(1L);
		List<Long> list_2 = new ArrayList<Long>();
		list_2.add(64L);
		list_2.add(32L);
		list_2.add(16L);
		list_2.add(8L);
		list_2.add(4L);
		list_2.add(2L);
		list_2.add(1L);
		
		assertEquals(list_1, Collatz.sequenceOf(20));
		assertEquals(list_2, Collatz.sequenceOf(64));
	}

	@Test
	public void testLengthOfSequence() {
		assertEquals(9, Collatz.lengthOfSequence(42));
		assertEquals(30, Collatz.lengthOfSequence(43));
	}

	@Test
	public void testLargestValueInSequence() {
		assertEquals(52, Collatz.largestValueInSequence(7));
		assertEquals(16, Collatz.largestValueInSequence(10));

	}

	@Test
	public void testEqualLengthTwins() {
		ArrayList<Pair<Long, Integer>> equalLengthTwinsTest = new ArrayList<Pair<Long, Integer>>();
		equalLengthTwinsTest.add(new Pair<Long, Integer>(12L,10));
		equalLengthTwinsTest.add(new Pair<Long, Integer>(14L,18));
		equalLengthTwinsTest.add(new Pair<Long, Integer>(18L,21));
		equalLengthTwinsTest.add(new Pair<Long, Integer>(20L,8));
		assertThat(Collatz.equalLengthTwins(1,20), is(equalTo(equalLengthTwinsTest)));
		//assertEquals(equalLengthTwinsTest, Collatz.equalLengthTwins(1,20));
		
		ArrayList<Pair<Long, Integer>> equalLengthTwinsTest2 = new ArrayList<Pair<Long, Integer>>();
		equalLengthTwinsTest2.add(new Pair<Long, Integer>(20L,8));
		equalLengthTwinsTest2.add(new Pair<Long, Integer>(22L,16));
		equalLengthTwinsTest2.add(new Pair<Long, Integer>(28L,19));
		equalLengthTwinsTest2.add(new Pair<Long, Integer>(29L,19));
		equalLengthTwinsTest2.add(new Pair<Long, Integer>(34L,14));
		equalLengthTwinsTest2.add(new Pair<Long, Integer>(36L,22));
		equalLengthTwinsTest2.add(new Pair<Long, Integer>(37L,22));
		assertThat(Collatz.equalLengthTwins(20,40), is(equalLengthTwinsTest2));
		//assertEquals(equalLengthTwinsTest, Collatz.equalLengthTwins2(20,40));
		 
		 
	}

	private void assertThat(List<Pair<Long, Integer>> equalLengthTwins,
			Matcher<ArrayList<Pair<Long, Integer>>> matcher) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testEqualMaxValueTwins() {
		ArrayList<Pair<Long, Integer>> equalValueTwinsTest = new ArrayList<Pair<Long, Integer>>();
		equalValueTwinsTest.add(new Pair<Long, Integer>(5L,16));
		equalValueTwinsTest.add(new Pair<Long, Integer>(17L,52));
		assertThat(Collatz.equalMaxValueTwins(1,20), is(equalTo(equalValueTwinsTest)));
		//assertEquals(equalValueTwinsTest, Collatz.equalMaxValueTwins(1,20));
		
		
		ArrayList<Pair<Long, Integer>> equalValueTwinsTest2 = new ArrayList<Pair<Long, Integer>>();
		assertThat(Collatz.equalMaxValueTwins(20,40), is(equalTo(equalValueTwinsTest2)));
		//assertEquals(equalValueTwinsTest2, Collatz.equalMaxValueTwins2(20,40));
		 
	}


	@Test
	public void testOccurrences() {
		int [] arr_1 = {0, 100, 99, 6, 98, 88};
		int [] arr_2 = {0, 1000, 999, 9, 998, 932, 8};
		
		assertArrayEquals (arr_1, Collatz.occurrences(100, 5));
		assertArrayEquals (arr_2, Collatz.occurrences(1000, 6));
	}
	
	@Test
	public void testNumberEven(){
		assertEquals(3, Collatz.numberEven(8));
		assertEquals(12, Collatz.numberEven(14));
	}
	
	@Test
	public void testDiffBetweenMaxAndValue(){
		assertEquals(4, Collatz.diffBetweenMaxAndValue(12));
		assertEquals(69, Collatz.diffBetweenMaxAndValue(19));
	}
}
