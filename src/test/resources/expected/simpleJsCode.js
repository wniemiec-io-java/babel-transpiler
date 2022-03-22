"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Pen = function () {
    function Pen(name, color, price) {
        _classCallCheck(this, Pen);

        this.name = name;
        this.color = color;
        this.price = price;
    }

    _createClass(Pen, [{
        key: "showPrice",
        value: function showPrice() {
            console.log("Price of " + this.name + " is " + this.price);
        }
    }]);

    return Pen;
}();

var pen1 = new Pen("Marker", "Blue", "$3");
pen1.showPrice();
