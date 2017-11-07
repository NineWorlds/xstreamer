package us.nineworlds.xstreamer.templates.html.common.css;

import static us.nineworlds.xstreamer.templates.css.asserts.CSSStyleRuleAssert.assertThat;
import static us.nineworlds.xstreamer.templates.css.asserts.CSSStyleSheetAssert.assertThat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.junit.*;
import org.osgi.framework.Bundle;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.dom.CSSStyleRuleImpl;
import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;

public class XWIngCommonCssTest {
	CSSStyleSheet styleSheet;
	CSSStyleDeclaration parseStyleDeclaration;
	CSSOMParser parser;
	
	@Before
	public void setUp() throws Exception {
		Bundle bundle = Platform.getBundle("us.nineworlds.xstreamer.templates");
		Path path = new Path("templates/squads/html/common/css_squads_common.ftl");
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
	public void hullClassHasExpectedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".hull");
		assertThat(rule).hasPropertyWithValue("font-size", "0.95em")
			.hasPropertyWithValue("color", "yellow");	
	}
	
	@Test
	public void pilotClassHasExpectedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".pilot");
		assertThat(rule).hasPropertyWithValue("font-size", "1.1em")
			.hasPropertyWithValue("margin-top", "5px")
			.hasPropertyWithValue("font-weight", "bold");	
	}
	
	@Test
	public void redClassHasExpectedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".red");
		assertThat(rule).hasPropertyWithValue("color", "red");
	}

	@Test
	public void shieldsSymbolClassHasExpectedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".shields-symbol");
		assertThat(rule).hasPropertyWithValue("font-family", "\"x-wing-symbols\"")
			.hasPropertyWithValue("font-size", "1.75em")
			.hasPropertyWithValue("color", "cyan")
			.hasPropertyWithValue("font-weight", "normal")
			.hasPropertyWithValue("font-style", "normal")
			.hasPropertyWithValue("text-shadow", "0px 0px rgb(0, 0, 0)")
			.hasPropertyWithValue("-webkit-font-smoothing", "always");
	}
	
	@Test
	public void hullSymbolClassHasExpectedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".hull-symbol");
		assertThat(rule).hasPropertyWithValue("font-family", "\"x-wing-symbols\"")
			.hasPropertyWithValue("font-size", "1.7em")
			.hasPropertyWithValue("font-style", "normal")
			.hasPropertyWithValue("color", "yellow")
			.hasPropertyWithValue("font-weight", "normal")
			.hasPropertyWithValue("font-style", "normal")
			.hasPropertyWithValue("text-shadow", "0px 0px rgb(0, 0, 0)")
			.hasPropertyWithValue("-webkit-font-smoothing", "always");
	}
	
	@Test
	public void agilitySymbolClassHasExpectedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".agility-symbol");
		assertThat(rule).hasPropertyWithValue("font-family", "\"x-wing-symbols\"")
			.hasPropertyWithValue("font-size", "1.75em")
			.hasPropertyWithValue("color", "rgb(86, 244, 66)")
			.hasPropertyWithValue("font-weight", "normal")
			.hasPropertyWithValue("font-style", "normal")
			.hasPropertyWithValue("text-shadow", "0px 0px rgb(0, 0, 0)")
			.hasPropertyWithValue("-webkit-font-smoothing", "always");
	}
	
	@Test
	public void attackSymbolClassHasExpectedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".attack-symbol");
		assertThat(rule).hasPropertyWithValue("font-family", "\"x-wing-symbols\"")
			.hasPropertyWithValue("font-size", "1.75em")
			.hasPropertyWithValue("color", "rgb(242, 14, 56)")
			.hasPropertyWithValue("font-weight", "normal")
			.hasPropertyWithValue("font-style", "normal")
			.hasPropertyWithValue("text-shadow", "0px 0px rgb(0, 0, 0)")
			.hasPropertyWithValue("-webkit-font-smoothing", "always");
	}
	
	@Test
	public void upgradeTypeSymbolClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".upgrade-type-symbol");
		assertThat(rule).hasPropertyWithValue("font-family", "\"x-wing-symbols\"")
			.hasPropertyWithValue("font-size", "16pt")
			.hasPropertyWithValue("color", "rgb(255, 255, 255)")
			.hasPropertyWithValue("font-weight", "normal")
			.hasPropertyWithValue("font-style", "normal")
			.hasPropertyWithValue("text-shadow", "0px 0px rgb(0, 0, 0)")
			.hasPropertyWithValue("-webkit-font-smoothing", "always");
	}
	
	@Test
	public void squadClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".squad");
		assertThat(rule).hasPropertyWithValue("padding", "10px")
			.hasPropertyWithValue("height", "100%")
			.hasPropertyWithValue("background-image", "linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5))");
	}
		
	@Test
	public void pilotClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".pilot");
		assertThat(rule).hasPropertyWithValue("font-size", "1.1em")
			.hasPropertyWithValue("margin-top", "5px")
			.hasPropertyWithValue("font-weight", "bold");
	}

	@Test
	public void upgradesClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".upgrades");
		assertThat(rule).hasPropertyWithValue("font-size", "0.9em")
			.hasPropertyWithValue("margin-bottom", "5px")
			.hasPropertyWithValue("font-weight", "bold");
	}

	@Test
	public void shieldsClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".shields");
		assertThat(rule).hasPropertyWithValue("font-size", "0.95em")
			.hasPropertyWithValue("color", "cyan");
	}
	
	@Test
	public void hullClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".hull");
		assertThat(rule).hasPropertyWithValue("font-size", "0.95em")
			.hasPropertyWithValue("color", "yellow");
	}
	
	@Test
	public void agilityClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".agility");
		assertThat(rule).hasPropertyWithValue("font-size", "0.95em")
			.hasPropertyWithValue("color", "rgb(86, 244, 66)");
	}

	@Test
	public void attackClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".attack");
		assertThat(rule).hasPropertyWithValue("font-size", "0.95em")
			.hasPropertyWithValue("color", "rgb(242, 14, 56)");
	}
	
	@Test
	public void idTagClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".idtag");
		assertThat(rule).hasPropertyWithValue("background-image", "url(images/Token-IDTag_Light.png)")
			.hasPropertyWithValue("background-size", "30px 30px")
			.hasPropertyWithValue("background-repeat", "no-repeat")
			.hasPropertyWithValue("height", "30px")
			.hasPropertyWithValue("width", "30px")
			.hasPropertyWithValue("display", "inline-block")
			.hasPropertyWithValue("text-align", "center")
			.hasPropertyWithValue("text-shadow", "0px 0px rgb(0, 0, 0)")
			.hasPropertyWithValue("color", "black")
			.hasPropertyWithValue("padding-top", "5px")
			.hasPropertyWithValue("vertical-align", "middle")
			.hasPropertyWithValue("font-size", "0.95em");
	}

	@Test
	public void upgradeDiscardedClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".upgradeDiscarded");
		assertThat(rule).hasPropertyWithValue("color", "red");
	}	
		
	@Test
	public void upgradeDiscardedBeforeClassHasExpecedValues() throws Exception {
		CSSStyleRule rule = findRule(styleSheet.getCssRules(), ".upgradeDiscarded:before");
		assertThat(rule).hasPropertyWithValue("content", "\"\u2718\"");
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
