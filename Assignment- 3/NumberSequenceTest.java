// NumberSequenceTest.java

/****************************************************************

NumberSequenceTest is a test program for the classes
ArrayNumberSequence and LinkedNumberSequence.

Author
Fadil Galjic

****************************************************************/

import static java.lang.System.out;

class NumberSequenceTest
{
    public static void main (String[] args)
    {
		double[] realNumbers =
		    {8.0, 2.0, 16.0, 5.0, 1.0, 12.0, 4.0};
        NumberSequence sequence = null;
        sequence = new ArrayNumberSequence(realNumbers);
        //sequence = new LinkedNumberSequence(realNumbers);
        out.println("the sequence:");
        out.println(sequence);
        out.println();
        // add code here
        out.println("length: " + sequence.length());
        out.println("one upper bound: " + sequence.upperBound());
        out.println("one lower bound: " + sequence.lowerBound());
        out.println();
        out.println("number at position 3: " + sequence.numberAt(3));
        out.println("position of 8.0: " + sequence.positionOf(8.0));
        out.println();
        out.println("is increasing: " + sequence.isIncreasing());
        out.println("is decreasing: " + sequence.isDecreasing());
        out.println("contains 16.0: " + sequence.contains(16.0));
        out.println();

        sequence.add(32.0);
        out.println("add 32.0: \n" + sequence);

        sequence.insert(7, 0.0);
        out.println("insert 0.0 at position 7: \n" + sequence);

        sequence.removeAt(7);
        out.println("remove at position 7: \n" + sequence + "\n");

        double[] arrayRepresentation = sequence.asArray();
        out.print("corresponding array: \n");
        for (int i = 0; i < arrayRepresentation.length - 1; i++) {
            out.print(arrayRepresentation[i] + " ");
        }
        out.println(arrayRepresentation[arrayRepresentation.length - 1]);
    }
}