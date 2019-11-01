public class Palindrome {

    public Palindrome() {
    }

    /** convert a string to Deque type. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        int length = word.length();
        for (int i = length - 1; i >= 0; i--) {
            char letterToAdd = word.charAt(i);
            deque.addFirst(letterToAdd);
        }
        return deque;
    }

    /** a help function used to check the palindrome recursively */
    private boolean isPalindromeHelper(Deque deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        char start = (Character) deque.removeFirst();
        char end = (Character) deque.removeLast();
        return (start == end) && isPalindromeHelper(deque);
    }
    /**check if a string is palindrome; True -- "noon", "aa",""; False --"abc" */
    public boolean isPalindrome(String word) {
        Deque deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }

    /** a help function used to check the palindrome recursively */
    private boolean isPalindromeHelper(Deque deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        char start = (Character) deque.removeFirst();
        char end = (Character) deque.removeLast();
        return (cc.equalChars(start, end)) && isPalindromeHelper(deque, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque deque = wordToDeque(word);
        return isPalindromeHelper(deque, cc);
    }

}



