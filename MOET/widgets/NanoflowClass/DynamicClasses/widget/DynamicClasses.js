define([
    "dojo/_base/declare",
    "mxui/widget/_WidgetBase",

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


], function (declare, _WidgetBase, dom, dojoDom, dojoProp, dojoGeometry, dojoClass, dojoStyle, dojoConstruct, dojoArray, lang, dojoText, dojoHtml, dojoEvent) {
    "use strict";

    return declare("DynamicClasses.widget.DynamicClasses", [ _WidgetBase ], {

        // Modeler variables
        nfReturningClass: "",
        mfReturningClass: "",

        // Internal variables.
        _handles: null,
        _handle: null,
        _contextObj: null,
        _elementToApplyTo: null,
		_classList: "";

        constructor: function () {
          this._handles = [];
        },

        postCreate: function () {
          //logger.debug(this.id + ".postCreate");
		 _copyClass(this.domNode,this.domNode.parentNode);
          this._elementToApplyTo = this.domNode.parentNode

        },

        update: function (obj, callback) {
          //logger.debug(this.id + ".update");
          this._contextObj = obj;
          this._resetSubscriptions();
          this._setClasses();
          this._executeCallback(callback, "update");
        },

        resize: function (box) {
          //logger.debug(this.id + ".resize");
        },

        uninitialize: function () {
          //logger.debug(this.id + ".uninitialize");
        },

        // Get classes from microflow and/or nanoflow and apply them to the right element
        _setClasses: function () {

        },

        // Reset subscriptions
        _resetSubscriptions: function () {

        },
		_copyClass: function (toNode, fromNode) {
			var oldClasses = fromNode.classList;
			var newClasses = toNode.classList;
			var oldList = oldClasses.toString().split(" ");
			var newList = newClasses.toString().split(" ");
			for (i=0; i<oldList.length; i++) {
				toNode.classList.remove(oldList[i]);
			}
			for (i=0; i<newList.length; i++) {
				toNode.classList.add(newList[i]);
			}	
        },

        // Shorthand for executing a callback, adds logging to your inspector
        _executeCallback: function (cb, from) {
          //logger.debug(this.id + "._executeCallback" + (from ? " from " + from : ""));
          if (cb && typeof cb === "function") {
            cb();
          }
        }
    });
});

require(["DynamicClasses/widget/DynamicClasses"]);
