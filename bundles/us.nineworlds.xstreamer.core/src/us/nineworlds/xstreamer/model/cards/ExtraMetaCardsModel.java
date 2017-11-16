package us.nineworlds.xstreamer.model.cards;

import java.util.HashMap;
import java.util.Map;

public class ExtraMetaCardsModel {
	
	private Map<String, Object> miscellaneousCards = new HashMap();

	public Map<String, Object> getMiscellaneousCards() {
		return miscellaneousCards;
	}

	public void setMiscellaneousCards(Map<String, Object> miscellaneousCards) {
		this.miscellaneousCards = miscellaneousCards;
	}

}
