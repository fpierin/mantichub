package org.mantic.datastore;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.BuiltinRegistry;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.mantic.datastore.rules.Near;

public class RuleTest {

	public static void main(final String[] args) {
		// Create an empty model.
		final String NS = "http://mantic.hub/";
		// Build a trivial example data set
		final Model model = ModelFactory.createDefaultModel();
		final Resource t1 = model.createResource(NS + "Type1");
		final Resource t2 = model.createResource(NS + "Type2");
		final Resource t3 = model.createResource(NS + "Type3");
		final Property st = model.createProperty(NS, "subType");
		model.add(t1, st, t2);
		model.add(t2, st, t3);

		String rules = rules();
		System.out.println(rules);
		BuiltinRegistry.theRegistry.register(new Near());
		final Reasoner reasoner = new GenericRuleReasoner(Rule.rulesFromURL("rules.txt"));
		reasoner.setDerivationLogging(true);
		final InfModel inf = ModelFactory.createInfModel(reasoner, model);
		
		final StmtIterator statements = inf.listStatements();
		while (statements.hasNext()) {
			System.out.println(statements.next());
		}
		
	}
	
	protected static String rules() {
		final StringBuilder sb = new StringBuilder();
		sb.append("@prefix myns:<http://mantic.hub/#>.\n");
//		sb.append("@include <RDFS>.");
//		(?x ?hasBase ?b) (?x hasExponent ?e) pow(?b,?e,?z) -> (?x hasValue ?z)
//		[RuleExample: (?X rdf:type NS:System), (?X NS:hasNbAbsence ?Y), greaterThan(?Y, 10) -> (?X rdf:type NS:BadBehaviorOperators)]
		sb.append("[rule1: (?a myns:subType ?b), (?b myns:subType ?c) -> (?a myns:subType ?c)]");
		sb.append("[rule2: (?a myns:subType ?b), (?b myns:subType ?c) -> (?a myns:subType ?c)]");
		return sb.toString();
	}

}
