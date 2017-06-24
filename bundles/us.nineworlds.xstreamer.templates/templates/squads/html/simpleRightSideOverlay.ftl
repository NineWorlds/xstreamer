<html>
<head>
  <style>
    body { margin: 5px;
           color: #F8F8FF;
           text-shadow: 4px 1px #000000;
           text-align: left;
         }
         
    .shields-symbol { font-family: "x-wing-symbols";
               font-size: 16pt;
               color: cyan;
             }
    .hull-symbol { font-family: "x-wing-symbols";
               font-size: 16pt;
               color: yellow;
             }
         

    .squad { padding:5px;
             background-image: linear-gradient(rgba(0, 0, 0, 0.5),rgba(0, 0, 0, 0.5)); 
         }
    .pilot { font-size: 14pt; margin-botton: 2px;}
    .shields { font-size: 14pt; color: cyan; }
    .hull { font-size: 14pt; color: yellow; }
  </style>
</head>
<body>
<#list xwsspec.pilots as pilot>
<div class="squad">
<b class="pilot">${pilot.name} (${pilot.points})</b><br/>
<b class="shields">${(pilot.shields)!"0"}</b> <span class="shields-symbol">*</span>&nbsp;&nbsp;
<b class="hull">${(pilot.hull)!"0"}</b> <span class="hull-symbol">&</span>
<br/>
</div>
</#list>

</body>
</html>