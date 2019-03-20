import java.util.LinkedList;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 *
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * path = "/a/../../b/../c//.//", => "/c"
 * path = "/a//b////c/d//././/..", => "/a/b/c"
 *
 * In a UNIX-style file system, a period ('.') refers to the current directory, so it can be ignored
 * in a simplified path. Additionally, a double period ("..") moves up a directory,
 * so it cancels out whatever the last directory was. For more information,
 * look here: https://en.wikipedia.org/wiki/Path_(computing)#Unix_style
 *
 * Corner Cases:
 *
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class SimplifyPath {

    public static void main(String[] args) {
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
    }

    public static String simplifyPath(String path) {
        char[] chars = path.toCharArray();
        LinkedList<String> p = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            StringBuilder sb = new StringBuilder();
            while(i < chars.length && chars[i] != '/') {
                sb.append(chars[i]);
                i++;
            }


            if (sb.length() > 0) {
                String pathEl = sb.toString();
                if (".".equals(pathEl)) {
                    // ignore
                } else if ("..".equals(pathEl)) {
                    if (!p.isEmpty()) {
                        p.removeLast();
                    }
                } else {
                    p.addLast(pathEl);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String pathEl : p) {
            sb.append("/").append(pathEl);
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
