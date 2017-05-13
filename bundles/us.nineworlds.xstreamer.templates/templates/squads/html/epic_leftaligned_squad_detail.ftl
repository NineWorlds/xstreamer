<div class="squad">
<#list xwsspec.pilots as pilot>
  <div class="blocks">
	  <div class="pilot">
		<#if pilot.pilotSkill??>
		  <span>${fun.pilotAliveDead(pilot)}</span>
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
	<span>${fun.findUpgrade(upgradeType)}, </span>
		        	<#else>
	<span>${fun.findUpgrade(upgradeType)}</span><br/>
		        	</#if>
		    	  </#list>
			  </#list>
			 </div> 
	 	  </#if>
		</#if>
  </div>
</#list>
</div>
