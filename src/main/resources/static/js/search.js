/**
 * Created by nijat.amrastanov on 04.06.2015.
 */
var Search = {
    init: function() {
        Search.cashElements();
        Search.bindEvents();
        //$(Search.tags).jScrollPane().bind('mousewheel', function(e) {e.preventDefault();});
    },

    cashElements: function() {
        Search.input = '#search-input';
        Search.tags = '#tags';
        Search.tagsUl = Search.tags + ' ul';
        Search.tag = Search.tagsUl + ' li';

        Search.filter = ".filter";
        Search.filterClear = Search.filter + " .title .right-part";
        Search.filters = Search.filter + " ul";
        Search.tagFilter = ".filter ul li";
        Search.tagFilterClose = ".filter ul li .close";

        Search.tagTmpl = '#tag-tmpl';
        Search.tagFilterTmpl = '#tag-filter-tmpl';
    },

    bindEvents: function() {
        $(document).on('keyup', Search.input, Search.searchInputKeyUp);
        $(document).on('click', Search.tag, Search.searchTagClick);
        $(document).on('click', Search.tagFilterClose, Search.tagFilterCloseClick);
        $(document).on('click', Search.filterClear, Search.filterClearClick);
    },

    searchInputKeyUp: function(e) {
        var keyCode = getKeyCode(e);
        if (keyCode == Constant.Key.Enter
            || keyCode == Constant.Key.Left || keyCode == Constant.Key.Right
            || keyCode == Constant.Key.Up || keyCode == Constant.Key.Down
            || keyCode == Constant.Key.End || keyCode == Constant.Key.Home
            || keyCode == Constant.Key.Shift || keyCode == Constant.Key.Ctrl
            || keyCode == Constant.Key.Alt
            || keyCode == Constant.Key.Pageup || keyCode == Constant.Key.Pagedown) return;
        Search.showTagsIfContains($(Search.input).val().toLowerCase());
    },

    searchTagClick: function() {
        if (!$(this).hasClass('selected')) {
            $(this).addClass('selected');
            Search.addTagToFilter(this);
        } else {
            Search.deleteTagFromFilter($(this).find('input').val());
        }
    },

    tagFilterCloseClick: function() {
        var id = $(this).next('input').val();
        Search.deleteTagFromFilter(id);
    },

    addTagToFilter: function(tag) {
        var data = {'id':$(tag).find('input').val(), 'name':$(tag).find('.text').html().toLowerCase()}
        $(Search.tagFilterTmpl).tmpl(data).appendTo(Search.filters);
        if (!$(Search.filter ).is(":visible")) {
            $(Search.filter).show();
        }
    },

    filterClearClick: function() {
        $(Search.tag).each(function() {
            var id = $(this).find('input').val();
            Search.deleteTagFromFilter(id);
        });
    },

    deleteTagFromFilter: function(id) {
        $('#tag-' + id).removeClass('selected');
        $('#tag-filter-' + id).remove();
        if ($(Search.filters).find('li').length == 0) {
            $(Search.filter).hide();
        }
    },

    showTagsIfContains: function(searchText) {
        $(Search.tag + ':visible').hide();
        $(Search.tag).each(function() {
            if (Search.isTagContainText(this, searchText)) $(this).show();
        });
    },

    isTagContainText: function(tag, part) {
        return $(tag).find('.text').html().toLowerCase().indexOf(part) >= 0;
    }
};