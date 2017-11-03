  <div class="squad">
<#list xwsspec.pilots as pilot>
  <div class="pilot">
    ${fun.shipIconMarkup(pilot.ship)}
	<#if pilot.pilotSkill??>
	  <span>${fun.pilotAliveDead(pilot)}</span>
	</#if> 
	<span> ${pilot.name} </span>
	<#if pilot.hasCriticalDamage()>
	<span> <i class="xwing-miniatures-font xwing-miniatures-font-token-crit red"></i></span>
	</#if>
  </div>
  	<div>
  	  <span><span class="attack-symbol">%</span>&nbsp;<b class="attack">${fun.findShipAttack(pilot.ship)}&nbsp;</b></span>	  
  	  <span><span class="agility-symbol">^</span>&nbsp;<b class="agility">${fun.findShipAgility(pilot.ship)}&nbsp;</b> </span>
      <span><span class="hull-symbol">&</span>&nbsp;<b class="hull">${(pilot.hull)!"0"}&nbsp;</b> </span>
	  <span><span class="shields-symbol">*</span>&nbsp;<b class="shields">${(pilot.shields)!"0"}</b> &nbsp;&nbsp;</span>
      <#if pilot.pilotId!?length gt 0>
	    <span class="idtag"><b>${pilot.pilotId}</b></span>
	  </#if>
	  <span> [${pilot.points}]</span>
	</div>    	

    <#if pilot.upgrades??>
	  <#if pilot.upgrades.additionalProperties??>
	 	<div class="upgrades">
	  	<#list pilot.upgrades.additionalProperties as key, value>
	  	     ${fun.upgradeIconMarkup(key)}
	     	 <#list value as upgradeType>
	     	    ${fun.upgradeDiscardedMarkup(pilot.upgrades.findUpgrade(upgradeType))}
	        	<#if upgradeType?has_next>
<span>${fun.findUpgrade(upgradeType)}, </span>
	        	<#else>
<span>${fun.findUpgrade(upgradeType)}</span><br/>
	        	</#if>
	    	  </#list>
		  </#list>
		 </div> 
 	  </#if>
	</#if>
</#list>
  </div>
