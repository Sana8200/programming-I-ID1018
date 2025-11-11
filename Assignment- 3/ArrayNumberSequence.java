// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class ArrayNumberSequence implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this sequence
	public String toString ()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}

    // add code here

	public int length() {
        return numbers.length;
    }

    public double upperBound() {
        double max = numbers[0];
        for (double num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public double lowerBound() {
        double min = numbers[0];
        for (double number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }

    public double numberAt(int position) throws IndexOutOfBoundsException {
        if (position < 0 || position >= length()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        return numbers[position];
    }

    public int positionOf(double number) {
        for (int i = 0; i < length(); i++) {
            if (numbers[i] == number) {
                return i;
            }
        }
        throw new IllegalArgumentException("number not found!"); 
    }

    public boolean isIncreasing() {
        for (int i = 0; i < length() - 1; i++) {
            if (numbers[i] >= numbers[i + 1]) {
                return false;
            }
        }
        return true;
    }


    public boolean isDecreasing() {
        for (int i = 0; i < length() - 1; i++) {
            if (numbers[i] <= numbers[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(double number) {
        for (double num : numbers) {
            if (num == number) {
                return true;
            }
        }
        return false;
    }

    public void add(double number) {
        double[] newArray = new double[length() + 1];
        System.arraycopy(numbers, 0, newArray, 0, length());
        newArray[length()] = number;
        numbers = newArray;
    }

    public void insert(int position, double number) throws IndexOutOfBoundsException {
        if (position < 0 || position > length()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        double[] newArray = new double[length() + 1];

        for (int i = 0; i < position ; i++) {
            newArray[i] = numbers[i];
        }
        newArray[position] = number;
        for (int i = position; i < length(); i++) {
            newArray[i + 1] = numbers[i];
        }
        numbers = newArray;
    }

    public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
        if (position < 0 || position >= length()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if (length() == 2) {
            throw new IllegalStateException("Cannot remove the last element, leaving only two elements");
        }
        double[] newArray = new double[length() - 1];

        for (int i = 0; i < position; i++) {
            newArray[i] = numbers[i];
        }
        for (int i = position + 1; i < length(); i++) {
            newArray[i - 1] = numbers[i];
        }
        numbers = newArray;
    }

    public double[] asArray() {
        double[] arrayCopy = new double[length()];

        for (int i = 0; i < length(); i++) {
            arrayCopy[i] = numbers[i];
        }
        return arrayCopy;
    }
}


