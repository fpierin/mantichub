package org.mantic.datastore;

import java.io.PrintWriter;
import java.util.Iterator;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Derivation;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileManager;

public class RuleTest2 {

	private static String fname = "file:///C:/Jena/Tutorial/reasoner/data03.ttl";
	private static String NS = "urn:x-hp:eg/";

	public static void main(final String[] args) {
		Model data = FileManager.get().loadModel(fname);
		String rules = "[rule1: (?a eg:p ?b) (?b eg:p ?c) -> (?a eg:p ?c)]";
		Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
		reasoner.setDerivationLogging(true);
		InfModel inf = ModelFactory.createInfModel(reasoner, data);

		PrintWriter out = new PrintWriter(System.out);
		for (StmtIterator i = inf.listStatements(inf.getResource(NS + "A"), inf.getProperty(NS + "p"),
				inf.getResource(NS + "D")); i.hasNext();) {
			Statement s = i.nextStatement();
			System.out.println("Statement is " + s);
			for (Iterator<Derivation> id = inf.getDerivation(s); id.hasNext();) {
				Derivation deriv = (Derivation) id.next();
				deriv.printTrace(out, true);
			}
		}
		out.flush();
	}

}