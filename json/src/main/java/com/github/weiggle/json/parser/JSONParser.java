package com.github.weiggle.json.parser;


import com.github.weiggle.json.parser.parser.Parser;
import com.github.weiggle.json.parser.tokenizer.CharReader;
import com.github.weiggle.json.parser.tokenizer.TokenList;
import com.github.weiggle.json.parser.tokenizer.Tokenizer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by code4wt on 17/9/1.
 */
public class JSONParser {

    private Tokenizer tokenizer = new Tokenizer();

    private Parser parser = new Parser();

    public Object fromJSON(String json) throws IOException {
        CharReader charReader = new CharReader(new StringReader(json));
        TokenList tokens = tokenizer.tokenize(charReader);
        return parser.parse(tokens);
    }
}
