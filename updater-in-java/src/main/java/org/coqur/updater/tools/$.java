package org.coqur.updater.tools;

import org.coqur.updater.exception.InvalidVersionCodeException;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Common tools
 *
 * @author Quanyec
 */
public class $ {

    private static String[] split(String sv) {
        try {
            return sv.split("\\.");
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * example:
     * 1. isAnIntegerStr("10")  ->  true
     * 2. isAnIntegerStr("10.10) ->  false
     * 3. isAnIntegerStr("10A0) ->  false
     */
    public static boolean isValidVersion(String sv) {
        String[] vCodes = split(sv);
        if (vCodes == null) {
            return false;
        }

        for (String vc : vCodes) {
            if (!isValidVersionCode(vc)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidVersionCode(String vCode) {
        for (int i = 0; i < vCode.length(); ++i) {
            char c = vCode.charAt(i);
            if (!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
                // is an integer string
                return false;
            }
        }
        return true;
    }

    /**
     * Compare the two of string-versions number
     *
     * @param sv1 string-version1
     * @param sv2 string-version2
     * @return `true` if sv1 is bigger than sv2, otherwise `false`
     */
    public static boolean isBiggerVersion(String sv1, String sv2) throws InvalidVersionCodeException {
        String[] vCodes1 = split(sv1);
        String[] vCodes2 = split(sv2);
        if (vCodes1 == null || vCodes2 == null) {
            throw new InvalidVersionCodeException("Invalid version code");
        }
        for (String sv : vCodes1) {
            if (!isValidVersionCode(sv)) {
                throw new InvalidVersionCodeException("Invalid version code");
            }
        }
        for (String sv : vCodes2) {
            if (!isValidVersionCode(sv)) {
                throw new InvalidVersionCodeException("Invalid version code");
            }
        }
        if (vCodes1.length <= vCodes2.length) {
            for (int i = 0; i < vCodes1.length; ++i) {
                int cc = compareVersionCode(vCodes1[i], vCodes2[i]);
                if (cc == 1) {
                    return true;
                } else if (cc == -1) {
                    return false;
                }
            }
        } else {
            int i = 0;
            for (; i < vCodes2.length; ++i) {
                int cc = compareVersionCode(vCodes1[i], vCodes2[i]);
                if (cc == 1) {
                    return true;
                } else if (cc == -1) {
                    return false;
                }
            }
            return vCodes1.length > i;
        }
        return false;
    }

    /**
     * compared version code
     *
     * @param v1
     * @param v2
     * @return -1 when lesser, 0 when equal, 1 when bigger
     */
    private static int compareVersionCode(String v1, String v2) {
        if (v1.length() == v2.length()) {
            int i = 0;
            for (; i < v1.length(); ++i) {
                char vc1 = v1.charAt(i);
                char vc2 = v2.charAt(i);
                if (vc1 > vc2) {
                    return 1;
                } else if (vc1 < vc2) {
                    return -1;
                }
            }
            return i == v1.length() ? 0 : -1;
        } else {
            if (v1.length() > v2.length()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static String readUrlText(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.addRequestProperty("Connection", "Keep-Alive");
        conn.addRequestProperty("Content-Type", "*/*");
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");

        StringBuilder sb = new StringBuilder();
        InputStream is = conn.getInputStream();
        byte[] buff = new byte[256];
        int hasRead;
        while ((hasRead = is.read(buff)) != -1) {
            sb.append(new String(buff, 0, hasRead));
        }
        return sb.toString();
    }
}
