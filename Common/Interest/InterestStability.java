package Interest;
import Common.Basic;
import Politics.Office;
public class InterestStability extends InterestType{
	public void consider(Office off){
		switch(Basic.randint(2)){
			case 0:
				InterestArchitecture.buildFort(off);
			case 1:
				off.lowerTax();
				off.getRuler().gainRenown(1);
				break;
		}
	}
}