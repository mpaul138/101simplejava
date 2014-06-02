package org.softlang.company.features;

import org.softlang.company.antlr.Company;
import org.antlr.v4.runtime.Lexer;

import org.antlr.v4.runtime.*;

import java.io.FileInputStream;
import java.io.IOException;

/*
 *    Implementation of the feature Total
 *    using an ANTLR-generated Lexer
 */

public class Total {

    public static double total(String s)
            throws IOException {
        double total = 0;
        FileInputStream stream = new FileInputStream(s);
        ANTLRInputStream antlr = new ANTLRInputStream(stream);
        Company lexer = new Company(antlr);
        Token token;
        while ((token = lexer.nextToken()).getType() != Token.EOF) 
            if (token.getType() == Company.FLOAT)
                total += Double.parseDouble(token.getText());
        return total;
    }

}