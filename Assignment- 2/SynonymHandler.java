// SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
import java.nio.file.Path;
// IOException
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
		System.out.println("Current working directory: " + new File("").getAbsolutePath() + "\n");
        
        BufferedReader reader = new BufferedReader(
	        new FileReader(synonymFile));
        int numberOfLines = 0;
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			numberOfLines++;
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[numberOfLines];
        reader = new BufferedReader(new FileReader(synonymFile));
		for (int i = 0; i < numberOfLines; i++)
		    synonymData[i] = reader.readLine();
		reader.close();

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns the synonym line corresponding to a given word.
    // If the given word is not present, an exception of the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given synonym line to the data.
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes the synonym line corresponding to a given word.
    // If the given word is not present, an exception of the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData, String word)   //#1
			throws IllegalArgumentException
	{
		// add code here		
		int indexRemove = synonymLineIndex(synonymData, word);
	    String[] updatedSynonymData = new String[synonymData.length - 1];
		for (int i = 0, j = 0; i < synonymData.length; i++) {
			if (i != indexRemove) {
				updatedSynonymData[j++] = synonymData[i];
			}
		}
		return updatedSynonymData;

	}		

    // getSynonyms returns synonyms in a given synonym line.
	private static String[] getSynonyms (String synonymLine)    //#2
	{
        // add code here
		int dividerIndex = synonymLine.indexOf('|');
	    String synonymsPart = synonymLine.substring(dividerIndex + 1).strip();
	    return synonymsPart.split(",\\s*");
	}

    // addSynonym accepts synonym data, and adds a given synonym for a given word.
    // If the given word is not present, an exception of the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData, String word, String synonym)   //#3
			throws IllegalArgumentException
	{
        // add code here
		int index = synonymLineIndex(synonymData, word);
	    String[] synonyms = getSynonyms(synonymData[index]);
	    String updatedSynonymLine = word + " | " + String.join(", ", synonyms) + ", " + synonym;
	    synonymData[index] = updatedSynonymLine;
		if (index == -1) {      //if word is not found throw an exception 
		    throw new IllegalArgumentException("Word not found in synonymData");
		}
	}
	
    // removeSynonym accepts synonym data, and removes a given synonym for a given word.
    // If the given word or the given synonym is not present, an exception of the type IllegalArgumentException is thrown.
    // If there is only one synonym for the given word, an 
    // exception of the type IllegalStateException is thrown.
	public static void removeSynonym (String[] synonymData, String word, String synonym)  //#4
	    throws IllegalArgumentException, IllegalStateException
	{
        // add code here
		int index = synonymLineIndex(synonymData, word);
	    String[] synonyms = getSynonyms(synonymData[index]);

	    if (synonyms.length == 1) {
	        throw new IllegalStateException("Cannot remove the only synonym.");
	    }

		int removeIndex = -1;
        for (int i = 0; i < synonyms.length; i++) {
            if (synonyms[i].equals(synonym)) {
                removeIndex = i;
                break;
			}
		}

        if (removeIndex == -1) {
            throw new IllegalArgumentException("Synonym not present for the word ");
		}

	    String[] updatedSynonyms = new String[synonyms.length - 1];
        for (int i = 0, j = 0; i < synonyms.length; i++) {
            if (i != removeIndex) {
                updatedSynonyms[j++] = synonyms[i];
			}
		}
        synonymData[index] = word + " | " + String.join(", ", updatedSynonyms);
	}

    // sortIgnoreCase sorts an array of strings, using the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)   //#5
	{
        // add code here
		for (int i = 0; i < strings.length - 1; i++) {
	        int minIndex = i;
	        for (int j = i + 1; j < strings.length; j++) {
	            if (strings[j].compareToIgnoreCase(strings[minIndex]) < 0) {
	                minIndex = j;
	            }
	        }
	        if (minIndex != i) {
	            String temp = strings[i];
	            strings[i] = strings[minIndex];
	            strings[minIndex] = temp;
	        }
	    }
	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    private static String sortSynonymLine (String synonymLine)   //#6
    {
	    // add code here
		String[] synonyms = getSynonyms(synonymLine);
	    sortIgnoreCase(synonyms);
	    return synonymLine.substring(0, synonymLine.indexOf('|') + 1) + " " + String.join(", ", synonyms);
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)     //#7
	{
        // add code here
		sortIgnoreCase(synonymData);
	    for (int i = 0; i < synonymData.length; i++) {
			synonymData[i] = sortSynonymLine(synonymData[i]);
		}
	}
}