package us.nineworlds.xstreamer.templates.css.asserts;

import org.assertj.core.api.AbstractAssert;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleSheet;

public class CSSStyleSheetAssert extends AbstractAssert<CSSStyleSheetAssert, CSSStyleSheet>{

	protected CSSStyleSheetAssert(CSSStyleSheet actual) {
		super(actual, CSSStyleSheetAssert.class);
	}
	
	public static CSSStyleSheetAssert assertThat(CSSStyleSheet actual) {
		return new CSSStyleSheetAssert(actual);
	}
	
	public CSSStyleSheetAssert hasSize(int size) {
		isNotNull();
		
		CSSRuleList rules = actual.getCssRules();
		
		if (rules == null) {
			failWithMessage("Expected to rules not to be empty or null");
		}
		
		if (rules.getLength() != size) {
			failWithMessage("Expected number of rules <%s> but was <%s>", size, rules.getLength());
		}
		
		return this;
	}

}
