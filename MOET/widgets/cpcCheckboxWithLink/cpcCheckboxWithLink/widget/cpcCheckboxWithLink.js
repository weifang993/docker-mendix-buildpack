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
    "dojo/dom-attr",
    "dojo/_base/array",
    "dojo/_base/lang",
    "dojo/text",
    "dojo/html",
    "dojo/_base/event",

    "dojo/text!cpcCheckboxWithLink/widget/template/cpcCheckboxWithLink.html"
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, dojoProp, dojoGeometry, dojoClass, dojoStyle, dojoConstruct, dojoAttr, dojoArray, lang, dojoText, dojoHtml, dojoEvent, widgetTemplate) {
    "use strict";

    return declare("cpcCheckboxWithLink.widget.cpcCheckboxWithLink", [ _WidgetBase, _TemplatedMixin ], {

        templateString: widgetTemplate,

        widgetBase: null,
        _handles: null,
        _contextObj: null,
        _selectFASRoute: null,
        valueOfIdAndFor: null,
        mfToExecute: "",
        
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
            this._createInupitItems();
			this._resetSubscriptions();
			this._executeCallback(callback, "update");
        },


        _createInupitItems: function () {
            //Link
            var urlSourceVal = this._contextObj.get(this.urlSource); 
            var urlTextVal = this._contextObj.get(this.urlText); 
            dojoAttr.set(this.checkBoxLink, "href", urlSourceVal);
            this.checkBoxLink.innerHTML = urlTextVal;

            // Label
            var inputIdandForPrefixVal = this._contextObj.get(this.valueOfIdAndForPrefix);
            var inputIdandForVal = this._contextObj.get(this.valueOfIdAndFor); 
            
            if (inputIdandForPrefixVal == null){
                dojoAttr.set(this.checkBoxInput, "id", inputIdandForVal);
                dojoAttr.set(this.checkBoxLabel, "for", inputIdandForVal);
            }else{
                dojoAttr.set(this.checkBoxInput, "id", inputIdandForPrefixVal + "-" + inputIdandForVal);
                dojoAttr.set(this.checkBoxLabel, "for", inputIdandForPrefixVal + "-" + inputIdandForVal);
            }
        },

        _setupEvents: function () {
            logger.debug(this.id + "._setupEvents");
			
			//runs if change happens directly on the checkbox itself - note Subscription will run if context updates
            this.connect(this.checkBoxInput, "change", function (e) {
				this._selectFASRoute = this._contextObj.get(this.dataSource);
                if (this.mfToExecute !== "") {
                    if (this._isReadOnly || this._contextObj.isReadonlyAttr(this.dataSource)) {
                        return;
                    }
					//toggle the value of the checkbox attribute
					this._selectFASRoute = this._contextObj.get(this.dataSource);
						if( this._selectFASRoute == false  ){
							this._contextObj.set(this.dataSource, true);
						} else {
							this._contextObj.set(this.dataSource, false);
						}
					
                    this._execMf(this.mfToExecute, this._contextObj.getGuid());
                }
            });
        },
		
		_updateCheckBox: function () {
			this._selectFASRoute = this._contextObj.get(this.dataSource);
                if( this._selectFASRoute == false  ){
					this._contextObj.set(this.dataSource, false);
				} else {
					this._contextObj.set(this.dataSource, true);
				}
		},

        _selectAllChildren: function(){
            if( this._selectFASRoute == false  ){
                dojoAttr.set(this.checkBoxInput, "checked", false);
            } else {
                dojoAttr.set(this.checkBoxInput, "checked", true);
            }
        },

        _updateRendering: function (callback) {
            //logger.debug(this.id + "._updateRendering");

            this._updateCheckBox();
			
            // The callback, coming from update, needs to be executed, to let the page know it finished rendering
            this._executeCallback(callback, "_updateRendering");
        },

        _execMf: function (mf, guid, cb) {
            //logger.debug(this.id + "._execMf");
            if (mf && guid) {
                mx.ui.action(mf, {
                    params: {
                        applyto: "selection",
                        guids: [this._contextObj.getGuid()]
                    },
                    progress: "none",
                    callback: function(result) {
                        //console.log("done: " + result);
                    },
                    error: function (error) {
                        //console.debug(error.description);
                    }
                }, this);
            }
        },
		
		// Reset subscriptions.
        _resetSubscriptions: function () {
            logger.debug(this.id + "._resetSubscriptions");
            // Release handles on previous object, if any.
            this.unsubscribeAll();

            // When a mendix object exists create subscribtions.
			if (this._contextObj) {
                this.subscribe({
                    guid: this._contextObj.getGuid(),
                    callback: lang.hitch(this, function (guid) {
                        this._updateRendering();
						this._selectAllChildren();
                    })
				});	
            }
        },

        _executeCallback: function (cb, from) {
            logger.debug(this.id + "._executeCallback" + (from ? " from " + from : ""));
            if (cb && typeof cb === "function") {
                cb();
            }
        }
    });
});

require(["cpcCheckboxWithLink/widget/cpcCheckboxWithLink"]);