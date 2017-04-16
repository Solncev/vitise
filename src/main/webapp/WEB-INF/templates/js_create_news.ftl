<script>
    function auto_grow(element) {
        element.style.height = "auto";
        element.style.height = (element.scrollHeight) + "px";
    }

    $('.post-input').click(function () {
        $('#buttons').show();
    });

    $('#submit-btn').click(function () {
        if ($("#text").val() && $.trim($("#text").val()).length > 0) {
            if ($('#date').val() == "" && $('#checkbox').prop('checked')) {
                return false;
            }
            else {
                $("#post-form").submit();
                $('#buttons').hide();
            }
        } else {
            return false;
        }
    });

    $(document).ready($('.datetime').css('visibility', 'hidden'));

    $('#checkbox').click(function () {
        display_datetime();
    });

    var display_datetime = function () {
        if (!$('#checkbox').prop('checked')) {
            $('#post-form').attr('action', '/news');
            $('.datetime').css('visibility', 'hidden');
        }
        else {
            $('.datetime').css('visibility', 'visible');
            $('#post-form').attr('action', '/events/create');
        }
    }


    $('#submit-btn').click(function () {
        $('form[name=post-form]').submit(function (e) {
            if (($('#text').length == 0) || (($('#date').val() == "") && ($('#checkbox').prop('checked')))) {
                return false;
            }
        });
    });

</script>