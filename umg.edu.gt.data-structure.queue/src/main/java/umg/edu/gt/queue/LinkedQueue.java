package umg.edu.gt.queue;

public class LinkedQueue<T> implements Queue<T> {
	
	private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(T item) {

        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    @Override
    public T dequeue() {

        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T value = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;
        return value;
    }

    @Override
    public T peek() {

        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return head.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

}
