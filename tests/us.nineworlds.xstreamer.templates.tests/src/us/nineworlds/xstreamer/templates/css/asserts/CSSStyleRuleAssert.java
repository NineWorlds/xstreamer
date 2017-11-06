package us.nineworlds.xstreamer.templates.css.asserts;

import org.assertj.core.api.AbstractAssert;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSValue;

public class CSSStyleRuleAssert extends AbstractAssert<CSSStyleRuleAssert, CSSStyleRule> {

	protected CSSStyleRuleAssert(CSSStyleRule actual) {
		super(actual, CSSStyleRuleAssert.class);
	}

	public static CSSStyleRuleAssert assertThat(CSSStyleRule actual) {
		return new CSSStyleRuleAssert(actual);
	}

	public CSSStyleRuleAssert hasProperty(String property) {

		CSSValue propertyCSSValue = actual.getStyle().getPropertyCSSValue(property);

		if (propertyCSSValue == null) {
			failWithMessage("Expected to have %s but was not found.", property);
		}

		return this;
	}

	public CSSStyleRuleAssert hasPropertyWithValue(String property, String value) {
		isNotNull();
		
		hasProperty(property);

		CSSValue propertyCSSValue = actual.getStyle().getPropertyCSSValue(property);

		if (!propertyCSSValue.getCssText().equals(value)) {
			failWithMessage("Expected property %s to have property value <%s> but was <%s>", property, value,
					propertyCSSValue.getCssText());
		}
		return this;
	}

}
