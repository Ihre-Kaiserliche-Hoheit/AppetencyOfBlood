package Code.Politics;
import Code.Common.Nick;
import Code.Common.HTML;

import Code.Common.Event;
import Code.Human.Human;
import Code.Relationship.Marriage;
import Code.Politics.*;
import Code.Ancestry.*;
import Code.Common.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Holder{
	private ArrayList<Event> events;
	private ArrayList<Ruler> regents;
	private Calendar end;
	private Calendar start;
	private Claim claim;
	private Human consort;
	private Human person;
	private Office office;
	private Ruler ruler;
	private String name;
	private String notes;

	public Holder(Human person, Office office){
		this.person = 	person;
		this.claim = 	new Claim();
		if (office.getLineage().getPriority() < 3){
			this.claim.combine(this, office);
		} else {
			this.claim.setSpecial(1);
		}

		this.start = 	(Calendar) Basic.date.clone();
		this.notes = 	"";
		this.regents =	new ArrayList<>();
		this.events = 	new ArrayList<>();
		this.office = 	office;
		person.addEvent(Event.E101);
	}

	public void setConsort(Human c){
		c.setOffice(this.office);
		this.consort = c;
		this.office.addToConsortList(c);
		if (c.isFemale()){
			c.setTitle(Title.QUEENCONSORT);
			c.sRename(Title.QUEENCONSORT);
		}
	}

	public static Holder regnafy(Human person, Office office){
		Holder holder = new Holder(person, office);
		Territory territory = holder.getOffice().getTerritory();
		holder.name = RegnalName.regnafy(holder, office);
		System.out.println(holder.name);
		return holder;
	}

	public void fixTheFirst(){
		if (this.getPerson().isMale()){
			this.name = (this.getPerson().getForename()+" I");
		} else{
			this.name = (this.getPerson().getForename()+" I");
		}
		this.person.getName().setFull(this.getName());
	}

	public String getReign(){
		String x = this.getStartYearStr();
		if(this.hasEnded()){
			if(this.start.get(Calendar.YEAR) != this.end.get(Calendar.YEAR)){
				x += "–";
				x = x+this.end.get(Calendar.YEAR);
			}
			return x;
		}
		else{
			return x+"–PRSN";
		}
	}

	public String getReignLength(){
		String rl = "";
		if(this.hasEnded()){
			rl += Integer.toString((this.end.get(Calendar.YEAR)-this.start.get(Calendar.YEAR)));
		}
		else{
			rl += Integer.toString((Basic.date.get(Calendar.YEAR)-this.start.get(Calendar.YEAR)));
		}
		return rl;
	}

	public int getReignLengthExact(){
		if (this.hasEnded()){
		 	return Basic.getDaysBetween(this.getStart(), this.getEnd());
		} else {
			return Basic.getDaysBetween(this.getStart(), Basic.getDate());
		}
	}

	public int getReignLengthYears(){
		return this.getReignLengthExact()/365;
	}

	public String getReignYearsAndDays(){
		int d = this.getReignLengthExact();		//Days
		int y = d/365;
		String s = "";
		d -= y*365;
		s = Basic.getPlural(y, "year");
		if (s != ""){
			s += " and ";
		}
		return s + Basic.getPlural(d, "day");
	}

	public void endReign(){
		this.end = (Calendar) Basic.date.clone();
	}

//Nickname methods

	public void handleNickname(){
		Human h = this.getPerson();
		int a = h.getAged();
		//There will be 33.3% a nick name will be used
		if (Basic.drawStraws(2)){
			if (a < 12){
				h.getName().setNick(Nick.CHILD);
			} else if (a < 24){
				h.getName().setNick(Nick.YOUNG);
			} else if (a > 60){
				h.getName().setNick(Nick.OLD);
			} else if (this.getReignLengthYears() >= 20){
				h.getName().setNick(Nick.getRandom());
			}
		}
	}
//Regent methods

	public void appointRegent(){
		Ruler regent = Regent.make();
		this.getOffice().setRuler(regent);
		this.regents.add(regent);
	}

	public Ruler getRegent(){ return this.regents.get(this.regents.size()-1); }

	public String getRegentsHTML(){
		String e = "";
		if (this.regents.size() != 0){
			e += "Regents:<ul>";
			for (Ruler r: this.regents){
				e += "<li>"+r.getPerson().getFullName()+"</li>";
			}
			e += "</ul><br>";
		}
		return e;
	}

	public String getBiography(){
		String s = "";
		s += this.getEarlyLife()+HTML.getBr();
		s += this.getBiographyReign()+HTML.getBr();
		s += this.getBiographyMarriage()+HTML.getBr();
		return s;
	}

	public String getBiographyMarriage(){
		String s = this.getPerson().getMaritalBio();
		return s;
	}

	public String getEarlyLife(){
		Human h = this.getPerson();
		String s = Basic.capitalize(h.getForename())+" was";
		if (h.hadFather()){
			s += " born in ";
			s += Basic.sDateLong(h.getBirth())+" as the ";
			s += h.getAgnaticOrderStr()+" of ";
			s += this.getParentNameAge(h.getFather());
			s += " and ";
			if (!h.hasSameBirthOrder()){
				s += "the "+h.getEnaticOrderStr()+" of ";
			}
			s += this.getParentNameAge(h.getMother());
			if (h.isPosthumous()){
				int d = Basic.getDaysBetween(h.getFather().getDeathDate(), h.getBirth());
				String ds = Basic.getMonthsOrDays(d);
				s += ", "+ds+" from his father's death";
			}
		} else {
			s += " born around ";
			s += h.getBirthYear();
		}
		return s+".";
	}

	public String getParentNameAge(Human p){
		Human h = this.getPerson();
		String s = Basic.getCardinal(p.getAgeIn(h.getBirth()));
		s += "-year-old ";
		s += p.getName().getPatronymic();
		return s;
	}


	public String getBiographyReign(){
		String s = "In "+this.getStartYearStr();
		s += " "+this.getPerson().getPronoun();
		s += " ascended the throne "+this.getAgeOfAscensionS();
		if (this.hasEnded()){
			s += " and reigned the next ";
		} else {
			s += " and has ruled ";
		}
		s += this.getReignYearsAndDays();
		return s+". ";
	}

	public String getAgeOfAscensionS(){
		int a = this.getPerson().getDaysIn(this.getStart());
		if (a > 365){
			return "at the age of "+Basic.getCardinal(a/365);
		} else if (a > 31) {
			return "while only "+Basic.getCardinal(a/31)+"-months-old";
		} else {
			return "while merely "+Basic.getCardinal(a)+"-days-old";
		}
	}

	public boolean hasEnded(){
		return this.end != null;
	}

	public String getStartYearStr(){
		return Integer.toString(this.getStart().get(Calendar.YEAR));
	}

//Simple Methods

	public ArrayList<Event> getEvents(){ 	return this.events; }
	public boolean hasConsort(){ 			return this.consort != null; }
	public Claim getClaim(){				return this.claim; }
	public Holder getHolder(){ 				return this; }
	public Human getConsort(){ 				return this.consort; }
	public Human getPerson(){ 				return this.person; }
	public Office getOffice(){				return this.office; }
	public Ruler getRuler(){ 				return this.ruler; }
	public String getName(){ 				return this.name; }
	public String getNotes(){ 				return this.notes; }
	public void addNotes(String n){ 		this.notes += n+"<br>"; }
	public void resetConsort(){ 			this.consort = null; }
	public void setRuler(Ruler r){ 			this.ruler = r;}
	public Calendar getStart(){				return (Calendar) this.start.clone();	}
	public Calendar getEnd(){				return (Calendar) this.end.clone();	}


}
