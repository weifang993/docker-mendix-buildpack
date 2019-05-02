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

    "dojo/text!cpcCheckbox/widget/template/cpcCheckbox.html"
], function (declare, _WidgetBase, _TemplatedMixin, dom, dojoDom, dojoProp, dojoGeometry, dojoClass, dojoStyle, dojoConstruct, dojoAttr, dojoArray, lang, dojoText, dojoHtml, dojoEvent, widgetTemplate) {
    "use strict";

    return declare("cpcCheckbox.widget.cpcCheckbox", [ _WidgetBase, _TemplatedMixin ], {

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
		
		// Rerender the interface.
        _updateRendering: function (callback) {
            //logger.debug(this.id + "._updateRendering");

			this._selectAllChildren();

            // The callback, coming from update, needs to be executed, to let the page know it finished rendering
            this._executeCallback(callback, "_updateRendering");
        },


        _createInupitItems: function () {

            // Label
            var inputIdandForVal = this._contextObj.get(this.valueOfIdAndFor); 
            this.checkBoxInput.setAttribute("id", inputIdandForVal);
            this.checkBoxLabel.setAttribute("for", inputIdandForVal);

            var labelTextAttributeVal = this._contextObj.get(this.labelTextViaAttribute); 
            this.checkBoxLabel.innerHTML = labelTextAttributeVal;
        },

        _setupEvents: function () {
            logger.debug(this.id + "._setupEvents");
			
			
			//runs if change happens directly on the checkbox itself
            this.connect(this.checkBoxInput, "change", function (e) {
				this._selectFASRoute = this._contextObj.get(this.dataSource);
				if (this.mfToExecute !== "") {
                    if (this._isReadOnly || this._contextObj.isReadonlyAttr(this.dataSource)) {
                        return;
                    }
					//toggle the checkbox attribute from false to true
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

        _selectAllChildren: function(){
			//make the checkbox reflect the value of it's context
			this._selectFASRoute = this._contextObj.get(this.dataSource);
            if( this._selectFASRoute == false  ){
                dojoAttr.set(this.checkBoxInput, "checked", false);
            } else {
                dojoAttr.set(this.checkBoxInput, "checked", true);
            }
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

require(["cpcCheckbox/widget/cpcCheckbox"]);