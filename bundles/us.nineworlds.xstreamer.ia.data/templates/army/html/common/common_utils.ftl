<#function findDeployment deployment>
  <#list allDeployments as rdeployment>
     <#if rdeployment.deployment.iaspecname = deployment.iaspecname && rdeployment.deployment.faction == deployment.faction>
        <#return rdeployment.deployment>
     </#if>
  </#list>
  <#return null>
</#function>