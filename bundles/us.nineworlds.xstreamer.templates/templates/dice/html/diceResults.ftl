<html>
<head>
  <style>
    body { margin: 5px;
           background-image: linear-gradient(rgba(0, 0, 0, 0.5),rgba(0, 0, 0, 0.5)
           color: #F8F8FF;
           text-shadow: 4px 1px #000000;
           text-align: right;
         }

    .attack { padding:5px; }
    .defense { padding:5px; }
  </style>
  <link rel="stylesheet" href="animate.css">
</head>
<body>
<#if diceresults.attackCrits gt 0>
   <@generateCrits numberOfCrits=diceresults.attackCrits/>
</#if>
<#if diceresults.attackHits gt 0>
   <@generateHits numberOfHits=diceresults.attackHits/>
</#if>
<#if diceresults.attackFocus gt 0>
   <@generateAttackFocus numberOfFocus=diceresults.attackFocus/>
</#if>
<#if diceresults.attackMisses gt 0>
   <@generateAttackMisses numberOfMisses=diceresults.attackMisses/>
</#if>
<br/>
<#if diceresults.defenseEvade gt 0>
   <@generateEvades numberOfEvades=diceresults.defenseEvade />
</#if>
<#if diceresults.defenseFocus gt 0>
   <@generateDefFocus numberOfFocus=diceresults.defenseFocus/>
</#if>
<#if diceresults.defenseMisses gt 0>
   <@generateDefMisses numberOfMisses=diceresults.defenseMisses/>
</#if>

</body>
</html>

<#macro generateHits numberOfHits>
  <#list 1..numberOfHits as hit>
    <span class="attack bounceInDown">
     <image style="vertical-align: middle;" height="76" width="81" src="images/dice/attack-hit.png"/>&nbsp;
    </span>
  </#list>
</#macro>

<#macro generateCrits numberOfCrits>
  <#list 1..numberOfCrits as crits>
    <span class="attack bounceInDown">
     <image style="vertical-align: middle;" height="76" width="81" src="images/dice/attack-crit.png"/>&nbsp;
    </span>
  </#list>
</#macro>

<#macro generateAttackFocus numberOfFocus>
  <#list 1..numberOfFocus as focus>
    <span class="attack bounceInDown">
     <image style="vertical-align: middle;" height="76" width="81" src="images/dice/attack-focus.png"/>&nbsp;
    </span>
  </#list>
</#macro>

<#macro generateAttackMisses numberOfMisses>
  <#list 1..numberOfMisses as blank>
    <span class="attack">
     <image class="bounceInUp" style="vertical-align: middle;" height="76" width="81" src="images/dice/attack-blank.png"/>&nbsp;
    </span>
  </#list>
</#macro>

<#macro generateEvades numberOfEvades>
  <#list 1..numberOfEvades as evade>
    <span class="defense bounceInUp">
     <image style="vertical-align: middle;" height="76" width="81" src="images/dice/defense-evade.png"/>&nbsp;
    </span>
  </#list>
</#macro>

<#macro generateDefFocus numberOfFocus>
  <#list 1..numberOfFocus as focus>
    <span class="defense bounceInUp">
     <image style="vertical-align: middle;" height="76" width="81" src="images/dice/defense-focus.png"/>&nbsp;
    </span>
  </#list>
</#macro>

<#macro generateDefMisses numberOfMisses>
  <#list 1..numberOfMisses as blank>
    <span class="defense bounceInUp">
     <image style="vertical-align: middle;" height="76" width="81" src="images/dice/defense-blank.png"/>&nbsp;
    </span>
  </#list>
</#macro>