import java.util.Random;

public class AlgoPractice {
	public int[] getLottoNumbers() {
	    int[] result = new int[6];     
		Random random = new Random();
	    
       for(int i = 0; i < 6; i++) {
    	   
    	   int temp = random.nextInt(45) + 1;
    	   result[i] = temp;
    	     	   
      	   for(int j = 0; j < i; j++) {
    		   if (temp == result[j]) {
    			   
    		   }
    	   }
       }
	    
		return result;
	}
}
