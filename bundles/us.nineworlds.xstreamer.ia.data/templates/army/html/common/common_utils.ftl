<#function findDeployment deployment>
  <#list allDeployments as rdeployment>
     <#if rdeployment.deployment.iaspecname = deployment.iaspecname && rdeployment.deployment.faction == deployment.faction>
        <#return rdeployment.deployment>
     </#if>
  </#list>
  <#return null>
</#function>

<#function commandCardDiscarded commandCard>
  <#if commandCard.vendorOptions["xstreamerOptions"].isDiscarded()>
     <span class="commandCardDiscarded">&nbsp;</span>
  </#if>
</#function>