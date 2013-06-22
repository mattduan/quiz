/*
 * Given a String, write a routine that converts the string to a long, 
 * without using the built in Java functions that would do this.
 * */


public class StringToLong {
  public static final long MIN_LONG = -9223372036854775808L;
    public static final long MAX_LONG = 9223372036854775807L;
    public static final long SIGNED_CHECK_VALUE = 223372036854775808L;
    public static final long UNSIGNED_CHECK_VALUE = 223372036854775807L;
    public static final int MAX_STRING_DIGITS = 19;
    
    public static long stringToLong(String s) {
		/* code goes here to convert a string to a long */
		
		boolean signed = false;
		if (s.charAt(0) == '-') {
			signed = true;
			s = s.substring(1);
		}
        if (s.length() > MAX_STRING_DIGITS) {
        	if (signed)
        		s = "-" + s;
			System.out.println("ERROR: String "+s+" exceeds the long limit.");
			System.exit(0);
		}
		
		long l = 0L;
		int j = 0;
		for (int i=s.length()-1; i >= 0; i--) {
			int ch = s.charAt(i);
			if (ch > 47 && ch < 58) {
				if (j == MAX_STRING_DIGITS-1) {
					System.out.println(j);
					if (ch == '9') {
						if ((signed && l > SIGNED_CHECK_VALUE) || 
								(!signed && l > UNSIGNED_CHECK_VALUE)) {
							System.out.println("ERROR: String "+s+" exceeds the long limit.");
							System.exit(0);
						}
					}
				}
				l += (ch-48) * (long)Math.pow(10, j);
			} else {
				System.out.println("ERROR: String "+s+" format incorrect.");
				System.exit(0);
			}
			j++;
		}
		if (signed)
			l = -l;
		return l;
	}
		
	public static void test()
	{
	    long i = stringToLong("1234506789123456789");
	    if (i == 1234506789123456789L)
	        System.out.println("Success");
	    else
	        System.out.println("Failure");
	}
    
	public static void main(String[] args) {
        test();
        
        long l = stringToLong("-9223306789123456789");
        if (l == -9223306789123456789L)
	        System.out.println("Success");
	    else
	        System.out.println("Failure " + l);
        
        l = stringToLong("9223306789123456789");
        if (l == 9223306789123456789L)
	        System.out.println("Success");
	    else
	        System.out.println("Failure " + l);
        
        l = stringToLong("932330a56789");
        l = stringToLong("9323306789123456789");
        l = stringToLong("12345067891234567890");
	}

}
