package us.nineworlds.xstreamer.model.dice;

public class DiceResults {
	
	private int attackHits;
	private int attackCrits;
	private int attackFocus;
	private int attackMisses;
	public int getAttackHits() {
		return attackHits;
	}
	public void setAttackHits(int attackHits) {
		this.attackHits = attackHits;
	}
	public int getAttackCrits() {
		return attackCrits;
	}
	public void setAttackCrits(int attackCrits) {
		this.attackCrits = attackCrits;
	}
	public int getAttackFocus() {
		return attackFocus;
	}
	public void setAttackFocus(int attackFocus) {
		this.attackFocus = attackFocus;
	}
	public int getAttackMisses() {
		return attackMisses;
	}
	public void setAttackMisses(int attackMisses) {
		this.attackMisses = attackMisses;
	}
	public int getDefenseEvade() {
		return defenseEvade;
	}
	public void setDefenseEvade(int defenseEvade) {
		this.defenseEvade = defenseEvade;
	}
	public int getDefenseFocus() {
		return defenseFocus;
	}
	public void setDefenseFocus(int defenseFocus) {
		this.defenseFocus = defenseFocus;
	}
	public int getDefenseMisses() {
		return defenseMisses;
	}
	public void setDefenseMisses(int defenseMisses) {
		this.defenseMisses = defenseMisses;
	}
	private int defenseEvade;
	private int defenseFocus;
	private int defenseMisses;

}
