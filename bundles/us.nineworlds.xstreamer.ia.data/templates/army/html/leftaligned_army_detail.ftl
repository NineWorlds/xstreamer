  <div id="army">
    <hr/>
<#list iaspec.army.deployments as deployments>
  <#if fun.findDeployment(deployments.deployment)??>
  <div class="deployment">
     <div class="name">
        <span class="${fun.findDeployment(deployments.deployment).faction.toString()}"></span><span class="deploymentName">${fun.findDeployment(deployments.deployment).name}</span>
        <span>(${fun.findDeployment(deployments.deployment).deploymentCost})</span>
     </div>
     <#if fun.findDeployment(deployments.deployment).attack?? || fun.findDeployment(deployments.deployment).defense??>
     <div id="attack_defense">
        <#if fun.findDeployment(deployments.deployment).attack.attackType.toString() == "range">
           <div class="left-text range"></div>
        <#elseif fun.findDeployment(deployments.deployment).attack.attackType == "melee">
           <div class="left-text melee"></div>
        </#if>
        <#if fun.findDeployment(deployments.deployment).attack.getDicePool()?? && fun.findDeployment(deployments.deployment).deploymentType.toString() == "deployment">
		    <#list fun.findDeployment(deployments.deployment).attack.getDicePool() as attackDice>
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
	    <#if fun.findDeployment(deployments.deployment).getDefenseDicePool()?? && fun.findDeployment(deployments.deployment).deploymentType.toString() == "deployment">
        <div class="left-text defense">&nbsp;&nbsp;E</div>
	    	<#list fun.findDeployment(deployments.deployment).getDefenseDicePool() as defense>
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
     <#if fun.findDeployment(deployments.deployment).deploymentType.toString() == "deployment">
     <div class="vitals">${deployments.deployment.health}<span class="red-font">&#10084;</span> ${deployments.deployment.getSpeed()}S ${deployments.deployment.getUnitsInGroup()}U</div>
     </#if>
  </div>
  </#if>
</#list>
  <hr />
