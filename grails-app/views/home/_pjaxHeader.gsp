<meta http-equiv="x-pjax-version" content="v123">

<jq:jquery>
    $(document).pjax('a[data-pjax]', '#body');
    $(document).on('submit', 'form[data-pjax]', function(event) {
      $.pjax.submit(event, '#body');
    });

    $(document).on('pjax:send', function() {
      $('#loading').show();
    });
    $(document).on('pjax:complete', function() {
      $('#loading').hide();
    });
</jq:jquery>

%{--<jq:jquery>--}%
%{--if($.support.pjax)--}%
%{--alert("$.support.pjax");--}%
%{--</jq:jquery>--}%
