package Code.Politics;
import Code.Human.Human;
import Code.Politics.Office;
import Code.Interest.Interest;
import Code.Politics.Skill;
import java.util.List;
import java.util.ArrayList;

//Political profile
public class PolProfile {
	private Human owner;					//Whose profile is this?
	private List<Holder> regnalTitles;
	private List<Office> nobleTitles;		//Peerages
	private List<Office> offices;
	private Interest interest;
	private Skill skill;
//	this.interest 	= 	new Interest();
//	this.skill 		= 	new Skill(this);

	public PolProfile(Human o){
		this.owner = o;
	}

	public void handleDeath(){
		Human h = this.getOwner();
		int a;
		if (this.isRegnant()){
			for(Holder x: this.regnalTitles){
				/*if (x.getHolder().getPerson().isAlive()){
					throw new RuntimeException();
				}*/
				x.getOffice().endTenure();
			}
			this.regnalTitles.get(0).handleNickname();


	/*		if (owner.isMarried()){
				w = owner.getSpouse();
				if (this.isQueenMother(w)){
					w.sRename(Title.QUEEN_MOTHER);
				} else{
					w.sRename(Title.QUEEN_DOWAGER);
				}
			}*/
		}
	}

	public void addRegnalTitle(Holder v){
		if (!this.isRegnant()){	this.makeRegnant();	}
		this.getRegnal().add(v);
	}

	public boolean isQueenMother(Human h){
		for(Holder x: this.getRegnal()){
			if (x.getPerson().isChildOf(h)){
				return true;
			}
		}
		return false;
	}

//Small methods
	public void addRegnalTitles(Office o){		this.regnalTitles.add(o.getHolder());				}
	public void makeRegnant(){					this.regnalTitles = new ArrayList<>();	}
	public boolean isRegnant(){					return this.regnalTitles != null;		}
	public List<Holder> getRegnal(){			return this.regnalTitles;				}
	public Interest getInterest(){ 				return this.interest;					}
	public Skill getSkill(){ 					return this.skill;							}
	public Human getOwner(){									return this.owner;			}

}
