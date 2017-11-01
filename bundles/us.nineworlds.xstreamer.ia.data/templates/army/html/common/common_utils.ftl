<#function findDeployment deployment>
  <#list allDeployments as rdeployment>
     <#if rdeployment.iaspecName = deployment.iaSpecName && rdeployment.faction == deployment.faction>
        <#return rdeployment>
     </#if>
  </#list>
  <#return null>
</#function>