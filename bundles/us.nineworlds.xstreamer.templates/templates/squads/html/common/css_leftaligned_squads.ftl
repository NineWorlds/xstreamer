  <style>
    body { margin: 5px;
           color: #FFFFFF;
           text-shadow: 4px 1px #000000;
           text-align: left;
           font-size: 100%;
           font-weight: bold;
           font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
           font-style: normal;
         }
         
    .shields-symbol { font-family: "x-wing-symbols";
               font-size: 1.7em;
               color: cyan;
               font-weight: normal;
               text-shadow: 0px 0px #000000;
               -webkit-font-smoothing: always;
             }
             
    .hull-symbol { font-family: "x-wing-symbols";
               font-size: 1.7em;
               color: yellow;
               text-shadow: 0px 0px #000000;
               -webkit-font-smoothing: always;
             }
    .agility-symbol {
       font-family: "x-wing-symbols";
       font-size: 1.75em;
       color: #56f442;
       text-shadow: 0px 0px #000000;
       -webkit-font-smoothing: always;
    }

    .attack-symbol {
       font-family: "x-wing-symbols";
       font-size: 1.75em;
       color: #f20e38;
       text-shadow: 0px 0px #000000;
       -webkit-font-smoothing: always;
    }
             
             
    .upgrade-type-symbol {
       font-family: "x-wing-symbols";
       font-size: 16pt;
       color: #FFFFFF;
       text-shadow: 0px 0px #000000;
       -webkit-font-smoothing: always;
    }

    .squad { padding:10px;
             height: 100%; 
             background-image: linear-gradient(rgba(0, 0, 0, 0.5),rgba(0, 0, 0, 0.5)); 
         }
    .pilot { font-size: 1.1em; margin-top: 5px; font-weight: bold;}
    .upgrades { font-size: 0.9em; margin-bottom: 5px; font-weight:bold;}
    .shields { font-size: 0.95em; color: cyan; }
    .hull { font-size: 0.95em; color: yellow; }
    .agility { font-size: 0.95em; color: #56f442; }
    .attack { font-size: 0.95em; color: #f20e38; }    
    .idtag { background-image: url(images/Token-IDTag_Light.png);
             background-size: 30px 30px;
             background-repeat: no-repeat;
             height: 30px;
             width: 30px;
             display: inline-block;
             text-align: center;
             text-shadow: 0px 0px #000000;
             color: black;
             padding-top: 5px;
             vertical-align: middle;
             font-size: 0.95em
     }

	i.xwing-miniatures-font {
      text-shadow: 0px 0px #000000;	
	  font-family: "x-wing-symbols";
	  font-style: normal;
	  font-size: 1.75em; }
	  
	i.xwing-miniatures-font-rebel:before {
		content: "\0021";
	}
	
	xwing-miniatures-font-ig88d-sloopleft:before {
		content: "\0022";
	}
	
	i.xwing-miniatures-font-scum:before {
		content: "\0023";
	}
	
	i.xwing-miniatures-font-attack-turret:before {
		content: "\0024";
	}
	
	i.xwing-miniatures-font-attack:before {
		content: "\0025";
	}
	
	i.xwing-miniatures-font-hull:before {
		content: "\0026";
	}
	
	i.xwing-miniatures-font-ig88d-sloopright:before {
		content: "\0027";
	}
	
	i.xwing-miniatures-font-energy:before {
		content: "\0028";
	}
	
	i.xwing-miniatures-font-epic:before {
		content: "\0029";
	}
	
	i.xwing-miniatures-font-shield:before {
		content: "\002a";
	}
	
	i.xwing-miniatures-font-firstorder:before {
		content: "\002b";
	}
	
	i.xwing-miniatures-font-overlay-frontback:before {
		content: "\002c";
	}
	
	i.xwing-miniatures-font-rebel-outline:before {
		content: "\002d";
	}
	
	i.xwing-miniatures-font-overlay-180:before {
		content: "\002e";
	}
	
	i.xwing-miniatures-font-overlay-turret:before {
		content: "\002f";
	}
	
	i.xwing-miniatures-font-squad-point-cost:before {
		content: "\0030";
	}
	
	i.xwing-miniatures-font-sloopleft:before {
		content: "\0031";
	}
	
	i.xwing-miniatures-font-kturn:before {
		content: "\0032";
	}
	
	i.xwing-miniatures-font-sloopright:before {
		content: "\0033";
	}
	
	i.xwing-miniatures-font-turnleft:before {
		content: "\0034";
	}
	
	i.xwing-miniatures-font-stop:before {
		content: "\0035";
	}
	
	i.xwing-miniatures-font-turnright:before {
		content: "\0036";
	}
	
	i.xwing-miniatures-font-bankleft:before {
		content: "\0037";
	}
	
	i.xwing-miniatures-font-straight:before {
		content: "\0038";
	}
	
	i.xwing-miniatures-font-bankright:before {
		content: "\0039";
	}
	
	i.xwing-miniatures-font-trollleft:before {
		content: "\003a";
	}
	
	i.xwing-miniatures-font-trollright:before {
		content: "\003b";
	}
	
	i.xwing-miniatures-font-attack-frontback:before {
		content: "\003c";
	}
	
	i.xwing-miniatures-font-attack-180:before {
		content: "\003e";
	}
	
	i.xwing-miniatures-font-empire:before {
		content: "\0040";
	}
	
	i.xwing-miniatures-font-astromech:before {
		content: "\0041";
	}
	
	i.xwing-miniatures-font-bomb:before {
		content: "\0042";
	}
	
	i.xwing-miniatures-font-cannon:before {
		content: "\0043";
	}
	
	i.xwing-miniatures-font-elite:before {
		content: "\0045";
	}
	
	i.xwing-miniatures-font-cargo:before {
		content: "\0047";
	}
	
	i.xwing-miniatures-font-hardpoint:before {
		content: "\0048";
	}
	
	i.xwing-miniatures-font-illicit:before {
		content: "\0049";
	}
	
	i.xwing-miniatures-font-missile:before {
		content: "\004d";
	}
	
	i.xwing-miniatures-font-torpedo:before {
		content: "\0050";
	}
	
	i.xwing-miniatures-font-rotatearc:before {
		content: "\0052";
	}
	
	i.xwing-miniatures-font-system:before {
		content: "\0053";
	}
	
	i.xwing-miniatures-font-team:before {
		content: "\0054";
	}
	
	i.xwing-miniatures-font-turret:before {
		content: "\0055";
	}
	
	i.xwing-miniatures-font-salvagedastromech:before {
		content: "\0056";
	}
	
	i.xwing-miniatures-font-crew:before {
		content: "\0057";
	}
	
	i.xwing-miniatures-font-tech:before {
		content: "\0058";
	}
	
	i.xwing-miniatures-font-agility:before {
		content: "\005e";
	}
	
	i.xwing-miniatures-font-boost:before {
		content: "\0062";
	}
	
	i.xwing-miniatures-font-crit:before {
		content: "\0063";
	}
	
	i.xwing-miniatures-font-hit:before {
		content: "\0064";
	}
	
	i.xwing-miniatures-font-evade:before {
		content: "\0065";
	}
	
	i.xwing-miniatures-font-focus:before {
		content: "\0066";
	}
	
	i.xwing-miniatures-font-reinforce:before {
		content: "\0069";
	}
	
	i.xwing-miniatures-font-jam:before {
		content: "\006a";
	}
	
	i.xwing-miniatures-font-cloak:before {
		content: "\006b";
	}
	
	i.xwing-miniatures-font-targetlock:before {
		content: "\006c";
	}
	
	i.xwing-miniatures-font-modification:before {
		content: "\006d";
	}
	
	i.xwing-miniatures-font-coordinate:before {
		content: "\006f";
	}
	
	i.xwing-miniatures-font-barrelroll:before {
		content: "\0072";
	}
	
	i.xwing-miniatures-font-slam:before {
		content: "\0073";
	}
	
	i.xwing-miniatures-font-title:before {
		content: "\0074";
	}
	
	i.xwing-miniatures-font-unique:before {
		content: "\0075";
	}
	
	i.xwing-miniatures-font-recover:before {
		content: "\0076";
	}
	
	i.xwing-miniatures-font-helmet-rebel:before {
		content: "\0078";
	}
	
	i.xwing-miniatures-font-helmet-imperial:before {
		content: "\0079";
	}
	
	i.xwing-miniatures-font-helmet-scum:before {
		content: "\007a";
	}
	
	i.xwing-miniatures-font-token-focus:before {
		content: "\00c4";
	}
	
	i.xwing-miniatures-font-token-evade:before {
		content: "\00c5";
	}
	
	i.xwing-miniatures-font-token-crit:before {
		content: "\00c7";
	}
	
	i.xwing-miniatures-font-token-stress:before {
		content: "\00c9";
	}
	
	i.xwing-miniatures-font-token-shield:before {
		content: "\00d1";
	}
	
	i.xwing-miniatures-font-token-cannotattack:before {
		content: "\00d6";
	}
	
	i.xwing-miniatures-font-token-reinforce:before {
		content: "\00dc";
	}
	
	i.xwing-miniatures-font-token-tractorbeam:before {
		content: "\00e0";
	}
	
	i.xwing-miniatures-font-token-energy:before {
		content: "\00e1";
	}
	
	i.xwing-miniatures-font-token-extramunitions:before {
		content: "\00e2";
	}
	
	i.xwing-miniatures-font-token-targetlock:before {
		content: "\00e3";
	}
	
	i.xwing-miniatures-font-token-ion:before {
		content: "\00e4";
	}
	
	i.xwing-miniatures-font-token-cloak:before {
		content: "\00e5";
	}
	
	i.xwing-miniatures-font-token-focus-outline:before {
		content: "\00e7";
	}
	
	i.xwing-miniatures-font-token-crit-outline:before {
		content: "\00e8";
	}
	
	i.xwing-miniatures-font-token-evade-outline:before {
		content: "\00e9";
	}
	
	i.xwing-miniatures-font-token-stress-outline:before {
		content: "\00ea";
	}
	
	i.xwing-miniatures-font-token-shield-outline:before {
		content: "\00eb";
	}
	
	i.xwing-miniatures-font-token-reinforce-outline:before {
		content: "\00ec";
	}
	
	i.xwing-miniatures-font-token-cannotattack-outline:before {
		content: "\00ed";
	}
	
	i.xwing-miniatures-font-token-energy-outline:before {
		content: "\00ee";
	}
	
	i.xwing-miniatures-font-token-tractorbeam-outline:before {
		content: "\00ef";
	}
	
	i.xwing-miniatures-font-token-extramunitions-outline:before {
		content: "\00f1";
	}
	
	i.xwing-miniatures-font-token-targetlock-outline:before {
		content: "\00f2";
	}
	
	i.xwing-miniatures-font-token-ion-outline:before {
		content: "\00f3";
	}
	
	i.xwing-miniatures-font-token-cloak-outline:before {
		content: "\00f4";
	}
          
  </style>
