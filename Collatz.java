package collatz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Analyzing the collatz sequence
 * 
 * @author Rajveer Parikh
 * @version January 22, 2015
 */
public class Collatz {


	public static void main(String [] args){
		// Warm up system
		doTimings(100000);
		doTimings(200000);
		doTimings(300000);
		System.out.println("Post warm up");
		//Post warm up
		doTimings(100000);
		doTimings(200000);
		doTimings(300000);
		doTimings(400000);
		doTimings(500000);
		doTimings(600000);
		doTimings(700000);
		doTimings(800000);
		doTimings(900000);
		doTimings(1000000);
		
		for (long i = 1; i <= 100; i++){
			System.out.printf("%d: length   %d, max   %d: ", i, lengthOfSequence(i), largestValueInSequence(i) );
			for (long item : sequenceOf(i)){
				System.out.printf("%d ", item);
			}
			System.out.println("");
		}
		
		System.out.println("Equal Length Twins:" + equalLengthTwins(1,100));
		System.out.println("Equal Value Twins: " + equalMaxValueTwins(1,100));
		System.out.println("Occurences: " + Arrays.toString(occurrences(1000000, 100)));
		System.out.println("Number of even numbers:" + numberEven(8));
		System.out.println("Difference between Max and initial Values: " + diffBetweenMaxAndValue(12));		
	}

	/**
     * Computes the length of the collatz sequence for all numbers up to a given number
     * 
     * @param final value up to which you want to calculate the sequence length
     * @return Integer array of sequence length values
     */
	static int[] simpleComputeSequenceLengths(long n){
		int[] arrayLength = new int [(int) (n+1)];
		arrayLength[1] = 1;
		for (int i = 2; i < n + 1; i++){
			int counter = 1;
			long tempValue = i;
			while (tempValue != 1){
				if (tempValue % 2 == 0){
					tempValue = tempValue / 2;
				}
				else {
					tempValue = (3 * tempValue) + 1;
				}
				counter++;
			}
			arrayLength[i] = counter;
		}
	return arrayLength;
	}
	
	/**
     * Computes the length of the collatz sequence for all numbers up to a given number
     * Remembers previously calculated values and adds automatically if sequence goes to 
     * a value already calculated
     * 
     * @param final value up to which you want to calculate the sequence length
     * @return Integer array of sequence length values
     */
	static int[] memoizedComputeSequenceLengths(long n){
		int[] arrayLength = new int[(int) (n+1)];
		arrayLength[1] = 1;
		arrayLength[2] = 2;
		for (int i = 3; i < n + 1; i++){
			int counter = 0;
			long tempValue = i;
			while ((tempValue != 1) && (tempValue >= i)){
				if (tempValue % 2 == 0){
					tempValue = tempValue / 2;
				}
				else {
					tempValue = (3 * tempValue) + 1;
				}
				counter++;
			}
			if (tempValue != 1){
				counter = counter + arrayLength[(int)tempValue];
			}
			arrayLength[i] = counter;
		}
	return arrayLength;
	}
	
	/**
     * Computes the amount of time taken for the simpleComputeSequenceLength and 
     * memoizedComputSequenceLength methods
     * 
     * @param final value up to which you want to calculate the sequence length
     */
	static void doTimings(long n){	
		System.gc();
		long startTime1 = System.nanoTime();
		for (int i = 1; i < 20; i++){
			simpleComputeSequenceLengths(n);
		}
		long elapsedTime1 = System.nanoTime() - startTime1;
		elapsedTime1 = elapsedTime1 / 20;
		System.out.printf("Average time elapsed simple compute sequence for length = %d = %d \n", n, elapsedTime1);
	
		
		System.gc();
		long startTime2 = System.nanoTime();
		for (int i = 1; i < 20; i++){
			memoizedComputeSequenceLengths(n);
		}
		long elapsedTime2 = System.nanoTime() - startTime2;
		elapsedTime2 = elapsedTime2 / 20;
		System.out.printf("Average time elapsed memoized compute sequence for length = %d = %d \n", n, elapsedTime2);
	}
	
	/**
     * Computes one step of the collatz sequence
     * 
     * @param value for which you want to find the next value in the collatz sequence
     * @return Next value of collatz sequence
     */
	static long collatz_1(long n){
		if (n % 2 == 0){
			n = n / 2;
		}
		else{
			n = (3 *n) + 1;
		}
		return n;
	}
	
