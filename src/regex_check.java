
/**
 *
 * @author soumajit
 */
public class regex_check
{
    public static void main(String[] args) {
        String s="soumajit.chatterjee@gmail.com";
        //System.out.println(s.matches("^[a-zA-Z]*$"));  for [a-z] and [A-Z] chars only
        //System.out.println(s.matches("^[0-9]+$"));  for [0-1] numbers only
        //System.out.println(s.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"));
        //System.out.println(s.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@([a-zA-Z])(.com)$"));
        System.out.println(s.matches("^[a-zA-Z0-9]+(!@#$%^&*(),{}|<>)+@([a-zA-Z])(.com)$"));
        
    }
}
