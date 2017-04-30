package org.mantic.datastore.rules;

import static java.lang.String.valueOf;

import org.apache.jena.graph.Node;
import org.apache.jena.reasoner.rulesys.RuleContext;
import org.apache.jena.reasoner.rulesys.builtins.BaseBuiltin;

import com.mantichub.core.domain.GeoCoordinates;
import com.mantichub.core.util.GeoUtils;

public class Near extends BaseBuiltin {

	@Override
	public String getName() {
		return "near";
	}

	public int getArgLength() {
		return 4;
	}

    @Override
    public boolean bodyCall(Node[] args, int length, RuleContext context) {
    	try {
    		checkArgs(length, context);
    		Node x1 = getArg(0, args, context);
    		Node y1 = getArg(1, args, context);
    		Node x2 = getArg(2, args, context);
    		Node y2 = getArg(3, args, context);        
    		if (x1.isConcrete() && y1.isConcrete() && x2.isConcrete() && y2.isConcrete()) {
    			Double vx1 = new Double(valueOf(x1.getIndexingValue()));
    			Double vx2 = new Double(valueOf(x2.getIndexingValue()));
    			Double vy1 = new Double(valueOf(y1.getIndexingValue()));
    			Double vy2 = new Double(valueOf(y2.getIndexingValue()));          
    			return isNear(vx1, vx2, vy1, vy2);
    		}
    	} catch (final Exception e) {
		}
    	return false;
    }

	private boolean isNear(Number nvx1, Number nvx2, Number nvy1, Number nvy2) {
		final GeoCoordinates radius = GeoUtils.radius(0.5, nvy1.doubleValue(), nvx1.doubleValue());
		final double rx1 = radius.getX1();
		final double rx2 = radius.getX2();
		final double ry1 = radius.getY1();
		final double ry2 = radius.getY2();                	
		final double px = nvx2.doubleValue();
		final double py = nvy2.doubleValue();
		return (py >= ry1) && (py <= ry2) && (px >= rx1) && (px <= rx2);
	}

}
