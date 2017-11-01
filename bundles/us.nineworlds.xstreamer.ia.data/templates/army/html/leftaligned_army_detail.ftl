  <div id="army">
    <hr/>
<#list iaspec.army.deployments as deployments>
  <#assign dbDeployment>${fun.deploymentLookup(deployments.deployment)}</#assign>
  <#if dbDeployment??>
  <div class="deployment">
     <div class="name">
        <span class="${dbDeployment.faction.toString()}"></span><span class="deploymentName">${dbDeployment.name}</span>
        <span>(${dbDeployment.deploymentCost})</span>
     </div>
     <#if dbDeployment.attack?? || dbDeployment.defense??>
     <div id="attack_defense">
        <#if dbDeployment.attack.attackType.toString() == "range">
           <div class="left-text range"></div>
        <#elseif dbDeployment.attack.attackType == "melee">
           <div class="left-text melee"></div>
        </#if>
        <#if dbDeployment.attack.getDicePool()?? && dbDeployment.deploymentType.toString() == "deployment">
		    <#list dbDeployment.attack.getDicePool() as attackDice>
	        	<#switch attackDice.toString()>
	        	   <#case "red"><div class="box red">&nbsp;</div><#break>
	        	   <#case "blue"><div class="box blue">&nbsp;</div><#break>
	        	   <#case "green"><div class="box green">&nbsp;</div><#break>
	        	   <#case "yellow"><div class="box yellow">&nbsp;</div><#break>
	        	   <#case "any"><div class="box any">&nbsp;</div><#break>
				   <#default><#break>
	        	</#switch>
	        <div class="spacer">&nbsp;</div>
	        </#list>
	    </#if>
	    <#if dbDeployment.getDefenseDicePool()?? && dbDeployment.deploymentType.toString() == "deployment">
        <div class="left-text defense">&nbsp;&nbsp;E</div>
	    	<#list dbDeployment.getDefenseDicePool() as defense>
	    		<#switch defense.toString()>
	    			<#case "white"><div class="box white">&nbsp;</div><#break>
					<#case "black"><div class="box black">&nbsp;</div><#break>
					<#default><#break>
	    		</#switch>
	    	</#list>
        <div class="spacer">&nbsp;</div>
	    </#if>
        <br />
     </div>
     </#if>
     <#if dbDeployment.deploymentType.toString() == "deployment">
     <div class="vitals">${dbDeployment.getDeploymentCost()}&#10084; ${dbDeployment.getSpeed()}S ${dbDeployment.getUnitsInGroup()}U</div>
     </#if>
  </div>
  </#if>
</#list>
