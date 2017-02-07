<#function findUpgrade upgradeName> 
  <#list allupgrades as upgrade>
     <#if upgrade.xws == upgradeName>
         <#return upgrade.name>
     </#if>
  </#list>
  <#return ''>
</#function>

<#function pilotAliveDead pilot>
  <#assign skill>
	  <#if pilot.shields == 0 && pilot.hull == 0>
	     <#lt>(X) <#rt>
	  <#else>
	     <#if pilot.pilotSkill?length != 0>
	        <#lt>(${pilot.pilotSkill}) <#rt>
	     </#if>
	  </#if>
  </#assign>
  <#return skill>
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
