package org.softlang.company.features;

import org.softlang.company.antlr.Company;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;

import java.io.FileInputStream;
import java.io.IOException;

/*
 *    Implementation of the feature Parsing
 *    using an ANTLR-generated Lexer
 */

public class Parsing {

    public static void parse(String s)
            throws IOException {
        FileInputStream stream = new FileInputStream(s);
        ANTLRInputStream antlr = new ANTLRInputStream(stream);
        Company lexer = new Company(antlr);
       
        while ((lexer.nextToken()).getType()!=Token.EOF) {}
    }

}