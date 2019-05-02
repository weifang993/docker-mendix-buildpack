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

    "dojo/text!toggleDwellings/widget/template/toggleDwellings.html"
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, dojoProp, dojoGeometry, dojoClass, dojoStyle, dojoConstruct, dojoArray, lang, dojoText, dojoHtml, dojoEvent, widgetTemplate) {
    "use strict";

    return declare("toggleDwellings.widget.toggleDwellings", [ _WidgetBase, _TemplatedMixin ], {

        templateString: widgetTemplate,


        widgetBase: null,

        // Internal variables.
        _handles: null,
        _contextObj: null,
		mfToExecute: "",

        constructor: function () {
            this._handles = [];
            
        },

        postCreate: function () {
            logger.debug(this.id + ".postCreate");

            // ------------------------ START
                
                $('.toggleHouses').on('click', function(){  
                    if( $(this).is(':checked') ){
                        $('.houses label').addClass('enable');
					    $('.houses .dash').addClass('disable');
                    }else{
                        $('.houses label').removeClass('enable');
                        $('.houses .dash').removeClass('disable');
                    }
					hideRoutesWithNoValue();
                }); 

				$('.toggleApartments').on('click', function(){  
                    if( $(this).is(':checked') ){
                        $('.apartments label').addClass('enable');
                        $('.apartments .dash').addClass('disable');
                    }else{
                        $('.apartments label').removeClass('enable');
                        $('.apartments .dash').removeClass('disable');
                    }
					hideRoutesWithNoValue();
                }); 
				
				$('.toggleFarms').on('click', function(){  
                    if( $(this).is(':checked') ){
                        $('.farms label').addClass('enable');
                        $('.farms .dash').addClass('disable');
                    }else{
                        $('.farms label').removeClass('enable');
                        $('.farms .dash').removeClass('disable');
                    }
					hideRoutesWithNoValue();
                }); 
				
				$('.toggleBusinesses').on('click', function(){  
                    if( $(this).is(':checked') ){
                        $('.businesses label').addClass('enable');
                        $('.businesses .dash').addClass('disable');
                    }else{
                        $('.businesses label').removeClass('enable');
                        $('.businesses .dash').removeClass('disable');
                    }
					hideRoutesWithNoValue();
                }); 
				
				function hideRoutesWithNoValue() {
					$( '.routes li' ).each(function( index, elem ) {
					  // this: the current, raw DOM element
					  // index: the current element's index in the selection
					  // elem: the current, raw DOM element (same as this)
					  $(".houses label, .apartments label, .businesses label, .farms label").filter(function() {
							return $(this).text() === "0";
					  }).css( "text-decoration", "underline" );
					  
					});
				};
				
                
            // ------------------------ END
            
        },

        update: function (obj, callback) {
            logger.debug(this.id + ".update");
            
            this._contextObj = obj;
            this._updateRendering(callback);
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

require(["toggleDwellings/widget/toggleDwellings"]);
