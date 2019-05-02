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

    "dojo/text!ListViewClickDetection2/widget/template/ListViewClickDetection2.html"
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, dojoProp, dojoGeometry, dojoClass, dojoStyle, dojoConstruct, dojoArray, lang, dojoText, dojoHtml, dojoEvent, widgetTemplate) {
    "use strict";

    return declare("ListViewClickDetection2.widget.ListViewClickDetection2", [ _WidgetBase, _TemplatedMixin ], {

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

            // ------------------------ START
                
                $('.showAllRoutes').on('click', function(){ 
					$(this).closest('li').addClass('currentItem'); 
                    $('.currentItem div.routes').addClass('active');
					$('.currentItem .hideAllRoutes').addClass('active');
                    $( this ).removeClass('active');

                    //$('.outletpopup .defaultselection').removeClass('active');
                }); 
				
				$('.hideAllRoutes').on('click', function(){ 
					$(this).closest('li').removeClass('currentItem');
                    $('div.routes').removeClass('active');
					$('.showAllRoutes').addClass('active');
                    $( this ).removeClass('active');

                    //$('.outletpopup .defaultselection').removeClass('active');
                }); 
				
                
            // ------------------------ END
            
        },

        update: function (obj, callback) {
            logger.debug(this.id + ".update");
            
            this._contextObj = obj;
            this._updateRendering(callback);

            //$('.scrollable').show();
            //$('.outletpopup .outletprovencescities').hide();
        },
        
        
        // ------------------------ My Function
        _alertUser: function(e){
            alert(e);
        },
        
        // ------------------------ END
        resize: function (box) {
            logger.debug(this.id + ".resize");
        },

        uninitialize: function () {
            logger.debug(this.id + ".uninitialize");
            //$('.scrollable').hide();
            //$('.outletpopup .outletprovencescities').show();
        },

        _updateRendering: function (callback) {
            logger.debug(this.id + "._updateRendering");

            if (this._contextObj !== null) {
                dojoStyle.set(this.domNode, "display", "block");
            } else {
                dojoStyle.set(this.domNode, "display", "none");
            }

            this._executeCallback(callback, "_updateRendering");
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

require(["ListViewClickDetection2/widget/ListViewClickDetection2"]);
