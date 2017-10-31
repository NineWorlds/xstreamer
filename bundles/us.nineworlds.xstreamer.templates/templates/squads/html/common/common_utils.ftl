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

<#function upgradeDiscardedMarkup upgrade>
   <#if upgrade.isDiscarded()>
     <#assign markup>
        <span class="upgradeDiscarded"></span>
     </#assign>
     <#return markup>
   </#if>
  <#return "">
</#function>

<#function upgradeIconMarkup upgradeType>
  <#assign markup>
    <i class="xwing-miniatures-font 
     <#switch upgradeType>
  		<#case "amd"> xwing-miniatures-font-astromech<#break>
  		<#case "bomb"> xwing-miniatures-font-bomb<#break>
  		<#case "cannon"> xwing-miniatures-font-cannon<#break>
  		<#case "cargo"> xwing-miniatures-font-cargo<#break>
  		<#case "crew"> xwing-miniatures-font-crew<#break>
     	<#case "ept"> xwing-miniatures-font-elite<#break>
     	<#case "hardpoint"> xwing-miniatures-font-hardpoint<#break>
     	<#case "illicit"> xwing-miniatures-font-illicit<#break>
  		<#case "missile"> xwing-miniatures-font-missile<#break>
  		<#case "mod"> xwing-miniatures-font-modification<#break>
  		<#case "samd"> xwing-miniatures-font-salvagedastromech<#break>
  		<#case "system"> xwing-miniatures-font-system<#break>
  		<#case "team"> xwing-miniatures-font-team<#break>
  		<#case "tech"> xwing-miniatures-font-tech<#break>
  		<#case "title"> xwing-miniatures-font-title<#break>
  		<#case "torpedo"> xwing-miniatures-font-torpedo<#break>
  		<#case "turret"> xwing-miniatures-font-turret<#break>
     </#switch>">
     </i>
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