	/**
     * Computes the full collatz sequence for a given value
     * 
     * @param value who's collatz sequence is to be caluclated
     * @return Collatz sequence
     */
	static List<Long> sequenceOf(long n){
		List<Long> collatzSequence = new ArrayList<Long>();
		while (n != 1) {
			collatzSequence.add(n);
			if (n % 2 == 0) {
				n = n / 2;
			} else {
				n = n * 3 + 1;
			}
		}
		collatzSequence.add((long)1);
		return collatzSequence;
	}
	
	/**
     * Computes the length of the collatz sequence for a given value
     * 
     * @param value who's collatz sequence length is to be caluclated
     * @return length of Collatz sequence
     */
	static int lengthOfSequence(long n){
		int counter = 1;
		long tempValue = n;
		while (tempValue != 1){
			if (tempValue % 2 == 0){
				tempValue = tempValue / 2;
				counter++;
			}
			if ((tempValue % 2 != 0) && (tempValue != 1)){
				tempValue = (3 * tempValue) + 1;
				counter++;
			}
		}
	return counter;
	}
	
	/**
     * Computes the largest value in the collatz sequence for a given value
     * 
     * @param value who's largest value of the collatz sequence is to be caluclated
     * @return largest value in Collatz sequence
     */
	static long largestValueInSequence(long n){
		long tempValue = n;
		long largestValue = 1;
		while (tempValue != 1){
			if (tempValue % 2 == 0){
				if (tempValue >= largestValue){
					largestValue = tempValue;
				}
				tempValue = tempValue / 2;
			}
			if ((tempValue % 2 != 0) && (tempValue != 1)){
				if (tempValue >= largestValue){
					largestValue = tempValue;
				}
				tempValue = (3 * tempValue) + 1;
			}
		}
	return largestValue;
	}
	
	/**
     * Computes all equal length twins within a given range of collatz sequences
     * 
     * @param lower range to calculate equal length twins
     * @param upperrange to calculate equal length twins
     * @return equal length twins within the specified range
     */
	static List<Pair<Long, Integer>> equalLengthTwins(long lo, long hi){
		ArrayList<Pair<Long, Integer>> equalLengthTwins = new ArrayList<Pair<Long, Integer>>();
		for (long i = lo; i <= hi; i++){
			int length = lengthOfSequence(i);
			if (length == lengthOfSequence(i+1)){
				equalLengthTwins.add(new Pair<Long, Integer>(i, length));
			}
		}
		return equalLengthTwins;
	}
	
	/**
     * Computes all equal value twins within a given range of collatz sequences
     * 
     * @param lower range to calculate equal value twins
     * @param upperrange to calculate equal value twins
     * @return equal value twins within the specified range
     */
	static List<Pair<Long, Integer>> equalMaxValueTwins(long lo, long hi){
		List<Pair<Long, Integer>> equalMaxValueTwins = new ArrayList<Pair<Long, Integer>>();
		for (long i = lo; i <= hi; i++){
			int max = (int) largestValueInSequence(i);
			if (max == largestValueInSequence(i+1)){
				equalMaxValueTwins.add(new Pair<Long, Integer>(i, max));
			}
		}
		return equalMaxValueTwins;
	}
	
	/**
     * Computes occurrences of all numbers below a given number within all 
     * Collatz sequences up to a given number
     * 
     * @param value up till which the collatz sequences need to be checked
     * @param number up till which the occurrences need to be checked
     * @return integer array of all occurrences
     */
	static int[] occurrences(long n, int counts) {
		int[] arrayCount = new int[(int) (counts+1)];
		for (int i = 1; i < n +1; i++){
			ArrayList<Long> occurrencesList = (ArrayList<Long>) sequenceOf(i);
			for (long occurrencesValue : occurrencesList){
				if (occurrencesValue <= counts){
					arrayCount[(int)occurrencesValue]++;
				}
			}
		}
		return arrayCount;
	}
	
	/**
     * Computes number of even numbers in a given collatz sequence
     * 
     * @param number who's collatz sequence is to be calculated
     * @return total number of even numbers in a given collatz sequence
     */
	static int numberEven(long n){
		int evenCounter = 0;
		long tempValue = n;
		while (tempValue != 1){
			if (tempValue % 2 == 0){
				tempValue = tempValue / 2;
				evenCounter++;
			}
			if ((tempValue % 2 != 0) && (tempValue != 1)){
				tempValue = (3 * tempValue) + 1;
			}
		}
		return evenCounter;
	
	}
	
	/**
     * Computes the difference between the maximum value in a collatz sequence of a number
     * and the number itself
     * 
     * @param number who's collatz sequence is to be calculated
     * @return difference betweeen the maximum value in a collatz sequence of a number
     * and the number itself
     */
	static long diffBetweenMaxAndValue(long n){
		long largestValue = largestValueInSequence(n);
		long diff = largestValue - n;
		return diff;
	}
}
