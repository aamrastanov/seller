/**
 * Created by nijat.amrastanov on 17.06.2015.
 */
var Slider = {
    init: function() {
        Slider.cashElements();
        Slider.bindEvents();
    },

    cashElements: function() {
        Slider.fon = '#fon';
        Slider.slider = '#slider';
        Slider.content = Slider.slider + ' .content';
        Slider.galleria = '#galleria';
        Slider.tmpl = '#slider-tmpl';
    },

    bindEvents: function() {
        $(document).on('click', Slider.slider, Slider.sliderClick);
    },

    sliderClick: function() {
        if (!mouseOnElement($(Slider.content))) {
            $(Slider.fon).hide();
            $(Slider.slider).hide();
        }
    },

    setFonHeight: function() {
        var wHeight = $(window).height();
        var wDocument = $(document).height();
        var bHeight = $('body').height();
        var cHeight = $(Slider.content).offset().top + $(Slider.content).height();
        var maxHeight = Math.max(wHeight,wDocument,bHeight,cHeight);
        $(Slider.fon).height(maxHeight);
        $(Slider.slider).height(maxHeight);
    }
};
window.onresize = function() {
    if ($(Slider.fon).is(":visible")) {
        Slider.setFonHeight();
    }
};