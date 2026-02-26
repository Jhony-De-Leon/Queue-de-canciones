package umg.edu.gt.queue;

public interface Queue<T> {

	void enqueue(T item);

    T dequeue();

    T peek();

    boolean isEmpty();

    int size();
	
	
}

