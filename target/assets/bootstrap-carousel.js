+function(e){"use strict";function n(n){return this.each(function(){var r=e(this);var i=r.data("bs.carousel");var s=e.extend({},t.DEFAULTS,r.data(),typeof n=="object"&&n);var o=typeof n=="string"?n:s.slide;if(!i)r.data("bs.carousel",i=new t(this,s));if(typeof n=="number")i.to(n);else if(o)i[o]();else if(s.interval)i.pause().cycle()})}var t=function(t,n){this.$element=e(t).on("keydown.bs.carousel",e.proxy(this.keydown,this));this.$indicators=this.$element.find(".carousel-indicators");this.options=n;this.paused=this.sliding=this.interval=this.$active=this.$items=null;this.options.pause=="hover"&&this.$element.on("mouseenter.bs.carousel",e.proxy(this.pause,this)).on("mouseleave.bs.carousel",e.proxy(this.cycle,this))};t.VERSION="3.2.0";t.DEFAULTS={interval:5e3,pause:"hover",wrap:true};t.prototype.keydown=function(e){switch(e.which){case 37:this.prev();break;case 39:this.next();break;default:return}e.preventDefault()};t.prototype.cycle=function(t){t||(this.paused=false);this.interval&&clearInterval(this.interval);this.options.interval&&!this.paused&&(this.interval=setInterval(e.proxy(this.next,this),this.options.interval));return this};t.prototype.getItemIndex=function(e){this.$items=e.parent().children(".item");return this.$items.index(e||this.$active)};t.prototype.to=function(t){var n=this;var r=this.getItemIndex(this.$active=this.$element.find(".item.active"));if(t>this.$items.length-1||t<0)return;if(this.sliding)return this.$element.one("slid.bs.carousel",function(){n.to(t)});if(r==t)return this.pause().cycle();return this.slide(t>r?"next":"prev",e(this.$items[t]))};t.prototype.pause=function(t){t||(this.paused=true);if(this.$element.find(".next, .prev").length&&e.support.transition){this.$element.trigger(e.support.transition.end);this.cycle(true)}this.interval=clearInterval(this.interval);return this};t.prototype.next=function(){if(this.sliding)return;return this.slide("next")};t.prototype.prev=function(){if(this.sliding)return;return this.slide("prev")};t.prototype.slide=function(t,n){var r=this.$element.find(".item.active");var i=n||r[t]();var s=this.interval;var o=t=="next"?"left":"right";var u=t=="next"?"first":"last";var a=this;if(!i.length){if(!this.options.wrap)return;i=this.$element.find(".item")[u]()}if(i.hasClass("active"))return this.sliding=false;var f=i[0];var l=e.Event("slide.bs.carousel",{relatedTarget:f,direction:o});this.$element.trigger(l);if(l.isDefaultPrevented())return;this.sliding=true;s&&this.pause();if(this.$indicators.length){this.$indicators.find(".active").removeClass("active");var c=e(this.$indicators.children()[this.getItemIndex(i)]);c&&c.addClass("active")}var h=e.Event("slid.bs.carousel",{relatedTarget:f,direction:o});if(e.support.transition&&this.$element.hasClass("slide")){i.addClass(t);i[0].offsetWidth;r.addClass(o);i.addClass(o);r.one("bsTransitionEnd",function(){i.removeClass([t,o].join(" ")).addClass("active");r.removeClass(["active",o].join(" "));a.sliding=false;setTimeout(function(){a.$element.trigger(h)},0)}).emulateTransitionEnd(r.css("transition-duration").slice(0,-1)*1e3)}else{r.removeClass("active");i.addClass("active");this.sliding=false;this.$element.trigger(h)}s&&this.cycle();return this};var r=e.fn.carousel;e.fn.carousel=n;e.fn.carousel.Constructor=t;e.fn.carousel.noConflict=function(){e.fn.carousel=r;return this};e(document).on("click.bs.carousel.data-api","[data-slide], [data-slide-to]",function(t){var r;var i=e(this);var s=e(i.attr("data-target")||(r=i.attr("href"))&&r.replace(/.*(?=#[^\s]+$)/,""));if(!s.hasClass("carousel"))return;var o=e.extend({},s.data(),i.data());var u=i.attr("data-slide-to");if(u)o.interval=false;n.call(s,o);if(u){s.data("bs.carousel").to(u)}t.preventDefault()});e(window).on("load",function(){e('[data-ride="carousel"]').each(function(){var t=e(this);n.call(t,t.data())})})}(jQuery)