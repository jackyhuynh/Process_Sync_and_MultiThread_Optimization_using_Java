/**
 * Truc Huynh
 * 11/11/2020
 * Producer Consumer Implementation
 */
import java.util.LinkedList; 
import java.util.Queue; 
import java.util.Random; 

import java.util.concurrent.locks.Condition; 
import java.util.concurrent.locks.Lock; 
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author Truc Huynh
 *
 */
public class ProducerConsumerUsingConditionVariables {

	/**
	 * 
	 */

	
	public static void main(String[] args) 
	{ 
		// Object on which producer and consumer thread will operate 
		ProducerConsumerImpl sharedObject = new ProducerConsumerImpl(); 
		// create producer threads
		Producer producer = new Producer(sharedObject); 
		//create consumer threads
		Consumer consumer = new Consumer(sharedObject);  
		
		// start producer and consumer threads 
		consumer.start();
		producer.start(); 
	
		
	}// end main 
}

	class ProducerConsumerImpl 
	{ 
		// producer consumer problem data 
		private static final int CAPACITY = 100; 
		private final Queue<Object> queue = new LinkedList<>(); 
		private final Random theRandom = new Random(); 
		
		// lock and condition variables 
		private final Lock aLock = new ReentrantLock(); 
		private final Condition bufferNotFull = aLock.newCondition(); 
		private final Condition bufferNotEmpty = aLock.newCondition();
	
		public void put() throws InterruptedException { 
			aLock.lock(); 
			try { 
				while (queue.size() == CAPACITY) { 
					System.out.println(Thread.currentThread().getName() + " : Buffer is full, waiting"); 
					bufferNotEmpty.await(); 
				} 
				int number = theRandom.nextInt(); 
				boolean isAdded = queue.offer(number); 
				if (isAdded) { 
					System.out.printf("%s added %d into queue %n", Thread 
							.currentThread().getName(), number); 
					
					// signal consumer thread that, buffer has element now 
					System.out.println(Thread.currentThread().getName() 
						+ " : Signalling that buffer is no more empty now"); 
					bufferNotFull.signalAll(); 
				} 
			} 
			finally { 
				aLock.unlock(); 
			} 
		}// end put
		
		public void get() throws InterruptedException { 
			// lock so other thread won't interrupt
			aLock.lock(); 
			try { 
				while (queue.size() == 0) { 
					System.out.println(Thread.currentThread().getName() 
							+ " : Buffer is empty, waiting"); 
					bufferNotFull.await(); 
				} 
				Integer value = (Integer) queue.poll(); 
				if (value != null) { 
					System.out.printf("%s consumed %d from queue %n", Thread 
							.currentThread().getName(), value); 
					// signal producer thread that, buffer may be empty now 
					System.out.println(Thread.currentThread().getName() 
							+ " : Signalling that buffer may be empty now"); 
					bufferNotEmpty.signalAll(); 
				} 
			} 
			finally { 
				// release so that don't cause dead lock 
				aLock.unlock(); 
			} 
		}// end get()
	}
			
	class Producer extends Thread { 
		ProducerConsumerImpl pc; 
		public Producer(ProducerConsumerImpl sharedObject) { 
			// Create a Thread Class name Producer
			super("PRODUCER"); 
			this.pc = sharedObject; 
		} 
		
		@Override 
		public void run() { 
			try { 
				pc.put(); 
				} catch (InterruptedException e) {
					e.printStackTrace(); 
				} 
			} 
		} //end Thread

	class Consumer extends Thread { 
		ProducerConsumerImpl pc; 
		public Consumer(ProducerConsumerImpl sharedObject) { 
			super("CONSUMER"); 
			this.pc = sharedObject; 
		} @Override public void run() { 
			try { 
				pc.get(); 
			} catch (InterruptedException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
			} 
		} 
	}


		




