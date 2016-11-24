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
<#list xwsspec.pilots as pilot>
	<#if pilot.pilotSkill??>
	  <#t>(${pilotAliveDead()})<#rt>
	</#if> 
	<#lt> ${pilot.name} <#t>
	<#if pilot.pilotId??>
	  <#lt>#${pilot.pilotId}<#t>
	</#if>
	 <#lt> - ${pilot.points}
	<#if pilot.upgrades.additionalProperties??>
	  <#list pilot.upgrades.additionalProperties as key, value>
	      <#list value as upgradeType>
	        <#if upgradeType?has_next>
${findUpgrade(upgradeType)}, <#rt>
	        <#else>
${findUpgrade(upgradeType)}
	        </#if>
	      </#list>
	  </#list>
	</#if>
	<#if pilot.hull gt 0>
	   <#lt>${remainingHull(pilot.hull)}
    </#if>
	<#if pilot.shields gt 0>
	   <#lt>${remainingShields(pilot.shields)}<#t>    
    </#if>
    	
</#list>

<#function findUpgrade upgradeName> 
  <#list allupgrades as upgrade>
     <#if upgrade.xws == upgradeName>
         <#return upgrade.name>
     </#if>
  </#list>
  <#return ''>
</#function>

<#function remainingHull hullValue>
	<#assign hull>
	  <#list 1..hullValue as x>
	    <#lt>H<#rt>
	  </#list>
	</#assign>
	<#return hull>
</#function>

<#function remainingShields shieldsValue>
	<#assign shield>
	  <#list 1..shieldsValue as x>
	    <#lt>*<#rt>
	  </#list>
	</#assign>
	<#return shield>
</#function>

<#function pilotAliveDead pilot>
  <#assign skill>
	  <#if pilot.shields == 0 && pilot.hull == 0>
	     <#lt>X<#rt>
	  <#else>
	     <#lt>${pilot.pilotSkill}<#rt>
	  </#if>
  </#assign>
  <#return skill>
</#function>
