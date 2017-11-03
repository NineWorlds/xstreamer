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
    .red {
        color: red;
    }     
         
    .shields-symbol { font-family: "x-wing-symbols";
               font-size: 1.75em;
               color: cyan;
               font-weight: normal;
               font-style: normal;
               text-shadow: 0px 0px #000000;
               -webkit-font-smoothing: always;
             }
             
    .hull-symbol { font-family: "x-wing-symbols";
               font-size: 1.7em;
               color: yellow;
               font-weight: normal;
               font-style: normal;
               text-shadow: 0px 0px #000000;
               -webkit-font-smoothing: always;
             }
    .agility-symbol {
       font-family: "x-wing-symbols";
       font-size: 1.75em;
       color: #56f442;
       font-weight: normal;
       font-style: normal;
       text-shadow: 0px 0px #000000;
       -webkit-font-smoothing: always;
    }

    .attack-symbol {
       font-family: "x-wing-symbols";
       font-size: 1.75em;
       color: #f20e38;
       font-weight: normal;
       font-style: normal;
       text-shadow: 0px 0px #000000;
       -webkit-font-smoothing: always;
    }
             
             
    .upgrade-type-symbol {
       font-family: "x-wing-symbols";
       font-size: 16pt;
       color: #FFFFFF;
       font-weight: normal;
       font-style: normal;
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
     
    .upgradeDiscarded {
       color:red;
    }
    
    .upgradeDiscarded:before {
       content: "\002718";
    }


    <#include "xwing_miniatures_font.ftl">                
  </style>
