package org.mantic.datastore;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
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

public class RuleTest3 {

	public static void main(final String[] args) {
		// Create an empty model.
		final String NS = "http://integraweb.ddns.net/";
		final String NS2 = "http://schema.org/";
		// Build a trivial example data set
		final Model model = ModelFactory.createDefaultModel();
		final Resource t1 = model.createResource(NS + "Type1");
		final Resource t2 = model.createResource(NS + "Type2");
		final Property st1 = model.createProperty(NS2, "latitude");
		final Property st2 = model.createProperty(NS2, "longitude");
		model.createProperty(NS, "near");
//		GeoCoordinates [y1=-23.639300808029596, x1=-46.64494812109885, y2=-23.630307591970407, x2=-46.635131478901144]

		final Literal y1 = model.createTypedLiteral(-23.6348042);
		final Literal x1 = model.createTypedLiteral(-46.6400398);
		final Literal y2 = model.createTypedLiteral(-23.6350000);
		final Literal x2 = model.createTypedLiteral(-46.6400000);
//		final Literal y2 = model.createTypedLiteral(-21.2250000);
//		final Literal x2 = model.createTypedLiteral(-48.6700000);		
		model.add(t1, st1, y1);
		model.add(t1, st2, x1);
		model.add(t2, st1, y2);
		model.add(t2, st2, x2);

		final String rules = rules();
		System.out.println(rules);
		BuiltinRegistry.theRegistry.register(new Near());
		final Reasoner reasoner = new GenericRuleReasoner(Rule.rulesFromURL("rules.txt"));
		reasoner.setDerivationLogging(true);
		final InfModel inf = ModelFactory.createInfModel(reasoner, model);
		
		final StmtIterator statements = inf.listStatements();
		while (statements.hasNext()) {
			System.out.println(statements.next());
		}
		
//        checkArgs(length, context);
//        BindingEnvironment env = context.getEnv();
//        Node n1 = getArg(0, args, context);
//        Node n2 = getArg(1, args, context);
//        if (n1.isLiteral() && n2.isLiteral()) {
//            Object v1 = n1.getLiteralValue();
//            Object v2 = n2.getLiteralValue();
//            Node sum = null;
//            if (v1 instanceof Number && v2 instanceof Number) {
//                Number nv1 = (Number)v1;
//                Number nv2 = (Number)v2;
//                if (v1 instanceof Float || v1 instanceof Double 
//                ||  v2 instanceof Float || v2 instanceof Double) {
//                    sum = Util.makeDoubleNode(nv1.doubleValue() + nv2.doubleValue());
//                } else {
//                    sum = Util.makeLongNode(nv1.longValue() + nv2.longValue());
//                }
//                return env.bind(args[2], sum);
//            }
//        }
//        // Doesn't (yet) handle partially bound cases
//        return false;
		
	}
	
	protected static String rules() {
		final StringBuilder sb = new StringBuilder();
//		sb.append("@prefix myns:<http://mantic.hub/#>.\n");
		sb.append("Regrar no arquivo rules.txt");
//		sb.append("@include <RDFS>.");
//		(?x ?hasBase ?b) (?x hasExponent ?e) pow(?b,?e,?z) -> (?x hasValue ?z)
//		[RuleExample: (?X rdf:type NS:System), (?X NS:hasNbAbsence ?Y), greaterThan(?Y, 10) -> (?X rdf:type NS:BadBehaviorOperators)]
//		sb.append("[rule1: (?a myns:latitude ?y1), (?b myns:latitude ?y2), near(?y1, ?y2) -> (?a myns:near ?b)]");
//		sb.append("[rule2: (?a myns:near ?b) -> (?b myns:near ?a)]");
//		sb.append("[rule2: (?a myns:subType ?b), (?b myns:subType ?c) -> (?a myns:subType ?c)]");
		return sb.toString();
	}

}
