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
pilotAliveDead - determines whether the pilot is still alive or dead.
-->
<html>
<head>
  <style>
    body { margin: 5px;
           color: #FFFFFF;
           text-shadow: 4px 1px #000000;
           text-align: right;
           font-size: 100%;
           font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
		   font-weight: bold;
		   font-style: normal;
         }

    .squad { padding:10px;
             background-image: linear-gradient(rgba(0, 0, 0, 0.5),rgba(0, 0, 0, 0.5)); 
         }
    .pilot { font-size: 1.1em; margin-top: 5px; font-weight: bold;}
    .upgrades { font-size: 0.9em; margin-bottom: 5px; font-weight: bold;}
    .shields { font-size: 0.95em; color: cyan; }
    .hull { font-size: 0.95em; color: yellow; }
    .idtag { background-image: url(images/Token-IDTag_Light.png);
             background-size: 30px 30px;
             background-repeat: no-repeat;
             height: 30px;
             width: 30px;
             display: inline-block;
             text-align: center;
             text-shadow: 0px 0px #000000;
             color: black;
             padding-top: 5px;
             vertical-align: middle;
             font-size: 0.95em
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
  </div>
  	<div>
	  <span><b class="shields">${(pilot.shields)!"0"}</b> <image style="vertical-align: middle;" height="30" width="30" src="images/Token_Shield.png"/>&nbsp;&nbsp;</span>
      <span><b class="hull">${(pilot.hull)!"0"}</b><image style="vertical-align: middle;" height="30" width="30"src="images/Token_Hull.png"/></span>
      <#if pilot.pilotId!?length gt 0>
	    <span class="idtag"><b>${pilot.pilotId}</b></span>
	  </#if>
	  <span> pts: ${pilot.points}</span>
	</div>    	
	<#if pilot.upgrades??>
	  <#if pilot.upgrades.additionalProperties??>
	    <div class="upgrades">
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
	</#if>
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
