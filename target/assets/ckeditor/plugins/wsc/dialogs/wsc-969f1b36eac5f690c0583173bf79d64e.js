(function(){function e(e){if(!e)throw"Languages-by-groups list are required for construct selectbox";var t=[],n="",r;for(r in e)for(var i in e[r]){var s=e[r][i];"en_US"==s?n=s:t.push(s)}t.sort();n&&t.unshift(n);return{getCurrentLangGroup:function(t){e:{for(var n in e)for(var r in e[n])if(r.toUpperCase()===t.toUpperCase()){t=n;break e}t=""}return t},setLangList:function(){var t={},n;for(n in e)for(var r in e[n])t[e[n][r]]=r;return t}()}}var t=function(){var e=function(e,t,n){var n=n||{},r=n.expires;if("number"==typeof r&&r){var i=new Date;i.setTime(i.getTime()+1e3*r);r=n.expires=i}r&&r.toUTCString&&(n.expires=r.toUTCString());var t=encodeURIComponent(t),e=e+"="+t,s;for(s in n)t=n[s],e+="; "+s,!0!==t&&(e+="="+t);document.cookie=e};return{postMessage:{init:function(e){window.addEventListener?window.addEventListener("message",e,!1):window.attachEvent("onmessage",e)},send:function(e){var t=Object.prototype.toString,n=e.fn||null,r=e.id||"",i=e.target||window,s=e.message||{id:r};e.message&&"[object Object]"==t.call(e.message)&&(e.message.id||(e.message.id=r),s=e.message);e=window.JSON.stringify(s,n);i.postMessage(e,"*")},unbindHandler:function(e){window.removeEventListener?window.removeEventListener("message",e,!1):window.detachEvent("onmessage",e)}},hash:{create:function(){},parse:function(){}},cookie:{set:e,get:function(e){return(e=document.cookie.match(RegExp("(?:^|; )"+e.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g,"\\$1")+"=([^;]*)")))?decodeURIComponent(e[1]):void 0},remove:function(t){e(t,"",{expires:-1})}},misc:{findFocusable:function(e){var t=null;e&&(t=e.find("a[href], area[href], input, select, textarea, button, *[tabindex], *[contenteditable]"));return t},isVisible:function(e){return!(0===e.offsetWidth||0==e.offsetHeight||"none"===(document.defaultView&&document.defaultView.getComputedStyle?document.defaultView.getComputedStyle(e,null).display:e.currentStyle?e.currentStyle.display:e.style.display))},hasClass:function(e,t){return!(!e.className||!e.className.match(RegExp("(\\s|^)"+t+"(\\s|$)")))}}}}(),n=n||{};n.TextAreaNumber=null;n.load=!0;n.cmd={SpellTab:"spell",Thesaurus:"thes",GrammTab:"grammar"};n.dialog=null;n.optionNode=null;n.selectNode=null;n.grammerSuggest=null;n.textNode={};n.iframeMain=null;n.dataTemp="";n.div_overlay=null;n.textNodeInfo={};n.selectNode={};n.selectNodeResponce={};n.langList=null;n.langSelectbox=null;n.banner="";n.show_grammar=null;n.div_overlay_no_check=null;n.targetFromFrame={};n.onLoadOverlay=null;n.LocalizationComing={};n.OverlayPlace=null;n.LocalizationButton={ChangeTo:{instance:null,text:"Change to"},ChangeAll:{instance:null,text:"Change All"},IgnoreWord:{instance:null,text:"Ignore word"},IgnoreAllWords:{instance:null,text:"Ignore all words"},Options:{instance:null,text:"Options",optionsDialog:{instance:null}},AddWord:{instance:null,text:"Add word"},FinishChecking:{instance:null,text:"Finish Checking"}};n.LocalizationLabel={ChangeTo:{instance:null,text:"Change to"},Suggestions:{instance:null,text:"Suggestions"}};var r=function(e){var t,r;for(r in e)t=e[r].instance.getElement().getFirst()||e[r].instance.getElement(),t.setText(n.LocalizationComing[r])},i=function(e){for(var t in e){if(!e[t].instance.setLabel)break;e[t].instance.setLabel(n.LocalizationComing[t])}},s,o;n.framesetHtml=function(e){return"<iframe id="+n.iframeNumber+"_"+e+' frameborder="0" allowtransparency="1" style="width:100%;border: 1px solid #AEB3B9;overflow: auto;background:#fff; border-radius: 3px;"></iframe>'};n.setIframe=function(e,t){var r;r=n.framesetHtml(t);var i=n.iframeNumber+"_"+t;e.getElement().setHtml(r);r=document.getElementById(i);r=r.contentWindow?r.contentWindow:r.contentDocument.document?r.contentDocument.document:r.contentDocument;r.document.open();r.document.write('<!DOCTYPE html><html><head><meta charset="UTF-8"><title>iframe</title><style>html,body{margin: 0;height: 100%;font: 13px/1.555 "Trebuchet MS", sans-serif;}a{color: #888;font-weight: bold;text-decoration: none;border-bottom: 1px solid #888;}.main-box {color:#252525;padding: 3px 5px;text-align: justify;}.main-box p{margin: 0 0 14px;}.main-box .cerr{color: #f00000;border-bottom-color: #f00000;}</style></head><body><div id="content" class="main-box"></div><iframe src="" frameborder="0" id="spelltext" name="spelltext" style="display:none; width: 100%" ></iframe><iframe src="" frameborder="0" id="loadsuggestfirst" name="loadsuggestfirst" style="display:none; width: 100%" ></iframe><iframe src="" frameborder="0" id="loadspellsuggestall" name="loadspellsuggestall" style="display:none; width: 100%" ></iframe><iframe src="" frameborder="0" id="loadOptionsForm" name="loadOptionsForm" style="display:none; width: 100%" ></iframe><script>(function(window) {var ManagerPostMessage = function() {var _init = function(handler) {if (document.addEventListener) {window.addEventListener("message", handler, false);} else {window.attachEvent("onmessage", handler);};};var _sendCmd = function(o) {var str,type = Object.prototype.toString,fn = o.fn || null,id = o.id || "",target = o.target || window,message = o.message || { "id": id };if (o.message && type.call(o.message) == "[object Object]") {(o.message["id"]) ? o.message["id"] : o.message["id"] = id;message = o.message;};str = JSON.stringify(message, fn);target.postMessage(str, "*");};return {init: _init,send: _sendCmd};};var manageMessageTmp = new ManagerPostMessage;var appString = (function(){var spell = parent.CKEDITOR.config.wsc.DefaultParams.scriptPath;var serverUrl = parent.CKEDITOR.config.wsc.DefaultParams.serviceHost;return serverUrl + spell;})();function loadScript(src, callback) {var scriptTag = document.createElement("script");scriptTag.type = "text/javascript";callback ? callback : callback = function() {};if(scriptTag.readyState) {scriptTag.onreadystatechange = function() {if (scriptTag.readyState == "loaded" ||scriptTag.readyState == "complete") {scriptTag.onreadystatechange = null;setTimeout(function(){scriptTag.parentNode.removeChild(scriptTag)},1);callback();}};}else{scriptTag.onload = function() {setTimeout(function(){scriptTag.parentNode.removeChild(scriptTag)},1);callback();};};scriptTag.src = src;document.getElementsByTagName("head")[0].appendChild(scriptTag);};window.onload = function(){loadScript(appString, function(){manageMessageTmp.send({"id": "iframeOnload","target": window.parent});});}})(this);</script></body></html>');r.document.close()};n.setCurrentIframe=function(e){n.setIframe(n.dialog._.contents[e].Content,e)};n.setHeightBannerFrame=function(){var e=n.dialog.getContentElement("SpellTab","banner").getElement(),t=n.dialog.getContentElement("GrammTab","banner").getElement(),r=n.dialog.getContentElement("Thesaurus","banner").getElement();e.setStyle("height","90px");t.setStyle("height","90px");r.setStyle("height","90px")};n.setHeightFrame=function(){document.getElementById(n.iframeNumber+"_"+n.dialog._.currentTabId).style.height="240px"};n.sendData=function(e){var t=e._.currentTabId,r=e._.contents[t].Content,i,s;n.setIframe(r,t);var o=function(o){o=o||window.event;o.data.getTarget().is("a")&&t!=e._.currentTabId&&(t=e._.currentTabId,r=e._.contents[t].Content,i=n.iframeNumber+"_"+t,n.div_overlay.setEnable(),r.getElement().getChildCount()?d(n.targetFromFrame[i],n.cmd[t]):(n.setIframe(r,t),s=document.getElementById(i),n.targetFromFrame[i]=s.contentWindow))};e.parts.tabs.removeListener("click",o);e.parts.tabs.on("click",o)};n.buildSelectLang=function(e){var t=new CKEDITOR.dom.element("div"),n=new CKEDITOR.dom.element("select"),e="wscLang"+e;t.addClass("cke_dialog_ui_input_select");t.setAttribute("role","presentation");t.setStyles({height:"auto",position:"absolute",right:"0",top:"-1px",width:"160px","white-space":"normal"});n.setAttribute("id",e);n.addClass("cke_dialog_ui_input_select");n.setStyles({width:"160px"});t.append(n);return t};n.buildOptionLang=function(e,t){var r=document.getElementById("wscLang"+t),i=document.createDocumentFragment(),s,o,u=[];if(0===r.options.length){for(s in e)u.push([s,e[s]]);u.sort();for(var a=0;a<u.length;a++)s=document.createElement("option"),s.setAttribute("value",u[a][1]),o=document.createTextNode(u[a][0]),s.appendChild(o),u[a][1]==n.selectingLang&&s.setAttribute("selected","selected"),i.appendChild(s);r.appendChild(i)}};n.buildOptionSynonyms=function(e){e=n.selectNodeResponce[e];n.selectNode.synonyms.clear();for(var t=0;t<e.length;t++)n.selectNode.synonyms.add(e[t],e[t]);n.selectNode.synonyms.getInputElement().$.firstChild.selected=!0;n.textNode.Thesaurus.setValue(n.selectNode.synonyms.getInputElement().getValue())};var u=function(e){var t=document,n=e.target||t.body,r=e.id||"overlayBlock",i=e.opacity||"0.9",e=e.background||"#f1f1f1",s=t.getElementById(r),o=s||t.createElement("div");o.style.cssText="position: absolute;top:30px;bottom:41px;left:1px;right:1px;z-index: 10020;padding:0;margin:0;background:"+e+";opacity: "+i+";filter: alpha(opacity="+100*i+");display: none;";o.id=r;s||n.appendChild(o);return{setDisable:function(){o.style.display="none"},setEnable:function(){o.style.display="block"}}},a=function(e,t,r){var i=new CKEDITOR.dom.element("div"),s=new CKEDITOR.dom.element("input"),o=new CKEDITOR.dom.element("label"),u="wscGrammerSuggest"+e+"_"+t;i.addClass("cke_dialog_ui_input_radio");i.setAttribute("role","presentation");i.setStyles({width:"97%",padding:"5px","white-space":"normal"});s.setAttributes({type:"radio",value:t,name:"wscGrammerSuggest",id:u});s.setStyles({"float":"left"});s.on("click",function(e){n.textNode.GrammTab.setValue(e.sender.getValue())});r&&s.setAttribute("checked",!0);s.addClass("cke_dialog_ui_radio_input");o.appendText(e);o.setAttribute("for",u);o.setStyles({display:"block","line-height":"16px","margin-left":"18px","white-space":"normal"});i.append(s);i.append(o);return i},f=function(e){e=e||"true";null!==e&&"false"==e&&y()},l=function(r){var i=new e(r),r="wscLang"+n.dialog.getParentEditor().name,r=document.getElementById(r),s=n.iframeNumber+"_"+n.dialog._.currentTabId;n.buildOptionLang(i.setLangList,n.dialog.getParentEditor().name);v[i.getCurrentLangGroup(n.selectingLang)]();f(n.show_grammar);r.onchange=function(){v[i.getCurrentLangGroup(this.value)]();f(n.show_grammar);n.div_overlay.setEnable();n.selectingLang=this.value;t.postMessage.send({message:{changeLang:n.selectingLang,text:n.dataTemp},target:n.targetFromFrame[s],id:"selectionLang_outer__page"})}},c=function(e){if("no_any_suggestions"==e){e="No suggestions";n.LocalizationButton.ChangeTo.instance.disable();n.LocalizationButton.ChangeAll.instance.disable();var t=function(e){e=n.LocalizationButton[e].instance;e.getElement().hasClass("cke_disabled")?e.getElement().setStyle("color","#a0a0a0"):e.disable()};t("ChangeTo");t("ChangeAll")}else n.LocalizationButton.ChangeTo.instance.enable(),n.LocalizationButton.ChangeAll.instance.enable(),n.LocalizationButton.ChangeTo.instance.getElement().setStyle("color","#333"),n.LocalizationButton.ChangeAll.instance.getElement().setStyle("color","#333");return e},h={iframeOnload:function(){n.div_overlay.setEnable();var e=n.dialog._.currentTabId;d(n.targetFromFrame[n.iframeNumber+"_"+e],n.cmd[e])},suggestlist:function(e){delete e.id;n.div_overlay_no_check.setDisable();S();l(n.langList);var t=c(e.word),r="";t instanceof Array&&(t=e.word[0]);r=t=t.split(",");o.clear();n.textNode.SpellTab.setValue(r[0]);for(e=0;e<r.length;e++)o.add(r[e],r[e]);w();n.div_overlay.setDisable()},grammerSuggest:function(e){delete e.id;delete e.mocklangs;S();l(n.langList);var t=e.grammSuggest[0];n.grammerSuggest.getElement().setHtml("");n.textNode.GrammTab.reset();n.textNode.GrammTab.setValue(t);n.textNodeInfo.GrammTab.getElement().setHtml("");n.textNodeInfo.GrammTab.getElement().setText(e.info);for(var e=e.grammSuggest,t=e.length,r=!0,i=0;i<t;i++)n.grammerSuggest.getElement().append(a(e[i],e[i],r)),r=!1;w();n.div_overlay.setDisable()},thesaurusSuggest:function(e){delete e.id;delete e.mocklangs;S();l(n.langList);n.selectNodeResponce=e;n.textNode.Thesaurus.reset();n.selectNode.categories.clear();for(var t in e)n.selectNode.categories.add(t,t);e=n.selectNode.categories.getInputElement().getChildren().$[0].value;n.selectNode.categories.getInputElement().getChildren().$[0].selected=!0;n.buildOptionSynonyms(e);w();n.div_overlay.setDisable()},finish:function(e){delete e.id;E();e=n.dialog.getContentElement(n.dialog._.currentTabId,"BlockFinishChecking").getElement();e.removeStyle("display");e.removeStyle("position");e.removeStyle("left");e.show();n.div_overlay.setDisable()},settext:function(e){delete e.id;n.dialog.getParentEditor().getCommand("checkspell");var t=n.dialog.getParentEditor();try{t.focus()}catch(r){}t.setData(e.text,function(){n.dataTemp="";t.unlockSelection();t.fire("saveSnapshot");n.dialog.hide()})},ReplaceText:function(e){delete e.id;n.div_overlay.setEnable();n.dataTemp=e.text;n.selectingLang=e.currentLang;window.setTimeout(function(){try{n.div_overlay.setDisable()}catch(e){}},500);r(n.LocalizationButton);i(n.LocalizationLabel)},options_checkbox_send:function(e){delete e.id;e={osp:t.cookie.get("osp"),udn:t.cookie.get("udn"),cust_dic_ids:n.cust_dic_ids};t.postMessage.send({message:e,target:n.targetFromFrame[n.iframeNumber+"_"+n.dialog._.currentTabId],id:"options_outer__page"})},getOptions:function(e){var r=e.DefOptions.udn;n.LocalizationComing=e.DefOptions.localizationButtonsAndText;n.show_grammar=e.show_grammar;n.langList=e.lang;if(n.bnr=e.bannerId){n.setHeightBannerFrame();var i=e.banner;n.dialog.getContentElement(n.dialog._.currentTabId,"banner").getElement().setHtml(i)}else n.setHeightFrame();"undefined"==r&&(n.userDictionaryName?(r=n.userDictionaryName,i={osp:t.cookie.get("osp"),udn:n.userDictionaryName,cust_dic_ids:n.cust_dic_ids,id:"options_dic_send",udnCmd:"create"},t.postMessage.send({message:i,target:n.targetFromFrame[void 0]})):r="");t.cookie.set("osp",e.DefOptions.osp);t.cookie.set("udn",r);t.cookie.set("cust_dic_ids",e.DefOptions.cust_dic_ids);t.postMessage.send({id:"giveOptions"})},options_dic_send:function(){var e={osp:t.cookie.get("osp"),udn:t.cookie.get("udn"),cust_dic_ids:n.cust_dic_ids,id:"options_dic_send",udnCmd:t.cookie.get("udnCmd")};t.postMessage.send({message:e,target:n.targetFromFrame[n.iframeNumber+"_"+n.dialog._.currentTabId]})},data:function(e){delete e.id},giveOptions:function(){},setOptionsConfirmF:function(){},setOptionsConfirmT:function(){s.setValue("")},clickBusy:function(){n.div_overlay.setEnable()},suggestAllCame:function(){n.div_overlay.setDisable();n.div_overlay_no_check.setDisable()},TextCorrect:function(){l(n.langList)}},p=function(e){e=e||window.event;if((e=window.JSON.parse(e.data))&&e.id)h[e.id](e)},d=function(e,r,i,s){r=r||CKEDITOR.config.wsc_cmd;i=i||n.dataTemp;t.postMessage.send({message:{customerId:n.wsc_customerId,text:i,txt_ctrl:n.TextAreaNumber,cmd:r,cust_dic_ids:n.cust_dic_ids,udn:n.userDictionaryName,slang:n.selectingLang,reset_suggest:s||!1},target:e,id:"data_outer__page"});n.div_overlay.setEnable()},v={superset:function(){n.dialog.showPage("Thesaurus");n.dialog.showPage("GrammTab");b()},usual:function(){g();y();b()},rtl:function(){g();y();b()}},m=function(e){var t=new function(e){var t={};return{getCmdByTab:function(n){for(var r in e)t[e[r]]=r;return t[n]}}}(n.cmd);e.selectPage(t.getCmdByTab(CKEDITOR.config.wsc_cmd));n.sendData(e)},g=function(){n.dialog.hidePage("Thesaurus")},y=function(){n.dialog.hidePage("GrammTab")},b=function(){n.dialog.showPage("SpellTab")},w=function(){var e=n.dialog.getContentElement(n.dialog._.currentTabId,"bottomGroup").getElement();e.removeStyle("display");e.removeStyle("position");e.removeStyle("left");e.show()},E=function(){var e=n.dialog.getContentElement(n.dialog._.currentTabId,"bottomGroup").getElement(),r=document.activeElement,i;e.setStyles({display:"block",position:"absolute",left:"-9999px"});setTimeout(function(){e.removeStyle("display");e.removeStyle("position");e.removeStyle("left");e.hide();n.dialog._.editor.focusManager.currentActive.focusNext();i=t.misc.findFocusable(n.dialog.parts.contents);if(!t.misc.hasClass(r,"cke_dialog_tab")&&!t.misc.hasClass(r,"cke_dialog_contents_body")&&t.misc.isVisible(r))try{r.focus()}catch(s){}else for(var o=0,u;o<i.count();o++)if(u=i.getItem(o),t.misc.isVisible(u.$)){try{u.$.focus()}catch(a){}break}},0)},S=function(){var e=n.dialog.getContentElement(n.dialog._.currentTabId,"BlockFinishChecking").getElement(),r=document.activeElement,i;e.setStyles({display:"block",position:"absolute",left:"-9999px"});setTimeout(function(){e.removeStyle("display");e.removeStyle("position");e.removeStyle("left");e.hide();n.dialog._.editor.focusManager.currentActive.focusNext();i=t.misc.findFocusable(n.dialog.parts.contents);if(!t.misc.hasClass(r,"cke_dialog_tab")&&!t.misc.hasClass(r,"cke_dialog_contents_body")&&t.misc.isVisible(r))try{r.focus()}catch(s){}else for(var o=0,u;o<i.count();o++)if(u=i.getItem(o),t.misc.isVisible(u.$)){try{u.$.focus()}catch(a){}break}},0)};CKEDITOR.dialog.add("checkspell",function(e){var r=function(){this.getElement().focus();n.div_overlay.setEnable();var r=n.dialog._.currentTabId,i=n.iframeNumber+"_"+r,s=n.textNode[r].getValue(),o=this.getElement().getAttribute("title-cmd");t.postMessage.send({message:{cmd:o,tabId:r,new_word:s},target:n.targetFromFrame[i],id:"cmd_outer__page"});("ChangeTo"==o||"ChangeAll"==o)&&e.fire("saveSnapshot");"FinishChecking"==o&&e.config.wsc_onFinish.call(CKEDITOR.document.getWindow().getFrame())};return{title:e.config.wsc_dialogTitle||e.lang.wsc.title,minWidth:560,minHeight:444,buttons:[CKEDITOR.dialog.cancelButton],onLoad:function(){n.dialog=this;g();y();b()},onShow:function(){e.lockSelection(e.getSelection());n.TextAreaNumber="cke_textarea_"+CKEDITOR.currentInstance.name;t.postMessage.init(p);n.dataTemp=CKEDITOR.currentInstance.getData();n.OverlayPlace=n.dialog.parts.tabs.getParent().$;if(CKEDITOR&&CKEDITOR.config){n.wsc_customerId=e.config.wsc_customerId;n.cust_dic_ids=e.config.wsc_customDictionaryIds;n.userDictionaryName=e.config.wsc_userDictionaryName;n.defaultLanguage=CKEDITOR.config.defaultLanguage;var r="file:"==document.location.protocol?"http:":document.location.protocol;CKEDITOR.scriptLoader.load(e.config.wsc_customLoaderScript||r+"//loader.webspellchecker.net/sproxy_fck/sproxy.php?plugin=fck2&customerid="+n.wsc_customerId+"&cmd=script&doc=wsc&schema=22",function(t){CKEDITOR.config&&CKEDITOR.config.wsc&&CKEDITOR.config.wsc.DefaultParams?(n.serverLocationHash=CKEDITOR.config.wsc.DefaultParams.serviceHost,n.logotype=CKEDITOR.config.wsc.DefaultParams.logoPath,n.loadIcon=CKEDITOR.config.wsc.DefaultParams.iconPath,n.loadIconEmptyEditor=CKEDITOR.config.wsc.DefaultParams.iconPathEmptyEditor,n.LangComparer=new CKEDITOR.config.wsc.DefaultParams._SP_FCK_LangCompare):(n.serverLocationHash=DefaultParams.serviceHost,n.logotype=DefaultParams.logoPath,n.loadIcon=DefaultParams.iconPath,n.loadIconEmptyEditor=DefaultParams.iconPathEmptyEditor,n.LangComparer=new _SP_FCK_LangCompare);n.pluginPath=CKEDITOR.getUrl(e.plugins.wsc.path);n.iframeNumber=n.TextAreaNumber;n.templatePath=n.pluginPath+"dialogs/tmp.html";n.LangComparer.setDefaulLangCode(n.defaultLanguage);n.currentLang=e.config.wsc_lang||n.LangComparer.getSPLangCode(e.langCode);n.selectingLang=n.currentLang;n.div_overlay=new u({opacity:"1",background:"#fff url("+n.loadIcon+") no-repeat 50% 50%",target:n.OverlayPlace});var r=n.dialog.parts.tabs.getId(),r=CKEDITOR.document.getById(r);r.setStyle("width","97%");r.getElementsByTag("DIV").count()||r.append(n.buildSelectLang(n.dialog.getParentEditor().name));n.div_overlay_no_check=new u({opacity:"1",id:"no_check_over",background:"#fff url("+n.loadIconEmptyEditor+") no-repeat 50% 50%",target:n.OverlayPlace});t&&(m(n.dialog),n.dialog.setupContent(n.dialog))})}else n.dialog.hide()},onHide:function(){var r=CKEDITOR.plugins.scayt,i=e.scayt;e.unlockSelection();r&&i&&r.state[e.name]&&i.setMarkupPaused&&i.setMarkupPaused(!1);n.dataTemp="";t.postMessage.unbindHandler(p)},contents:[{id:"SpellTab",label:"SpellChecker",accessKey:"S",elements:[{type:"html",id:"banner",label:"banner",style:"",html:"<div></div>"},{type:"html",id:"Content",label:"spellContent",html:"",setup:function(e){var e=n.iframeNumber+"_"+e._.currentTabId,t=document.getElementById(e);n.targetFromFrame[e]=t.contentWindow}},{type:"hbox",id:"bottomGroup",style:"width:560px; margin: 0 auto;",widths:["50%","50%"],children:[{type:"hbox",id:"leftCol",align:"left",width:"50%",children:[{type:"vbox",id:"rightCol1",widths:["50%","50%"],children:[{type:"text",id:"text",label:n.LocalizationLabel.ChangeTo.text+":",labelLayout:"horizontal",labelStyle:"font: 12px/25px arial, sans-serif;",width:"140px","default":"",onShow:function(){n.textNode.SpellTab=this;n.LocalizationLabel.ChangeTo.instance=this},onHide:function(){this.reset()}},{type:"hbox",id:"rightCol",align:"right",width:"30%",children:[{type:"vbox",id:"rightCol_col__left",children:[{type:"text",id:"labelSuggestions",label:n.LocalizationLabel.Suggestions.text+":",onShow:function(){n.LocalizationLabel.Suggestions.instance=this;this.getInputElement().setStyles({display:"block",position:"absolute",left:"-9999px"})}},{type:"html",id:"logo",html:'<img width="99" height="68" border="0" src="" title="WebSpellChecker.net" alt="WebSpellChecker.net" style="display: inline-block;">',setup:function(){this.getElement().$.src=n.logotype;this.getElement().getParent().setStyles({"text-align":"left"})}}]},{type:"select",id:"list_of_suggestions",labelStyle:"font: 12px/25px arial, sans-serif;",size:"6",inputStyle:"width: 140px; height: auto;",items:[["loading..."]],onShow:function(){o=this},onHide:function(){this.clear()},onChange:function(){n.textNode.SpellTab.setValue(this.getValue())}}]}]}]},{type:"hbox",id:"rightCol",align:"right",width:"50%",children:[{type:"vbox",id:"rightCol_col__left",widths:["50%","50%","50%","50%"],children:[{type:"button",id:"ChangeTo",label:n.LocalizationButton.ChangeTo.text,title:"Change to",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id);n.LocalizationButton.ChangeTo.instance=this},onClick:r},{type:"button",id:"ChangeAll",label:n.LocalizationButton.ChangeAll.text,title:"Change All",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id);n.LocalizationButton.ChangeAll.instance=this},onClick:r},{type:"button",id:"AddWord",label:n.LocalizationButton.AddWord.text,title:"Add word",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id);n.LocalizationButton.AddWord.instance=this},onClick:r},{type:"button",id:"FinishChecking",label:n.LocalizationButton.FinishChecking.text,title:"Finish Checking",style:"width: 100%;margin-top: 9px;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id);n.LocalizationButton.FinishChecking.instance=this},onClick:r}]},{type:"vbox",id:"rightCol_col__right",widths:["50%","50%","50%"],children:[{type:"button",id:"IgnoreWord",label:n.LocalizationButton.IgnoreWord.text,title:"Ignore word",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id);n.LocalizationButton.IgnoreWord.instance=this},onClick:r},{type:"button",id:"IgnoreAllWords",label:n.LocalizationButton.IgnoreAllWords.text,title:"Ignore all words",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id);n.LocalizationButton.IgnoreAllWords.instance=this},onClick:r},{type:"button",id:"option",label:n.LocalizationButton.Options.text,title:"Option",style:"width: 100%;",onLoad:function(){n.LocalizationButton.Options.instance=this;"file:"==document.location.protocol&&this.disable()},onClick:function(){this.getElement().focus();"file:"==document.location.protocol?alert("WSC: Options functionality is disabled when runing from file system"):(x=document.activeElement,e.openDialog("options"))}}]}]}]},{type:"hbox",id:"BlockFinishChecking",style:"width:560px; margin: 0 auto;",widths:["70%","30%"],onShow:function(){this.getElement().setStyles({display:"block",position:"absolute",left:"-9999px"})},onHide:w,children:[{type:"hbox",id:"leftCol",align:"left",width:"70%",children:[{type:"vbox",id:"rightCol1",setup:function(){this.getChild()[0].getElement().$.src=n.logotype;this.getChild()[0].getElement().getParent().setStyles({"text-align":"center"})},children:[{type:"html",id:"logo",html:'<img width="99" height="68" border="0" src="" title="WebSpellChecker.net" alt="WebSpellChecker.net" style="display: inline-block;">'}]}]},{type:"hbox",id:"rightCol",align:"right",width:"30%",children:[{type:"vbox",id:"rightCol_col__left",children:[{type:"button",id:"Option_button",label:n.LocalizationButton.Options.text,title:"Option",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id);"file:"==document.location.protocol&&this.disable()},onClick:function(){this.getElement().focus();"file:"==document.location.protocol?alert("WSC: Options functionality is disabled when runing from file system"):(x=document.activeElement,e.openDialog("options"))}},{type:"button",id:"FinishChecking",label:n.LocalizationButton.FinishChecking.text,title:"Finish Checking",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onClick:r}]}]}]}]},{id:"GrammTab",label:"Grammar",accessKey:"G",elements:[{type:"html",id:"banner",label:"banner",style:"",html:"<div></div>"},{type:"html",id:"Content",label:"GrammarContent",html:"",setup:function(){var e=n.iframeNumber+"_"+n.dialog._.currentTabId,t=document.getElementById(e);n.targetFromFrame[e]=t.contentWindow}},{type:"vbox",id:"bottomGroup",style:"width:560px; margin: 0 auto;",children:[{type:"hbox",id:"leftCol",widths:["66%","34%"],children:[{type:"vbox",children:[{type:"text",id:"text",label:"Change to:",labelLayout:"horizontal",labelStyle:"font: 12px/25px arial, sans-serif;",inputStyle:"float: right; width: 200px;","default":"",onShow:function(){n.textNode.GrammTab=this},onHide:function(){this.reset()}},{type:"html",id:"html_text",html:"<div style='min-height: 17px; line-height: 17px; padding: 5px; text-align: left;background: #F1F1F1;color: #595959; white-space: normal!important;'></div>",onShow:function(){n.textNodeInfo.GrammTab=this}},{type:"html",id:"radio",html:"",onShow:function(){n.grammerSuggest=this}}]},{type:"vbox",children:[{type:"button",id:"ChangeTo",label:"Change to",title:"Change to",style:"width: 133px; float: right;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onClick:r},{type:"button",id:"IgnoreWord",label:"Ignore word",title:"Ignore word",style:"width: 133px; float: right;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onClick:r},{type:"button",id:"IgnoreAllWords",label:"Ignore Problem",title:"Ignore Problem",style:"width: 133px; float: right;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onClick:r},{type:"button",id:"FinishChecking",label:"Finish Checking",title:"Finish Checking",style:"width: 133px; float: right; margin-top: 9px;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onClick:r}]}]}]},{type:"hbox",id:"BlockFinishChecking",style:"width:560px; margin: 0 auto;",widths:["70%","30%"],onShow:function(){this.getElement().setStyles({display:"block",position:"absolute",left:"-9999px"})},onHide:w,children:[{type:"hbox",id:"leftCol",align:"left",width:"70%",children:[{type:"vbox",id:"rightCol1",children:[{type:"html",id:"logo",html:'<img width="99" height="68" border="0" src="" title="WebSpellChecker.net" alt="WebSpellChecker.net" style="display: inline-block;">',setup:function(){this.getElement().$.src=n.logotype;this.getElement().getParent().setStyles({"text-align":"center"})}}]}]},{type:"hbox",id:"rightCol",align:"right",width:"30%",children:[{type:"vbox",id:"rightCol_col__left",children:[{type:"button",id:"FinishChecking",label:"Finish Checking",title:"Finish Checking",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onClick:r}]}]}]}]},{id:"Thesaurus",label:"Thesaurus",accessKey:"T",elements:[{type:"html",id:"banner",label:"banner",style:"",html:"<div></div>"},{type:"html",id:"Content",label:"spellContent",html:"",setup:function(){var e=n.iframeNumber+"_"+n.dialog._.currentTabId,t=document.getElementById(e);n.targetFromFrame[e]=t.contentWindow}},{type:"vbox",id:"bottomGroup",style:"width:560px; margin: -10px auto; overflow: hidden;",children:[{type:"hbox",widths:["75%","25%"],children:[{type:"vbox",children:[{type:"hbox",widths:["65%","35%"],children:[{type:"text",id:"ChangeTo",label:"Change to:",labelLayout:"horizontal",inputStyle:"width: 160px;",labelStyle:"font: 12px/25px arial, sans-serif;","default":"",onShow:function(){n.textNode.Thesaurus=this},onHide:function(){this.reset()}},{type:"button",id:"ChangeTo",label:"Change to",title:"Change to",style:"width: 121px; margin-top: 1px;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onClick:r}]},{type:"hbox",children:[{type:"select",id:"categories",label:"Categories:",labelStyle:"font: 12px/25px arial, sans-serif;",size:"5",inputStyle:"width: 180px; height: auto;",items:[],onShow:function(){n.selectNode.categories=this},onHide:function(){this.clear()},onChange:function(){n.buildOptionSynonyms(this.getValue())}},{type:"select",id:"synonyms",label:"Synonyms:",labelStyle:"font: 12px/25px arial, sans-serif;",size:"5",inputStyle:"width: 180px; height: auto;",items:[],onShow:function(){n.selectNode.synonyms=this;n.textNode.Thesaurus.setValue(this.getValue())},onHide:function(){this.clear()},onChange:function(){n.textNode.Thesaurus.setValue(this.getValue())}}]}]},{type:"vbox",width:"120px",style:"margin-top:46px;",children:[{type:"html",id:"logotype",label:"WebSpellChecker.net",html:'<img width="99" height="68" border="0" src="" title="WebSpellChecker.net" alt="WebSpellChecker.net" style="display: inline-block;">',setup:function(){this.getElement().$.src=n.logotype;this.getElement().getParent().setStyles({"text-align":"center"})}},{type:"button",id:"FinishChecking",label:"Finish Checking",title:"Finish Checking",style:"width: 121px; float: right; margin-top: 9px;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onClick:r}]}]}]},{type:"hbox",id:"BlockFinishChecking",style:"width:560px; margin: 0 auto;",widths:["70%","30%"],onShow:function(){this.getElement().setStyles({display:"block",position:"absolute",left:"-9999px"})},children:[{type:"hbox",id:"leftCol",align:"left",width:"70%",children:[{type:"vbox",id:"rightCol1",children:[{type:"html",id:"logo",html:'<img width="99" height="68" border="0" src="" title="WebSpellChecker.net" alt="WebSpellChecker.net" style="display: inline-block;">',setup:function(){this.getElement().$.src=n.logotype;this.getElement().getParent().setStyles({"text-align":"center"})}}]}]},{type:"hbox",id:"rightCol",align:"right",width:"30%",children:[{type:"vbox",id:"rightCol_col__left",children:[{type:"button",id:"FinishChecking",label:"Finish Checking",title:"Finish Checking",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onClick:r}]}]}]}]}]}});var x=null;CKEDITOR.dialog.add("options",function(){var e=null,r={},i={},o=null,u=null;t.cookie.get("udn");t.cookie.get("osp");var a=function(){u=this.getElement().getAttribute("title-cmd");var e=[];e[0]=i.IgnoreAllCapsWords;e[1]=i.IgnoreWordsNumbers;e[2]=i.IgnoreMixedCaseWords;e[3]=i.IgnoreDomainNames;e=e.toString().replace(/,/g,"");t.cookie.set("osp",e);t.cookie.set("udnCmd",u?u:"ignore");"delete"!=u&&(e="",""!==s.getValue()&&(e=s.getValue()),t.cookie.set("udn",e));t.postMessage.send({id:"options_dic_send"})},f=function(){o.getElement().setHtml(n.LocalizationComing.error);o.getElement().show()};return{title:n.LocalizationComing.Options,minWidth:430,minHeight:130,resizable:CKEDITOR.DIALOG_RESIZE_NONE,contents:[{id:"OptionsTab",label:"Options",accessKey:"O",elements:[{type:"hbox",id:"options_error",children:[{type:"html",style:"display: block;text-align: center;white-space: normal!important; font-size: 12px;color:red",html:"<div></div>",onShow:function(){o=this}}]},{type:"vbox",id:"Options_content",children:[{type:"hbox",id:"Options_manager",widths:["52%","48%"],children:[{type:"fieldset",label:"Spell Checking Options",style:"border: none;margin-top: 13px;padding: 10px 0 10px 10px",onShow:function(){this.getInputElement().$.children[0].innerHTML=n.LocalizationComing.SpellCheckingOptions},children:[{type:"vbox",id:"Options_checkbox",children:[{type:"checkbox",id:"IgnoreAllCapsWords",label:"Ignore All-Caps Words",labelStyle:"margin-left: 5px; font: 12px/16px arial, sans-serif;display: inline-block;white-space: normal;",style:"float:left; min-height: 16px;","default":"",onClick:function(){i[this.id]=!this.getValue()?0:1}},{type:"checkbox",id:"IgnoreWordsNumbers",label:"Ignore Words with Numbers",labelStyle:"margin-left: 5px; font: 12px/16px arial, sans-serif;display: inline-block;white-space: normal;",style:"float:left; min-height: 16px;","default":"",onClick:function(){i[this.id]=!this.getValue()?0:1}},{type:"checkbox",id:"IgnoreMixedCaseWords",label:"Ignore Mixed-Case Words",labelStyle:"margin-left: 5px; font: 12px/16px arial, sans-serif;display: inline-block;white-space: normal;",style:"float:left; min-height: 16px;","default":"",onClick:function(){i[this.id]=!this.getValue()?0:1}},{type:"checkbox",id:"IgnoreDomainNames",label:"Ignore Domain Names",labelStyle:"margin-left: 5px; font: 12px/16px arial, sans-serif;display: inline-block;white-space: normal;",style:"float:left; min-height: 16px;","default":"",onClick:function(){i[this.id]=!this.getValue()?0:1}}]}]},{type:"vbox",id:"Options_DictionaryName",children:[{type:"text",id:"DictionaryName",style:"margin-bottom: 10px",label:"Dictionary Name:",labelLayout:"vertical",labelStyle:"font: 12px/25px arial, sans-serif;","default":"",onLoad:function(){s=this;this.setValue(n.userDictionaryName?n.userDictionaryName:(t.cookie.get("udn"),this.getValue()))},onShow:function(){s=this;this.setValue(!t.cookie.get("udn")?this.getValue():t.cookie.get("udn"));this.setLabel(n.LocalizationComing.DictionaryName)},onHide:function(){this.reset()}},{type:"hbox",id:"Options_buttons",children:[{type:"vbox",id:"Options_leftCol_col",widths:["50%","50%"],children:[{type:"button",id:"create",label:"Create",title:"Create",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onShow:function(){(this.getElement().getFirst()||this.getElement()).setText(n.LocalizationComing.Create)},onClick:a},{type:"button",id:"restore",label:"Restore",title:"Restore",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onShow:function(){(this.getElement().getFirst()||this.getElement()).setText(n.LocalizationComing.Restore)},onClick:a}]},{type:"vbox",id:"Options_rightCol_col",widths:["50%","50%"],children:[{type:"button",id:"rename",label:"Rename",title:"Rename",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onShow:function(){(this.getElement().getFirst()||this.getElement()).setText(n.LocalizationComing.Rename)},onClick:a},{type:"button",id:"delete",label:"Remove",title:"Remove",style:"width: 100%;",onLoad:function(){this.getElement().setAttribute("title-cmd",this.id)},onShow:function(){(this.getElement().getFirst()||this.getElement()).setText(n.LocalizationComing.Remove)},onClick:a}]}]}]}]},{type:"hbox",id:"Options_text",children:[{type:"html",style:"text-align: justify;margin-top: 15px;white-space: normal!important; font-size: 12px;color:#777;",html:"<div>"+n.LocalizationComing.OptionsTextIntro+"</div>",onShow:function(){this.getElement().setText(n.LocalizationComing.OptionsTextIntro)}}]}]}]}],buttons:[CKEDITOR.dialog.okButton,CKEDITOR.dialog.cancelButton],onOk:function(){var e=[];e[0]=i.IgnoreAllCapsWords;e[1]=i.IgnoreWordsNumbers;e[2]=i.IgnoreMixedCaseWords;e[3]=i.IgnoreDomainNames;e=e.toString().replace(/,/g,"");t.cookie.set("osp",e);t.cookie.set("udn",s.getValue());t.postMessage.send({id:"options_checkbox_send"});o.getElement().hide();o.getElement().setHtml(" ")},onLoad:function(){e=this;r.IgnoreAllCapsWords=e.getContentElement("OptionsTab","IgnoreAllCapsWords");r.IgnoreWordsNumbers=e.getContentElement("OptionsTab","IgnoreWordsNumbers");r.IgnoreMixedCaseWords=e.getContentElement("OptionsTab","IgnoreMixedCaseWords");r.IgnoreDomainNames=e.getContentElement("OptionsTab","IgnoreDomainNames")},onShow:function(){t.postMessage.init(f);var e=t.cookie.get("osp").split("");i.IgnoreAllCapsWords=e[0];i.IgnoreWordsNumbers=e[1];i.IgnoreMixedCaseWords=e[2];i.IgnoreDomainNames=e[3];!parseInt(i.IgnoreAllCapsWords,10)?r.IgnoreAllCapsWords.setValue("",!1):r.IgnoreAllCapsWords.setValue("checked",!1);!parseInt(i.IgnoreWordsNumbers,10)?r.IgnoreWordsNumbers.setValue("",!1):r.IgnoreWordsNumbers.setValue("checked",!1);!parseInt(i.IgnoreMixedCaseWords,10)?r.IgnoreMixedCaseWords.setValue("",!1):r.IgnoreMixedCaseWords.setValue("checked",!1);!parseInt(i.IgnoreDomainNames,10)?r.IgnoreDomainNames.setValue("",!1):r.IgnoreDomainNames.setValue("checked",!1);i.IgnoreAllCapsWords=!r.IgnoreAllCapsWords.getValue()?0:1;i.IgnoreWordsNumbers=!r.IgnoreWordsNumbers.getValue()?0:1;i.IgnoreMixedCaseWords=!r.IgnoreMixedCaseWords.getValue()?0:1;i.IgnoreDomainNames=!r.IgnoreDomainNames.getValue()?0:1;r.IgnoreAllCapsWords.getElement().$.lastChild.innerHTML=n.LocalizationComing.IgnoreAllCapsWords;r.IgnoreWordsNumbers.getElement().$.lastChild.innerHTML=n.LocalizationComing.IgnoreWordsWithNumbers;r.IgnoreMixedCaseWords.getElement().$.lastChild.innerHTML=n.LocalizationComing.IgnoreMixedCaseWords;r.IgnoreDomainNames.getElement().$.lastChild.innerHTML=n.LocalizationComing.IgnoreDomainNames},onHide:function(){t.postMessage.unbindHandler(f);if(x)try{x.focus()}catch(e){}}}});CKEDITOR.dialog.on("resize",function(e){var e=e.data,t=e.dialog,r=CKEDITOR.document.getById(n.iframeNumber+"_"+t._.currentTabId);"checkspell"==t._.name&&(n.bnr?r&&r.setSize("height",e.height-310):r&&r.setSize("height",e.height-220))});CKEDITOR.on("dialogDefinition",function(e){if("checkspell"===e.data.name){var t=e.data.definition;n.onLoadOverlay=new u({opacity:"1",background:"#fff",target:t.dialog.parts.tabs.getParent().$});n.onLoadOverlay.setEnable();t.dialog.on("cancel",function(){t.dialog.getParentEditor().config.wsc_onClose.call(this.document.getWindow().getFrame());n.div_overlay.setDisable();n.onLoadOverlay.setDisable();return!1},this,null,-1)}})})()