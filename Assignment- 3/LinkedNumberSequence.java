// LinkedNumberSequence.java

/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence
{
	private class Node
	{
		public double number;
		public Node next;

		public Node (double number)
		{
			this.number = number;
			next = null;
		}
	}

	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[0]);
        Node current = first;
		for (int i = 1; i < numbers.length; i++)
		{
			current.next = new Node(numbers[i]);
			current = current.next;
		}
	}

    // toString returns the character string representing this sequence
	public String toString ()
	{
		String s = "";
		Node n = first;
		while (n.next != null)
		{
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}

    // add code here

	public int length() {
        int countNumbers = 0;
        Node current = first;
        while (current != null) {
            countNumbers++;
            current = current.next;
        }
        return countNumbers;
    }
	
	public double upperBound() {
	      Node current = first;
	      double max = current.number;
	      while (current != null) {
	          if (current.number > max) {
	              max = current.number;
	          }
	          current = current.next;
	      }
	      return max;
	  }

	public double lowerBound() {
        Node current = first;
        double min = current.number;
        while (current != null) {
            if (current.number < min) {
                min = current.number;
            }
            current = current.next;
        }
        return min;
    }

	public double numberAt(int position) throws IndexOutOfBoundsException {
        if (position < 0 || position >= length()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        Node current = first;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.number;
    }

	public int positionOf(double number) {
        Node current = first;
        int position = 0;
        while (current != null) {
            if (current.number == number) {
                return position;
            }
            current = current.next;
            position++;
        }
        throw new IllegalArgumentException("number not found!"); 
    }
	
	public boolean isIncreasing() {
        Node current = first;
        while (current != null && current.next != null) {
            if (current.number >= current.next.number) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

	public boolean isDecreasing() {
        Node current = first;
        while (current != null && current.next != null) {
            if (current.number <= current.next.number) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

	public boolean contains(double number) {
        Node current = first;
        while (current != null) {
            if (current.number == number) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

	public void add(double number) {
        Node newNode = new Node(number);
        if (first == null) {
            first = newNode;
        } else {
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void insert(int position, double number) throws IndexOutOfBoundsException {
        if (position < 0 || position > length()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        Node newNode = new Node(number);
        if (position == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node current = first;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
        if (position < 0 || position >= length()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if (length() == 2) {
            throw new IllegalStateException("Cannot remove the last element, leaving only two elements");
        }
        if (position == 0) {
            first = first.next;
        } else {
            Node current = first;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
    }

    public double[] asArray() {
        double[] array = new double[length()];
        Node current = first;
        for (int i = 0; i < array.length; i++) {
            array[i] = current.number;
            current = current.next;
        }
        return array;
    }
}