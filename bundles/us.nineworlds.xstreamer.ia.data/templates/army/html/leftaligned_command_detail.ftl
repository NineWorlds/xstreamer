  <div class="commandCardsContainer">
  <hr />
<#list iaspec.commandCards as command>
	  <div class="commandCards">
	     <div class="cardName">${command.commandCard.name} (${command.commandCard.cost})</div>
	  </div>  
</#list>
  <hr />
  </div>
