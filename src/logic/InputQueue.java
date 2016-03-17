package logic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Bao Linh
 * This class stores the inputs that the UI sends to the Logic
 */
public class InputQueue {
	
	private static Queue<String> inputQueue;
	
	public static boolean addInput(String input) {
		return inputQueue.offer(input);
	}
	
	public static boolean isEmpty() {
		return inputQueue.isEmpty();
	}
	
	public static String getInput() {
		return inputQueue.poll();
	}
	
	public static void initialize() {
		inputQueue = new LinkedList<String>();
	}
}
