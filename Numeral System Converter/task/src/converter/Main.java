package converter;

import java.util.Scanner;

/**
 * @author Avinal Kumar
 * @since May 10th, 2020
 * <br>
 * We are all quite used to our good old decimal system of numerals. But let's
 * not forget that there are countless other ways to count! Whether we convert numbers
 * from one system to another just for fun or to store large data more efficiently,
 * a converter would be helpful. In this project I created a mathematical helper
 * that will help us convert numbers from system M to system N.
 */
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

    /**
     * Converts number from any base to Decimal Number System. Converts whole
     * numbers as well as floating point number. Number without floating point are
     * directly converted using <strong>Integer.parseInt</strong> and
     * <strong>Integer.toString</strong> methods. Numbers with floating points are
     * converted using algorithm.
     *
     * @param num   Number to be converted.
     * @param radix Radix of the number to be converted.
     * @return Converted Decimal Number string.
     * @see java.lang.Integer#parseInt(String, int)
     * @see java.lang.Integer#toString(int, int)
     * @see java.lang.String#split(String) split
     */
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

    /**
     * Converts a decimal number system to N base system. Whole Numbers as well as n
     * base floating number.Number without floating point are directly converted
     * using <strong>Integer.parseInt</strong> and <strong>Integer.toString</strong>
     * methods. Numbers with floating points are converted using algorithm.
     *
     * @param num   Number in Decimal Number System.
     * @param radix Target radix in which to convert.
     * @return Converted N base Numeral
     * @see java.lang.Double#parseDouble(String)
     * @see java.lang.Double#toHexString(double)
     */
    public static String toBase(String num, int radix) {
        String[] parts = num.split("[.]");
        if (radix == 1) {
            return "1".repeat(Integer.parseInt(parts[0]));
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
