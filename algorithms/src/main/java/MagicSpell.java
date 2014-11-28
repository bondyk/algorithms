/**
 * Created by y.bondaruk on 17.10.2014.
 */
public class MagicSpell {


    public static void main(String[] args) {
        MagicSpell task = new MagicSpell();
        System.out.println("Expected ZABZSGHNAZZHBNVAZGGGAGGAZA. Actual: " + task.fixTheSpell("AZBASGHNAZAHBNVZZGGGAGGZAZ"));
    }

    public String fixTheSpell(String spell) {
        char[] chars = spell.toCharArray();
        int n = chars.length;
        char[] res = new char[chars.length];

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (ch != 'A' && ch != 'Z') {
                res[i] = ch;
            }
        }

        int idx = n - 1;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (ch == 'A' || ch == 'Z') {
                idx = getNextFreePos(res, idx);
                res[idx] = ch;
            }
        }

// AZBASGHNAZAHBNVZZGGGAGGZAZ
// ZABZSGHNAZZHBNVAZGGGAGGAZA
// ZABZSGHNAZZHBNVAZGGG
        return new String(res);
    }

    private int getNextFreePos(char[] res, int idx) {
        while (res[idx] != 0 && idx >= 0) {
            idx--;
        }
        return idx;
    }
}
