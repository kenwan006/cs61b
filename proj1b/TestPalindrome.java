import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testIsPalindrome(){
        boolean a = palindrome.isPalindrome(" ");
        boolean b = palindrome.isPalindrome("a");
        boolean c = palindrome.isPalindrome("persiflage");
        boolean d = palindrome.isPalindrome("noon");

        assertEquals(true, a);
        assertEquals(true, b);
        assertEquals(false, c);
        assertEquals(true, d);
    }

    @Test
    public void testIsPalindrome2(){
        CharacterComparator cc = new OffByOne(); //first comparator; by 1
        boolean a = palindrome.isPalindrome(" ", cc);
        boolean b = palindrome.isPalindrome("a", cc);
        boolean c = palindrome.isPalindrome("flake", cc);
        boolean d = palindrome.isPalindrome("noon", cc);
        assertEquals(true, a);
        assertEquals(true, b);
        assertEquals(true, c);
        assertEquals(false, d);

        CharacterComparator dd = new OffByN(5);  //second comparator; by 5
        boolean A = palindrome.isPalindrome(" ", dd);
        boolean B = palindrome.isPalindrome("a", dd);
        boolean C = palindrome.isPalindrome("tiny", dd);
        boolean D = palindrome.isPalindrome("liang", dd);
        boolean E = palindrome.isPalindrome("noon", dd);
        assertEquals(true, A);
        assertEquals(true, B);
        assertEquals(true, C);
        assertEquals(true, D);
        assertEquals(false, E);

    }
}










