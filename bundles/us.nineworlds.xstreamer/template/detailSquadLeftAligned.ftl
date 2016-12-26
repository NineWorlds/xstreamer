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
<html>
<head>
  <style>
    body { margin: 5px;
           color: #F8F8FF;
           text-shadow: 4px 1px #000000;
           text-align: left;
         }

    .squad { padding:5px;
             background-image: linear-gradient(rgba(0, 0, 0, 0.5),rgba(0, 0, 0, 0.5)); 
         }
    .pilot { font-size: 14pt; margin-botton: 2px;}
    .shields { font-size: 14pt; color: cyan; }
    .hull { font-size: 14pt; color: yellow; }
  </style>
</head>
<body>
  <div class="squad">
<#list xwsspec.pilots as pilot>
  <div class="pilot">
	<#if pilot.pilotSkill??>
	  <span>(${pilotAliveDead(pilot)})</span>
	</#if> 
	<span> ${pilot.name} </span>
	<#if pilot.pilotId??>
	  <span>#${pilot.pilotId}</span>
	</#if>
	  <span> - ${pilot.points}</span>
  </div>
	<#if pilot.upgrades.additionalProperties??>
	 <div class="pilot">
	  <#list pilot.upgrades.additionalProperties as key, value>
	      <#list value as upgradeType>
	        <#if upgradeType?has_next>
<span>${findUpgrade(upgradeType)}, </span>
	        <#else>
<span>${findUpgrade(upgradeType)}</span><br/>
	        </#if>
	      </#list>
	  </#list>
	 </div> 
	</#if>
	<div>
	  <span><b class="shields">${(pilot.shields)!"0"}</b> <image style="vertical-align: middle;" height="30" width="30" src="images/Token_Shield.png"/>&nbsp;&nbsp;</span>
      <span><b class="hull">${(pilot.hull)!"0"}</b><image style="vertical-align: middle;" height="30" width="30"src="images/Token_Hull.png"/></span>
	</div>    	
</#list>
  </div>
</body>

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
