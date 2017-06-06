/**
 * Created by nijat.amrastanov on 04.06.2015.
 */
var Item = {
    page: 1,
    init: function() {
        Item.cashElements();
        Item.bindEvents();
        Item.bindScroll();
    },

    cashElements: function() {
        Item.items = '.right-side .content ul';
        Item.item = Item.items + ' li';
        Item.overlay = '.right-side .content ul li .overlay';
        Item.overlayAuthor = Item.overlay + ' .author';
        Item.overlayText = Item.overlay + ' .description';

        Item.itemTmpl = '#item-tmpl';
    },

    bindEvents: function() {
        $(document).on('mouseenter', Item.item, Item.itemMouseOver);
        $(document).on('mouseleave', Item.item, Item.itemMouseOut);
        $(document).on('click', Item.item, Item.itemClick);
    },

    bindScroll: function() {
        $(Item.overlayText).jScrollPane().bind('mousewheel', function(e) {e.preventDefault();});
    },

    itemMouseOver: function() {
        var overlay = $(this).children(Item.overlay);
        overlay.animate({ marginTop: '-' + overlay.height() }, 500);
    },

    itemMouseOut: function() {
        var overlay = $(this).children(Item.overlay);
        overlay.stop().css({ marginTop: '0' });
    },

    itemClick: function() {
        if (mouseOnElement($(Item.overlayAuthor))) return;
        var index = $(this).children('.item-index').val();
        var item = items[index];
        $(Slider.content).html('');
        $(Slider.tmpl).tmpl(item).appendTo(Slider.content);
        Galleria.run(Slider.galleria);
        $(Slider.fon).show();
        $(Slider.slider).show();
        setTimeout(Slider.setFonHeight(), 100);
    },

    loadNexts: function() {
        Item.page++;
        //alert("Load page " + Item.page);
        $(Item.itemTmpl).tmpl({'items': items}).appendTo(Item.items);
    }
};



$(window).scroll(function() {
    if($(window).scrollTop() + $(window).height() == $(document).height()) {
        Item.loadNexts();
    }
});