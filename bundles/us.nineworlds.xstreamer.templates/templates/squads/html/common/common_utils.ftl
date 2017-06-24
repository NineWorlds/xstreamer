<#function findUpgrade upgradeName> 
  <#list allupgrades as upgrade>
     <#if upgrade.xws == upgradeName>
         <#return upgrade.name>
     </#if>
  </#list>
  <#return ''>
</#function>

<#function findShipAgility shipName>
  <#list allships as ship>
     <#if ship.xws = shipName>
        <#return ship.agility>
     </#if>
  </#list>
  <#return '0'>
</#function>

<#function findShipAttack shipName>
  <#list allships as ship>
     <#if ship.xws = shipName>
        <#return ship.attack>
     </#if>
  </#list>
  <#return '0'>
</#function>


<#function upgradeIconMarkup upgradeType>
  <#assign markup>
    <span class="upgrade-type-symbol">
     <#switch upgradeType>
  		<#case "amd">A<#break>
  		<#case "bomb">B<#break>
  		<#case "cannon">C<#break>
  		<#case "crew">V<#break>
     	<#case "ept">E<#break>
     	<#case "hardpoint">H<#break>
     	<#case "illicit">I<#break>
  		<#case "missile">M<#break>
  		<#case "mod">m<#break>
  		<#case "samd">U<#break>
  		<#case "system">S<#break>
  		<#case "team">T<#break>
  		<#case "tech">X<#break>
  		<#case "title">t<#break>
  		<#case "torpedo">P<#break>
  		<#case "turret">T<#break>
     </#switch>
     </span>
  </#assign>
  <#return markup>
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
