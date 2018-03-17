
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
      UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      System.out.println(validator.isValid("http://www.google.com")); //this properly returns that the url is valid
      System.out.println(validator.isValid("www.google.co"));
      System.out.println(validator.isValid("ww.google.com"));
      System.out.println(validator.isValid("www./f.com"));
      System.out.println(validator.isValid("google.com"));
      System.out.println(validator.isValid("no://www.google.com")); //This assertion seems to reveal a bug (cannot convert to ascii)
   }
   

   /**
    * First partition
    * In this partition, we were able to determine that the authority validator was being called in an improper way
    * On line 318, the scheme is checked for http and if it is http, then the program checks to see if there is a port
    * and invalidates the url if it finds one. This is incorrect. The scheme should be checked if it is a special case
    * where the url is a local url. Basically, if the scheme is "file" it should not allow the port to be valid.
    **/
   public void testGoodUrls()
   {
      final UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      final String[] schemes = {"http://", "ftp://", "h3t://"};
      final String[] authorities = {"www.google.com", "0.0.0.0", "123.co"};
      final String[] ports = {"", ":80", ":0"};
      final String[] paths = {"", "/test", "/$23"};

      for (int i = 0; i < schemes.length; i++)
      {
         for (int j = 0; j < authorities.length; j++)
         {
            for (int k = 0; k < ports.length; k++)
            {
               for (int m = 0; m < paths.length; m++)
               {
                  if (!validator.isValid(schemes[i] + authorities[j] + ports[k] + paths[m]))
                  {
                     System.out.println(validator.isValid(schemes[i] + authorities[j] + ports[k] + paths[m]));
                     System.out.println(schemes[i] + authorities[j] + ports[k] + paths[m]); //whenever port is included in the good url, isValid claims it is bad
                     System.out.println("-------");
                  }
               }
            }
         }
      }
   }


   public void testBadUrls()
   {
      final UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      final String[] schemes = {"http://", "http:/", "3tp://"};
      final String[] authorities = {"go.co", "1.2.3.4", "aaa."};
      final String[] ports = {"", ":80", ":-1", ":65636"};
      final String[] paths = {"", "/test1", "/..", "/test//$23"};

      for (int i = 0; i < schemes.length; i++)
      {
         for (int j = 0; j < authorities.length; j++)
         {
            for (int k = 0; k < ports.length; k++)
            {
               for (int m = 0; m < paths.length; m++)
               {
                  if (validator.isValid(schemes[i] + authorities[j] + ports[k] + paths[m]))
                  {
                     System.out.println(m>0);
                     System.out.println(validator.isValid(schemes[i] + authorities[j] + ports[k] + paths[m]));
                     System.out.println(schemes[i] + authorities[j] + ports[k] + paths[m]); //whenever port is included in the good url, isValid claims it is bad
                     System.out.println("-------");
                  }
               }
            }
         }
      }
   }


   /**
    * This method iterates through urls that have known validity, comparing the results from isValid() with the known validity of the url
    */
   public void testIsValid()
   {
      final UrlValidator validator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
      final String[] schemes = {"http://", "ftp://", "http:/", "3tp://"};
      final Boolean[] schemesExpected = {true, true, false, false};
      final String[] authorities = {"www.google.com", "go.co", "1.2.3.4", "aaa."};
      final Boolean[] authoritiesExpected = {true, true, false, false};
      final String[] ports = {"", ":80", ":-1", ":65636"};
      final Boolean[] portsExpected = {true, true, false, false};
      final String[] paths = {"", "/test1", "/..", "/test//$23"};
      final Boolean[] pathsExpected = {true, true, false, false};
      for (int i = 0; i < schemes.length; i++)
      {
         for (int j = 0; j < authorities.length; j++)
         {
            for (int k = 0; k < ports.length; k++)
            {
               for (int m = 0; m < paths.length; m++)
               {
                  final Boolean expected = schemesExpected[i] && authoritiesExpected[j] && portsExpected[k] && pathsExpected[m];
                  final Boolean actual = validator.isValid(schemes[i] + authorities[j] + ports[k] + paths[m]);
                  assertEquals(schemes[i] + authorities[j] + ports[k] + paths[m], expected, actual);
               }
            }
         }
      }
   }
}
