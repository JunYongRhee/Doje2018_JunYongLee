package get_prefix;

public class GetPrefixLength {

	public static void main(String[] args) {
		String [] words = {"car","card","carry"};
		System.out.println("공통된 가장 긴 접두어의 길이:"+getMaxPrefixLength(words));
	}
	
	public static int getMaxPrefixLength(String[] word_arr) {
		String max_prefix = word_arr[0];
		
	    for (int i=0;i<max_prefix.length();i++) {
	        char piece = max_prefix.charAt(i);
	        
	        for (int j=0;j<word_arr.length;j++) {
	        	System.out.println( word_arr[j].charAt(i));
	        	if (i==word_arr[j].length() || word_arr[j].charAt(i)!=piece) {
	        		return i; 
	            }
	        }
	    }
	    return max_prefix.length();
	}
}