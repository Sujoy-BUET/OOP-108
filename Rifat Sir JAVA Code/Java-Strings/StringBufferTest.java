public class StringBufferTest {
    public static void main(String[] args) {
        //StringBuffer sb = new StringBuffer("hello");
        StringBuilder sb = new StringBuilder("hello");
        // append(), // insert(), // delete(), // reverse()
        // capacity(), // charAt(), // length()
        System.out.println(sb.capacity());
        System.out.println(sb.charAt(0));
        sb.append('w');
        sb.append("orld");
        System.out.println(sb);
        sb.insert(5, ' ');
        System.out.println(sb);
        System.out.println(sb.capacity());
        sb.delete(5, sb.length());
        System.out.println(sb);
        sb.reverse();
        System.out.println(sb);
    }
}
