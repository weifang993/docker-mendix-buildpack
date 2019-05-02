define([
    "dojo/_base/declare",
    "mxui/widget/_WidgetBase",
    "dijit/_TemplatedMixin",
    "mxui/dom",
    "dojo/dom",
    "dojo/dom-prop",
    "dojo/dom-geometry",
    "dojo/dom-class",
    "dojo/dom-style",
    "dojo/dom-construct",
    "dojo/_base/array",
    "dojo/_base/lang",
    "dojo/text",
    "dojo/html",
    "dojo/_base/event",

    "dojo/text!StickyWidget/widget/template/StickyWidget.html"
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, dojoProp, dojoGeometry, dojoClass, dojoStyle, dojoConstruct, dojoArray, lang, dojoText, dojoHtml, dojoEvent, widgetTemplate) {
    "use strict";

    return declare("StickyWidget.widget.StickyWidget", [ _WidgetBase, _TemplatedMixin ], {
        
        clickType: "mf",
        mfToExecute: "",

        templateString: widgetTemplate,


        widgetBase: null,

        // Internal variables.
        _handles: null,
        _contextObj: null,

        constructor: function () {
            this._handles = [];
        },

        postCreate: function () {
            logger.debug(this.id + ".postCreate");
            this._setupEvents();
        },

        update: function (obj, callback) {
            logger.debug(this.id + ".update");
            
            this._contextObj = obj;
            this._updateRendering(callback);
        },
        
        
        
        resize: function (box) {
            logger.debug(this.id + ".resize");
        },

        uninitialize: function () {
            logger.debug(this.id + ".uninitialize");
        },

        _updateRendering: function (callback) {
            logger.debug(this.id + "._updateRendering");

            if (this._contextObj !== null) {
                dojoStyle.set(this.domNode, "display", "block");
            } else {
                dojoStyle.set(this.domNode, "display", "none");
            }

            this._executeCallback(callback, "_updateRendering");

            // ------------------------ START
            var myVar = setInterval(myTimer, 1000);

            function myTimer() {
                if ( $('.stickyWidget').is(':visible') ) {
                    clearTimeout(myVar);
                    startStickyScroll();
                    alert("is vis")
                }else{
                    $('.stickyFooter').hide();
                }
            }
            // ------------------------ END
        },

        // Shorthand for running a microflow
        _execMf: function (mf, guid, cb) {
            logger.debug(this.id + "._execMf");
            if (mf && guid) {
                mx.ui.action(mf, {
                    params: {
                        applyto: "selection",
                        guids: [guid]
                    },
                    callback: lang.hitch(this, function (objs) {
                        if (cb && typeof cb === "function") {
                            cb(objs);
                        }
                    }),
                    error: function (error) {
                        console.debug(error.description);
                    }
                }, this);
            }
        },

        
        // Shorthand for executing a callback, adds logging to your inspector
        _executeCallback: function (cb, from) {
            logger.debug(this.id + "._executeCallback" + (from ? " from " + from : ""));
            if (cb && typeof cb === "function") {
                cb();
            }
        }
    });
});

require(["StickyWidget/widget/StickyWidget"]);

 // ------------------------ START
 function startStickyScroll (){
    $(window).scroll(function() {

        var top_of_element = $(".cpc-footer-widget").offset().top;
        var bottom_of_element = $(".cpc-footer-widget").offset().top + $(".cpc-footer-widget").outerHeight();
        var bottom_of_screen = $(window).scrollTop() + $(window).innerHeight();
        var top_of_screen = $(window).scrollTop();
    
        clearTimeout($.data(this, 'scrollTimer'));
        $.data(this, 'scrollTimer', setTimeout(function() {
            
            if ((bottom_of_screen > top_of_element) && (top_of_screen < bottom_of_element)){
                // the element is visible, do something
                
            if ( !$('.stickyFooter').hasClass('affix') ){
                
                $(".main-content").animate({ 'padding-bottom':'0'}, 'slow');
                $('.stickyFooter').addClass('affix').hide().fadeIn( 'slow', function(){
                    $('.fullWidthFooter').animate({'opacity': 'show', 'paddingTop': 32}, 2000);
                });
                

            }
    
            } else {
                // the element is not visible, do something else
    
                if ( $('.stickyFooter').hasClass('affix') ){
                $(".main-content").animate({ 'padding-bottom':'50'}, 'slow', function(){
                    $('.stickyFooter').removeClass('affix').hide().fadeIn( 'slow', function(){
                        $('.fullWidthFooter').animate({'opacity': 'show', 'paddingTop': 32}, 2000);
                    });
                });
    
                }
            }
    
        }, 100));
    });
}
// ------------------------ END