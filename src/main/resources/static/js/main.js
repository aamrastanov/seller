/**
 * Created by nijat.amrastanov on 10.06.2015.
 */
var Main = {
    init: function() {
        Search.init();
        Main.loadTags();
        Main.initItems();
    },

    initItems: function() {
        Item.init();
        //Нельзя вызывать до инициализации Item
        Main.loadItems();
        //этот метод надо вызывать всегда после того, как загрузились обявления
        Item.bindScroll();
        Slider.init();
        Galleria.loadTheme('js/vendor/galleria/galleria.classic.min.js');
    },

    loadTags: function() {
        $(Search.tagTmpl).tmpl(tags).appendTo(Search.tagsUl);
    },

    loadItems: function() {
        $(Item.itemTmpl).tmpl({'items': items}).appendTo(Item.items);
    }
};

var Constant = {
    Regex: {
        UserName: /^[a-zA-Zа-яА-ЯəöüçşığƏÖÜÇŞİĞ\.]{2,20}$/,
        Email: /^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/
    },
    Key: {
        Tab: 9,
        Enter: 13,
        Shift: 16,
        Ctrl: 17,
        Alt: 18,
        End: 35,
        Home: 36,
        Left: 37,
        Up: 38,
        Right: 39,
        Down: 40,
        PageUp: 33,
        Pagedown: 34
    },
    GlobalElement: {
        Wait: ".wait",
        PreWait: ".prewait"
    },
    Dir: {
        RealAva: "/Content/img/photo/ava/real/",
        LargeAvaDir: "/Content/img/photo/ava/large/",
        SmallAvaDir: "/Content/img/photo/ava/small/"
    },
    Command: {
        ChangeLang: "Main/ChangeLang/",
        AddQuestion: "Main/AddQuestion/",
        Register: "Main/Register/"
    },
    CookieName: {
        Auth: "__AUTH_COOKIE_ELANAPP",
        Lang: "__LANG_COOKIE_ELANAPP"
    }
};

var Variable = {
    MouseX: 0,
    MouseY: 0,
    WindowsHeight: $(window).height(),
    WindowsWidth: $(window).width()
}

$("html").mousemove(function (e) {
    Variable.MouseX = e.clientX;
    Variable.MouseY = e.clientY;
});

function getKeyCode(e) {
    if (window.event) { return e.keyCode; }  // IE
    else if (e.which) { return e.which; }
    return null;
}

function mouseOnElement(object) {
    var offset = object.offset();
    if (Variable.MouseX >= offset.left
        && Variable.MouseX <= offset.left + object.width()
        && Variable.MouseY >= offset.top
        && Variable.MouseY <= offset.top + object.height()) {
        return true;
    } else {
        return false;
    }
}

var tags = [
    {
        'id': 1,
        'name': 'videokamera',
        'count': 10
    },
    {
        'id': 2,
        'name': 'telefon',
        'count': 20
    },
    {
        'id': 3,
        'name': 'televizor',
        'count': 5
    },
    {
        'id': 4,
        'name': 'paltar',
        'count': 8
    },
    {
        'id': 5,
        'name': 'xolodilnik',
        'count': 192
    },
    {
        'id': 6,
        'name': 'qapi',
        'count': 2
    },
    {
        'id': 7,
        'name': 'samsung',
        'count': 5
    },
    {
        'id': 8,
        'name': 'noutbuk',
        'count': 8
    },
    {
        'id': 9,
        'name': 'xolodilnik',
        'count': 192
    },
    {
        'id': 10,
        'name': 'qapi',
        'count': 2
    },
    {
        'id': 11,
        'name': 'samsung',
        'count': 5
    },
    {
        'id': 12,
        'name': 'noutbuk',
        'count': 8
    },
    {
        'id': 13,
        'name': 'xolodilnik',
        'count': 192
    },
    {
        'id': 14,
        'name': 'qapi',
        'count': 2
    },
    {
        'id': 15,
        'name': 'samsung',
        'count': 5
    },
    {
        'id': 16,
        'name': 'noutbuk',
        'count': 8
    },
    {
        'id': 17,
        'name': 'komputer',
        'count': 192
    }
];

