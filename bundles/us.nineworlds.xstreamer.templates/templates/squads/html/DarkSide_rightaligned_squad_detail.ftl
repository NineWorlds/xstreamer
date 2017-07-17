  <div class="squad">
<#list xwsspec.pilots as pilot>
  <div class="pilot">
	<#if pilot.pilotSkill??>
	  <span>${fun.pilotAliveDead(pilot)}</span>
	</#if> 
	<span> ${pilot.name} </span>
	<span> (${pilot.points}) </span>

  </div>
	<#if pilot.upgrades??>
	  <#if pilot.upgrades.additionalProperties??>
	    <div class="upgrades">
	    <#list pilot.upgrades.additionalProperties as key, value>
	      <#list value as upgradeType>
	        <#if upgradeType?has_next>
<span>${fun.findUpgrade(upgradeType)}, </span>
	        <#else>
<span>${fun.findUpgrade(upgradeType)}</span> ${fun.upgradeIconMarkup(key)}<br/>
	        </#if>
	      </#list>
	    </#list>
	   </div> 
	  </#if>
	</#if>
	<div>
  	  <span><b class="agility">${fun.findShipAgility(pilot.ship)}</b> <span class="agility-symbol">^</span> </span>
  	  <span><b class="attack">${fun.findShipAttack(pilot.ship)}</b> <span class="attack-symbol">%</span> </span>	
	  <span><b class="shields">${(pilot.shields)!"0"}</b> <span class="shields-symbol">*</span>&nbsp;&nbsp;</span>
      <span><b class="hull">${(pilot.hull)!"0"}</b>span class="hull-symbol">&</span></span>
      <#if pilot.pilotId!?length gt 0>
	    <span class="idtag"><b>${pilot.pilotId}</b></span>
	  </#if>
	</div>    	
</#list>
  </div>
<br/>

  <footer>
    <div style="padding: 10px;">
       <span>${xwsspec.points} Points</span>
    </div> 
  </footer>
</div>
