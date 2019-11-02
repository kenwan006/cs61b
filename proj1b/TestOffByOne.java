import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    // Your tests go here.
    @Test
    public void testOffByOne(){
        boolean a = offByOne.equalChars('a', 'b');
        boolean b = offByOne.equalChars('A', 'b');
        boolean c = offByOne.equalChars('%', '&');
        boolean d = offByOne.equalChars('a', 'a');
        assertEquals(true, a);
        assertEquals(false, b);
        assertEquals(true, c);
        assertEquals(false, d);
    }
}
