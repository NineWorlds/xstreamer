  <div class="commandCardsContainer">
  <hr />
<#list iaspec.commandCards as command>
	  <div class="commandCards">
	     <div class="cardName">
  <#if command.commandCard.vendorOptions["xstreamerOptions"].isDiscarded()>
     <span class="commandCardDiscarded">&nbsp;</span>
  </#if>     
	     <span>${command.commandCard.name}</span>
	     <span> (${command.commandCard.cost})</span>
	     </div>
	  </div>  
</#list>
  <hr />
  </div>
