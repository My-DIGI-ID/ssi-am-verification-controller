package com.bka.ssi.controller.verification.company.services.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class StringUtilityTest {

    @Test
    void escapeSpecialCharacters() {
        String str = "!!\"\"\\\\§§$$%%&&//(())==??``´´**++''##__--::..;;,,>><<°°^^@[[]]||{{}}";
        String escaped = StringUtility.escapeSpecialCharacters(str);

        Assertions.assertEquals("\\!\\!\\\"\\\"\\\\\\\\\\§\\§\\$\\$\\%\\%\\&\\&\\/\\/\\(\\(\\)\\)" +
                "\\=\\=\\?\\?\\`\\`\\´\\´\\*\\*\\+\\+\\'\\'\\#\\#\\_\\_\\-\\-\\:\\:\\.\\.\\;\\;\\,\\," +
                "\\>\\>\\<\\<\\°\\°\\^\\^@\\[\\[\\]\\]\\|\\|\\{\\{\\}\\}", escaped);
    }

    @Test
    void encodeToEncoding() throws IOException {
        String str = "façade";
        String decoded = StringUtility.encodeToEncoding(str, "ISO-8859-1");

        Assertions.assertEquals("faÃ§ade", decoded);
    }
}
