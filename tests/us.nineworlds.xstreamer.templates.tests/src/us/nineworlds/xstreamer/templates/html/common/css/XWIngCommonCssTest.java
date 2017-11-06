package us.nineworlds.xstreamer.templates.html.common.css;

import static us.nineworlds.xstreamer.templates.css.asserts.CSSStyleRuleAssert.assertThat;
import static us.nineworlds.xstreamer.templates.css.asserts.CSSStyleSheetAssert.assertThat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.junit.*;
import org.osgi.framework.Bundle;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.dom.CSSStyleRuleImpl;
import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;

import us.nineworlds.xstreamer.templates.css.asserts.CSSStyleRuleAssert;


public class XWIngCommonCssTest {
	CSSStyleSheet styleSheet;
	CSSStyleDeclaration parseStyleDeclaration;
	CSSOMParser parser;
	
	@Before
	public void setUp() throws Exception {
		Bundle bundle = Platform.getBundle("us.nineworlds.xstreamer.templates");
		Path path = new Path("templates/squads/html/common/css_leftaligned_squads.ftl");
		URL url = FileLocator.find(bundle, path, null);
		
		InputSource source = new InputSource(new BufferedReader(new InputStreamReader(url.openStream())));
		
		parser = new CSSOMParser(new SACParserCSS3());
		
		styleSheet = parser.parseStyleSheet(source, null, null);
	}
	
	@Test
	public void simpleCSSTest() throws Exception {
		assertThat(styleSheet).hasSize(16);
	}
	
	@Test
	public void hallClassHasExpectedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".hull");
		assertThat(rule).hasPropertyWithValue("font-size", "0.95em")
		.hasProperty("body");
	}
	
	protected CSSStyleRule findRule(CSSRuleList rules, String ruleName) throws Exception {
		for (int i = 0; i < rules.getLength(); i++) {
			CSSStyleRuleImpl rule = (CSSStyleRuleImpl) rules.item(i);
			
			SelectorList selectorList = rule.getSelectors();
			for (int s = 0; s < selectorList.getLength(); s++ ) {
				Selector selector = selectorList.item(s);
				if (selector.toString().equals(ruleName)) {
					return rule;
				}
			}
		}
		return null;
	}
	
}
