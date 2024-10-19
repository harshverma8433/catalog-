import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;
import org.json.JSONObject;

public class SecretSharing {
    private static final BigInteger PRIME = BigInteger.TWO.pow(256).subtract(BigInteger.ONE);

    public static void main(String[] args) {
        String[] fileNames = {"testcase1.json", "testcase2.json"};
        for (String fileName : fileNames) {
            BigInteger secret = findSecret(fileName);
            System.out.println("Secret for " + fileName + ": " + secret);
        }
    }

    private static BigInteger findSecret(String fileName) {
        try {
            String content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(fileName)));
            JSONObject jsonObject = new JSONObject(content);

            JSONObject keys = jsonObject.getJSONObject("keys");
            int k = keys.getInt("k");

            List<BigInteger[]> points = new ArrayList<>();

            for (int i = 1; i <= k; i++) {
                if (jsonObject.has(String.valueOf(i))) {
                    JSONObject point = jsonObject.getJSONObject(String.valueOf(i));
                    int base = Integer.parseInt(point.getString("base"));
                    String value = point.getString("value");
                    BigInteger x = BigInteger.valueOf(i);
                    BigInteger y = new BigInteger(value, base);
                    points.add(new BigInteger[]{x, y});
                }
            }

            return lagrangeInterpolation(points, BigInteger.ZERO);
        } catch (Exception e) {
            e.printStackTrace();
            return BigInteger.ZERO;
        }
    }

    private static BigInteger lagrangeInterpolation(List<BigInteger[]> points, BigInteger x) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < points.size(); i++) {
            BigInteger term = points.get(i)[1];
            for (int j = 0; j < points.size(); j++) {
                if (j != i) {
                    BigInteger numerator = x.subtract(points.get(j)[0]).mod(PRIME);
                    BigInteger denominator = points.get(i)[0].subtract(points.get(j)[0]).mod(PRIME);
                    try {
                        BigInteger inverse = denominator.modInverse(PRIME);
                        term = term.multiply(numerator).multiply(inverse).mod(PRIME);
                    } catch (ArithmeticException e) {
                        term = BigInteger.ZERO;
                        break;
                    }
                }
            }
            result = result.add(term).mod(PRIME);
        }
        return result;
    }
}