var items = [
    {
        'id':20,
        'mainPictureLink': 'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
        'otherPictures': [
            'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
            'http://cdn.24.co.za/files/Cms/General/d/2148/7916b84449944e6c85c084e2239e015b.jpg',
            'http://guideimg.alibaba.com/images/shop/85/10/29/2/cool-3d-red-sports-car-case-for-apple-iphone-4-4s-2-piece-plastic-stand-cover_859062.JPG',
            
            'http://guessthe90scheats.com/wp-content/uploads/2014/02/guessthe90s-lrg-131.jpg',
            'http://www.motoroids.com/wp-content/uploads/2014/08/Toyota-FT-1-sports-car-concept-Image-3-300x300.jpg',
            'http://menelikseth.com/wp-content/uploads/2015/05/Volvo-suv-car-300x300.jpg'
        ],
        'price': 130,
        'authorSocialLink': 'https://www.facebook.com/amrastanov',
        'authorPage': 'user.html',
        'authorAvaLink': 'http://lh3.googleusercontent.com/-KK2Hpd79xqQ/AAAAAAAAAAI/AAAAAAAAAQk/k4RLXZRVs5M/s120-c/photo.jpg',
        'authorName': 'A. Nidzhat',
        'authorPhone': '070 621-07-97',
        'description': 'Mashin satilir. Tecili. Gelin alin. Ucuzdur! Cemi 130 manat.',
        'isNew': false,
        'dateTime': '18.06.2015 15:48'
    },
    {
        'id':21,
        'mainPictureLink': 'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
        'otherPictures': [
            'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
            'http://cdn.24.co.za/files/Cms/General/d/2148/7916b84449944e6c85c084e2239e015b.jpg',
            'http://guideimg.alibaba.com/images/shop/85/10/29/2/cool-3d-red-sports-car-case-for-apple-iphone-4-4s-2-piece-plastic-stand-cover_859062.JPG',
            'http://www.consumer-car-reviews.com/wp-content/uploads/2013/03/nicest-cars-2013-300x300.jpg',
            'http://guessthe90scheats.com/wp-content/uploads/2014/02/guessthe90s-lrg-131.jpg',
            'http://www.motoroids.com/wp-content/uploads/2014/08/Toyota-FT-1-sports-car-concept-Image-3-300x300.jpg',
            'http://menelikseth.com/wp-content/uploads/2015/05/Volvo-suv-car-300x300.jpg'
        ],
        'price': 130,
        'authorSocialLink': 'https://www.facebook.com/amrastanov',
        'authorPage': 'user.html',
        'authorAvaLink': 'http://lh3.googleusercontent.com/-KK2Hpd79xqQ/AAAAAAAAAAI/AAAAAAAAAQk/k4RLXZRVs5M/s120-c/photo.jpg',
        'authorName': 'A. Nidzhat',
        'authorPhone': '070 621-07-97',
        'description': 'Mashin satilir. Tecili. Gelin alin. Ucuzdur! Cemi 130 manat.',
        'isNew': false,
        'dateTime': '18.06.2015 15:48'
    },
    {
        'id':22,
        'mainPictureLink': 'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
        'otherPictures': [
            'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
            'http://cdn.24.co.za/files/Cms/General/d/2148/7916b84449944e6c85c084e2239e015b.jpg',
            'http://guideimg.alibaba.com/images/shop/85/10/29/2/cool-3d-red-sports-car-case-for-apple-iphone-4-4s-2-piece-plastic-stand-cover_859062.JPG',
            'http://www.consumer-car-reviews.com/wp-content/uploads/2013/03/nicest-cars-2013-300x300.jpg',
            'http://guessthe90scheats.com/wp-content/uploads/2014/02/guessthe90s-lrg-131.jpg',
            'http://www.motoroids.com/wp-content/uploads/2014/08/Toyota-FT-1-sports-car-concept-Image-3-300x300.jpg',
            'http://menelikseth.com/wp-content/uploads/2015/05/Volvo-suv-car-300x300.jpg'
        ],
        'price': 130,
        'authorSocialLink': 'https://www.facebook.com/amrastanov',
        'authorPage': 'user.html',
        'authorAvaLink': 'http://lh3.googleusercontent.com/-KK2Hpd79xqQ/AAAAAAAAAAI/AAAAAAAAAQk/k4RLXZRVs5M/s120-c/photo.jpg',
        'authorName': 'A. Nidzhat',
        'authorPhone': '070 621-07-97',
        'description': 'Mashin satilir. Tecili. Gelin alin. Ucuzdur! Cemi 130 manat.',
        'isNew': false,
        'dateTime': '18.06.2015 15:48'
    },
    {
        'id':23,
        'mainPictureLink': 'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
        'otherPictures': [
            'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
            'http://cdn.24.co.za/files/Cms/General/d/2148/7916b84449944e6c85c084e2239e015b.jpg',
            'http://guideimg.alibaba.com/images/shop/85/10/29/2/cool-3d-red-sports-car-case-for-apple-iphone-4-4s-2-piece-plastic-stand-cover_859062.JPG',
            'http://www.consumer-car-reviews.com/wp-content/uploads/2013/03/nicest-cars-2013-300x300.jpg',
            'http://guessthe90scheats.com/wp-content/uploads/2014/02/guessthe90s-lrg-131.jpg',
            'http://www.motoroids.com/wp-content/uploads/2014/08/Toyota-FT-1-sports-car-concept-Image-3-300x300.jpg',
            'http://menelikseth.com/wp-content/uploads/2015/05/Volvo-suv-car-300x300.jpg'
        ],
        'price': 130,
        'authorSocialLink': 'https://www.facebook.com/amrastanov',
        'authorPage': 'user.html',
        'authorAvaLink': 'http://lh3.googleusercontent.com/-KK2Hpd79xqQ/AAAAAAAAAAI/AAAAAAAAAQk/k4RLXZRVs5M/s120-c/photo.jpg',
        'authorName': 'A. Nidzhat',
        'authorPhone': '070 621-07-97',
        'description': 'Mashin satilir. Tecili. Gelin alin. Ucuzdur! Cemi 130 manat.',
        'isNew': false,
        'dateTime': '18.06.2015 15:48'
    },
    {
        'id':24,
        'mainPictureLink': 'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
        'otherPictures': [
            'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
            'http://cdn.24.co.za/files/Cms/General/d/2148/7916b84449944e6c85c084e2239e015b.jpg',
            'http://guideimg.alibaba.com/images/shop/85/10/29/2/cool-3d-red-sports-car-case-for-apple-iphone-4-4s-2-piece-plastic-stand-cover_859062.JPG',
            'http://www.consumer-car-reviews.com/wp-content/uploads/2013/03/nicest-cars-2013-300x300.jpg',
            'http://guessthe90scheats.com/wp-content/uploads/2014/02/guessthe90s-lrg-131.jpg',
            'http://www.motoroids.com/wp-content/uploads/2014/08/Toyota-FT-1-sports-car-concept-Image-3-300x300.jpg',
            'http://menelikseth.com/wp-content/uploads/2015/05/Volvo-suv-car-300x300.jpg'
        ],
        'price': 130,
        'authorSocialLink': 'https://www.facebook.com/amrastanov',
        'authorPage': 'user.html',
        'authorAvaLink': 'http://lh3.googleusercontent.com/-KK2Hpd79xqQ/AAAAAAAAAAI/AAAAAAAAAQk/k4RLXZRVs5M/s120-c/photo.jpg',
        'authorName': 'A. Nidzhat',
        'authorPhone': '070 621-07-97',
        'description': 'Mashin satilir. Tecili. Gelin alin. Ucuzdur! Cemi 130 manat.',
        'isNew': false,
        'dateTime': '18.06.2015 15:48'
    },
    {
        'id':25,
        'mainPictureLink': 'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
        'otherPictures': [
            'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
            'http://cdn.24.co.za/files/Cms/General/d/2148/7916b84449944e6c85c084e2239e015b.jpg',
            'http://guideimg.alibaba.com/images/shop/85/10/29/2/cool-3d-red-sports-car-case-for-apple-iphone-4-4s-2-piece-plastic-stand-cover_859062.JPG',
            'http://www.consumer-car-reviews.com/wp-content/uploads/2013/03/nicest-cars-2013-300x300.jpg',
            'http://guessthe90scheats.com/wp-content/uploads/2014/02/guessthe90s-lrg-131.jpg',
            'http://www.motoroids.com/wp-content/uploads/2014/08/Toyota-FT-1-sports-car-concept-Image-3-300x300.jpg',
            'http://menelikseth.com/wp-content/uploads/2015/05/Volvo-suv-car-300x300.jpg'
        ],
        'price': 130,
        'authorSocialLink': 'https://www.facebook.com/amrastanov',
        'authorPage': 'user.html',
        'authorAvaLink': 'http://lh3.googleusercontent.com/-KK2Hpd79xqQ/AAAAAAAAAAI/AAAAAAAAAQk/k4RLXZRVs5M/s120-c/photo.jpg',
        'authorName': 'A. Nidzhat',
        'authorPhone': '070 621-07-97',
        'description': 'Mashin satilir. Tecili. Gelin alin. Ucuzdur! Cemi 130 manat.',
        'isNew': false,
        'dateTime': '18.06.2015 15:48'
    },
    {
        'id':26,
        'mainPictureLink': 'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
        'otherPictures': [
            'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
            'http://cdn.24.co.za/files/Cms/General/d/2148/7916b84449944e6c85c084e2239e015b.jpg',
            'http://guideimg.alibaba.com/images/shop/85/10/29/2/cool-3d-red-sports-car-case-for-apple-iphone-4-4s-2-piece-plastic-stand-cover_859062.JPG',
            'http://www.consumer-car-reviews.com/wp-content/uploads/2013/03/nicest-cars-2013-300x300.jpg',
            'http://guessthe90scheats.com/wp-content/uploads/2014/02/guessthe90s-lrg-131.jpg',
            'http://www.motoroids.com/wp-content/uploads/2014/08/Toyota-FT-1-sports-car-concept-Image-3-300x300.jpg',
            'http://menelikseth.com/wp-content/uploads/2015/05/Volvo-suv-car-300x300.jpg'
        ],
        'price': 130,
        'authorSocialLink': 'https://www.facebook.com/amrastanov',
        'authorPage': 'user.html',
        'authorAvaLink': 'http://lh3.googleusercontent.com/-KK2Hpd79xqQ/AAAAAAAAAAI/AAAAAAAAAQk/k4RLXZRVs5M/s120-c/photo.jpg',
        'authorName': 'A. Nidzhat',
        'authorPhone': '070 621-07-97',
        'description': 'Mashin satilir. Tecili. Gelin alin. Ucuzdur! Cemi 130 manat.',
        'isNew': false,
        'dateTime': '18.06.2015 15:48'
    },
    {
        'id':27,
        'mainPictureLink': 'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
        'otherPictures': [
            'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
            'http://cdn.24.co.za/files/Cms/General/d/2148/7916b84449944e6c85c084e2239e015b.jpg',
            'http://guideimg.alibaba.com/images/shop/85/10/29/2/cool-3d-red-sports-car-case-for-apple-iphone-4-4s-2-piece-plastic-stand-cover_859062.JPG',
            'http://www.consumer-car-reviews.com/wp-content/uploads/2013/03/nicest-cars-2013-300x300.jpg',
            'http://guessthe90scheats.com/wp-content/uploads/2014/02/guessthe90s-lrg-131.jpg',
            'http://www.motoroids.com/wp-content/uploads/2014/08/Toyota-FT-1-sports-car-concept-Image-3-300x300.jpg',
            'http://menelikseth.com/wp-content/uploads/2015/05/Volvo-suv-car-300x300.jpg'
        ],
        'price': 130,
        'authorSocialLink': 'https://www.facebook.com/amrastanov',
        'authorPage': 'user.html',
        'authorAvaLink': 'http://lh3.googleusercontent.com/-KK2Hpd79xqQ/AAAAAAAAAAI/AAAAAAAAAQk/k4RLXZRVs5M/s120-c/photo.jpg',
        'authorName': 'A. Nidzhat',
        'authorPhone': '070 621-07-97',
        'description': 'Mashin satilir. Tecili. Gelin alin. Ucuzdur! Cemi 130 manat.',
        'isNew': false,
        'dateTime': '18.06.2015 15:48'
    },
    {
        'id':28,
        'mainPictureLink': 'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
        'otherPictures': [
            'http://www.comparecarinsurancequotes.co.uk/wp-content/uploads/2011/12/sports-car-insurance.jpg',
            'http://cdn.24.co.za/files/Cms/General/d/2148/7916b84449944e6c85c084e2239e015b.jpg',
            'http://guideimg.alibaba.com/images/shop/85/10/29/2/cool-3d-red-sports-car-case-for-apple-iphone-4-4s-2-piece-plastic-stand-cover_859062.JPG',
            'http://www.consumer-car-reviews.com/wp-content/uploads/2013/03/nicest-cars-2013-300x300.jpg',
            'http://guessthe90scheats.com/wp-content/uploads/2014/02/guessthe90s-lrg-131.jpg',
            'http://www.motoroids.com/wp-content/uploads/2014/08/Toyota-FT-1-sports-car-concept-Image-3-300x300.jpg',
            'http://menelikseth.com/wp-content/uploads/2015/05/Volvo-suv-car-300x300.jpg'
        ],
        'price': 130,
        'authorSocialLink': 'https://www.facebook.com/amrastanov',
        'authorPage': 'user.html',
        'authorAvaLink': 'http://lh3.googleusercontent.com/-KK2Hpd79xqQ/AAAAAAAAAAI/AAAAAAAAAQk/k4RLXZRVs5M/s120-c/photo.jpg',
        'authorName': 'A. Nidzhat',
        'authorPhone': '070 621-07-97',
        'description': 'Mashin satilir. Tecili. Gelin alin. Ucuzdur! Cemi 130 manat.',
        'isNew': false,
        'dateTime': '18.06.2015 15:48'
    }
];