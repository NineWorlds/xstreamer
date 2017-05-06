<#-- 
This will generate a text file that looks similar to the following for each ship in the squad

(4) Gamma Squadron Pilot #4 - 26
Extra Munitions
Assault Missiles, XX-23 S-Thread Tracers
Guidance Chips
HHHHHH*

If pilot skill values are entered, when a pilot is destroyed the skill will be replaced with an X

This makes use of several custom functions.

findUpgrade - looks up the name of a upgrade from the xws name provided by the squad builders.
pilotAliveDead - determines if the pilot is alive or dead

-->
<#import "common/common_utils.ftl" as fun/>
<html>
<head>
	<#include "darkside/css_leftaligned_squads.ftl"/>
    <#include "common/js_refresh.ftl"/>
 </head>
<body>
  <#include "DarkSide_leftaligned_squad_detail.ftl"/>
</body>
