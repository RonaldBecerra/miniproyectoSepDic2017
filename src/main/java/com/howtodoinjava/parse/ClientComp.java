package com.howtodoinjava.parse;
import org.antlr.v4.runtime.*;


/**
* @author jean 11-05-2020
*/

public class ClientComp {
	
	
	public static void main(String[] args) {
		
		
		// Only one argument is neccesary
		if( args.length != 1) {
			System.out.print("<Usage> java ClientComp MyExpression ");
			System.exit(0);
		}
		
		// Feed the argument to the parser
		CharStream in = CharStreams.fromString(args[0]);
		
		// Define the lexer for the input and edit its way of catching error
		CombLexer lexer = new CombLexer(in);
		lexer.removeErrorListeners();
		lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
		
		// Generate tokens for the parser
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		// Define the parser for the tokens and edit its way of catching error
		CombParser parser = new CombParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(ThrowingErrorListener.INSTANCE);
		
		// Try to parse
		try {		
			System.out.println(parser.expr().value.toString()) ;
		// Catch the error and show it to the user
		}catch(Exception e) {
			System.out.print(e.getMessage());
			System.exit(1);
		}

		// Success
		System.out.print("Parsed properly");
		
	}

}


