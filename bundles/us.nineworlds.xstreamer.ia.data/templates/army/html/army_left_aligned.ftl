<#-- 
  This generates an Army overlay for Imperial Assault.  The text will
  be left aligned.
-->
<#import "common/common_utils.ftl" as fun>
<html>
<head>
  <#include "common/css_leftaligned_army.ftl"/>
  <#if obs_refresh>
     <#include "common/js_refresh.ftl">
  </#if>
 </head>
<body>
  <#include "leftaligned_army_detail.ftl"/>
</body>

