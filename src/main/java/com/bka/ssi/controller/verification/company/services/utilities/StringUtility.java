package com.bka.ssi.controller.verification.company.services.utilities;

import org.apache.commons.lang3.RegExUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * The type String utility.
 */
public class StringUtility {

    /**
     * Escape special characters string.
     *
     * @param str the str
     * @return the string
     */
    public static String escapeSpecialCharacters(String str) {
        String PATTERN = "[\\[+\\]+:{}^~?\\\\/()><=\"!§$%&`´´'#_.;,°|*-]";

        str = RegExUtils.replaceAll(str, PATTERN, "\\\\$0");
        return str;
    }

    /**
     * Encode to encoding string.
     *
     * @param str      the str
     * @param encoding the encoding
     * @return the string
     * @throws IOException the io exception
     */
    public static String encodeToEncoding(String str, String encoding) throws IOException {
        return
                new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(str.getBytes()),
                                Charset.forName(encoding)))
                        .readLine();
    }
}
