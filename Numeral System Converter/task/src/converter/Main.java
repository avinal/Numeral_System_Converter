package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            int rads = in.nextInt();
            in.nextLine();
            String source = in.nextLine();
            int radix = in.nextInt();
            if (radix <= 0 || radix > 36) {
                throw new NumberFormatException("error");
            }
            System.out.println(toBase(toDecimal(source, rads), radix));
        } catch (Exception e) {
            System.out.println("error");
            in.close();
        }
        in.close();
    }

    public static String toDecimal(String num, int radix) {
        String[] parts;
        double decimal;
        num = num.toLowerCase();
        if (radix == 1) {
            return Integer.toString(num.length());
        } else if (radix == 10) {
            return num;
        }
        parts = num.split("[.]");
        if (parts.length == 1) {
            return Integer.toString(Integer.parseInt(parts[0], radix));
        } else {
            decimal = Integer.parseInt(parts[0], radix);
            int expo = radix;
            for (int i = 0; i < parts[1].length(); i++) {
                char c = parts[1].charAt(i);
                decimal += (double) Integer.parseInt(Character.toString(c), radix) / expo;
                expo *= radix;
            }
        }
        return Double.toString(decimal);
    }

    public static String toBase(String num, int radix) {
        String[] parts = num.split("[.]");
        if (radix == 1) {
            return new String("1").repeat(Integer.parseInt(parts[0]));
        }
        if (parts.length == 1) {
            return Integer.toString(Integer.parseInt(parts[0]), radix);
        }
        StringBuilder base = new StringBuilder(Integer.toString(Integer.parseInt(parts[0]), radix) + ".");
        double frac = Double.parseDouble("0." + parts[1]);
        for (int i = 0; i < 5; i++) {
            frac *= radix;
            String[] apart = Double.toString(frac).split("[.]");
            base.append(Integer.toString(Integer.parseInt(apart[0]), radix));
            frac = Double.parseDouble("0." + apart[1]);
        }
        return base.toString();
    }

}
