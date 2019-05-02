$(window).scroll(function() {

  var top_of_element = $(".cpc-footer-widget").offset().top;
  var bottom_of_element = $(".cpc-footer-widget").offset().top + $(".cpc-footer-widget").outerHeight();
  var bottom_of_screen = $(window).scrollTop() + $(window).innerHeight();
  var top_of_screen = $(window).scrollTop();

  clearTimeout($.data(this, 'scrollTimer'));
  $.data(this, 'scrollTimer', setTimeout(function() {
      
      if ((bottom_of_screen > top_of_element) && (top_of_screen < bottom_of_element)){
          // the element is visible, do something
          
      if ( !$('.stickyFooter').hasClass('affix') ){
          
          $(".main-content").animate({ 'padding-bottom':'0'}, 'slow');
          $('.stickyFooter').addClass('affix').hide().fadeIn( 'slow', function(){
              $('.fullWidthFooter').animate({'opacity': 'show', 'paddingTop': 25}, 2000);
          });
          

      }

      } else {
          // the element is not visible, do something else

          if ( $('.stickyFooter').hasClass('affix') ){
          $(".main-content").animate({ 'padding-bottom':'50'}, 'slow', function(){
              $('.stickyFooter').removeClass('affix').hide().fadeIn( 'slow', function(){
                  $('.fullWidthFooter').animate({'opacity': 'show', 'paddingTop': 25}, 2000);
              });
          });

          }
      }

  }, 100));
});