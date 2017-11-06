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

<#function shipIconMarkup shipName>
  <#assign markup>
    <i class="xwing-miniatures-ship  
     <#switch shipName>
  		<#case "gr75mediumtransport"> xwing-miniatures-ship-gr75mediumtransport<#break>
  		<#case "cr90corvette"> xwing-miniatures-ship-cr90corvette<#break>
  		<#case "raiderclasscorvette"> xwing-miniatures-ship-raiderclasscorvette<#break>
  		<#case "gozanticlasscruiser"> xwing-miniatures-ship-gozanticlasscruiser<#break>	
  		<#case "tieadvanced"> xwing-miniatures-ship-tieadvanced<#break>	
  		<#case "tiebomber"> xwing-miniatures-ship-tiebomber<#break>
  		<#case "tiedefender"> xwing-miniatures-ship-tiedefender<#break>
  		<#case "tiefighter"> xwing-miniatures-ship-tiefighter<#break>
  		<#case "vcx100"> xwing-miniatures-ship-vcx100<#break>
  		<#case "tieinterceptor"> xwing-miniatures-ship-tieinterceptor<#break>
  		<#case "lancerclasspursuitcraft"> xwing-miniatures-ship-lancerclasspursuitcraft<#break>
  		<#case "protectoratestarfighter"> xwing-miniatures-ship-protectoratestarfighter<#break>
  		<#case "tiepunisher"> xwing-miniatures-ship-tiepunisher<#break>
  		<#case "tiefofighter"> xwing-miniatures-ship-tiefofighter<#break>
  		<#case "tiephantom"> xwing-miniatures-ship-tiephantom<#break>
  		<#case "tieadvancedprototype"> xwing-miniatures-ship-tieadvancedprototype<#break>
  		<#case "tiesffighter"> xwing-miniatures-ship-tiesffighter<#break>
  		<#case "awing"> xwing-miniatures-ship-awing<#break>
  		<#case "bwing"> xwing-miniatures-ship-bwing<#break>
  		<#case "arc170"> xwing-miniatures-ship-arc170<#break>
  		<#case "vt49decimator"> xwing-miniatures-ship-vt49decimator<#break>
  		<#case "ewing"> xwing-miniatures-ship-ewing<#break>
  		<#case "firespray31"> xwing-miniatures-ship-firespray31<#break>
  		<#case "attackshuttle"> xwing-miniatures-ship-attackshuttle<#break>
  		<#case "hwk290"> xwing-miniatures-ship-hwk290<#break>
  		<#case "ig2000"> xwing-miniatures-ship-ig2000<#break>
  		<#case "aggressor"> xwing-miniatures-ship-aggressor<#break>
  		<#case "kwing"> xwing-miniatures-ship-kwing<#break>
  		<#case "lambdaclasshuttle"> xwing-miniatures-ship-lambdaclassshuttle<#break>
  		<#case "yt1300"> xwing-miniatures-ship-yt1300<#break>
  		<#case "g1astarfighter"> xwing-miniatures-ship-g1astarfighter<#break>
  		<#case "yt2400freighter"> xwing-miniatures-ship-yt2400freighter<#break>
  		<#case "jumpmaster5000"> xwing-miniatures-ship-jumpmaster<#break>
  		<#case "kihraxzfighter"> xwing-miniatures-ship-kihraxzfighter<#break>
  		<#case "m3ainterceptor"> xwing-miniatures-ship-m3ainterceptor<#break>
  		<#case "yv666"> xwing-miniatures-ship-yv666<#break>
  		<#case "starviper"> xwing-miniatures-ship-starviper<#break>
  		<#case "t70xwing"> xwing-miniatures-ship-t70xwing<#break>
  		<#case "xwing"> xwing-miniatures-ship-xwing<#break>
  		<#case "ywing"> xwing-miniatures-ship-ywing<#break>
  		<#case "z95headhunter"> xwing-miniatures-ship-z95headhunter<#break>
     </#switch>">
     </i>
  </#assign>
  <#return markup>
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
	     <#lt>&#9760;<#rt>
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
