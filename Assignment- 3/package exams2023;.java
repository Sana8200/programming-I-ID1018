package exams2023;

public class Word {

    public static void main(String[] args) {
		
		Character[] v = { new Character('m'),
				          new Character('a'),
				          new Character('i'),
				          new Character('l') };
		Word word = new Word(v);
		System.out.println(word);
	
		System.out.println(word.contains('a'));
		
		word.insert(0, 'e'); 
		System.out.println(word);
	}

    
	private Character[] chars;
	
	public Word (Character[] chars){
		this.chars = new Character[chars.length];
		for(int i = 0 ; i < chars.length ; i++) {
			this.chars[i] = chars[i];
		}
	}
		
	public String toString (){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			sb.append(chars[i]);
		}
		return sb.toString();
	}
	
	public boolean contains(Character ch) {
		boolean present = false;
		int i = 0;
		while(!present && i < chars.length) {
			if(chars[i] == ch) {
				present = true;
			}else
				i++;
		}
		return present;
	}
	
	public void insert(int index, Character ch) 
			throws IndexOutOfBoundsException{
		if ( index < 0 || index > chars.length) {
			throw new IndexOutOfBoundsException("index out of range");
		}
		
		Character[] a = new Character[chars.length + 1];
		
		int i = 0;
		while(i < index) {
			a[i] = chars[i];
			i++;
		}
		a[i] = ch;
		
		while(i < chars.length) {
			a[i] = chars[i + 1];
			i++;
		}
		chars = a;
	}
	
	
}