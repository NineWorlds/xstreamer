<#-- 
This will generate a text file that looks similar to the following for each ship in the squad

(4) Gamma Squadron Pilot #4 - 26
Extra Munitions
Assault Missiles, XX-23 S-Thread Tracers
Guidance Chips
HHHHHH*

If pilot skill values are entered, when a pilot is destroyed the skill will be replaced with an X

This makes use of several custom functions.

findUpgrade - looks up the name of a upgrade from the xws name provided by the squad builders.
remainingShields - outputs the remaining shields from a pilot
remainingHulls - ouputs the remainging hull for a pilot

-->
<#import "common/common_utils.ftl" as fun>
<#list xwsspec.pilots as pilot>
	<#if pilot.pilotSkill??>
	  <#lt>${fun.pilotAliveDead(pilot)}<#rt>
	</#if>
	<#lt>${pilot.name} <#t>
	<#if pilot.pilotId??>
	  <#lt>#${pilot.pilotId}<#t>
	</#if>
	 <#lt> - ${pilot.points}
	<#if pilot.upgrades??>
	  <#if pilot.upgrades.additionalProperties??>
	    <#list pilot.upgrades.additionalProperties as key, value>
	        <#list value as upgradeType>
	          <#if upgradeType?has_next>
${fun.findUpgrade(upgradeType)}, <#rt>
	          <#else>
${fun.findUpgrade(upgradeType)}
	          </#if>
	        </#list>
	    </#list>
	  </#if>
	</#if>
	<#if pilot.hull gt 0>
	   <#lt>${fun.remainingHull(pilot.hull)}<#rt>
    </#if>
	<#if pilot.shields gt 0>
	   <#lt>${fun.remainingShields(pilot.shields)}
    </#if>

</#list>
