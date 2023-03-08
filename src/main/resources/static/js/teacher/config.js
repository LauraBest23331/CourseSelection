$(function (){
    $('.close').click(function() {
        $('.my-alter-info').css('display', 'none')
    })
    $("#form-alter").submit(function (){
        let $courseNo = $('#ipt-account').val()
        let isGo = true;
        $.ajaxSettings.async = false;

        $.post("/CourseSelection/Teacher/isCourseExist", {
            courseNo: $courseNo
        }, function(data){
            if (data.code !== 0) {
                $('.my-alter-info').css("display", "block")
                $('.alter-text').html("添加失败！该课程号号已经存在")
                isGo = false;
            }
        })
        if (!isGo) {
            return false;
        }

        $.ajaxSettings.async = true;
        return isGo;
    })
    $('.btn-alter').click(function() {
        let $courseNo = $(this).attr('data_id')
        $('#ipt-courseNo-alter').val($courseNo)
        $('#ipt-hidden-courseNo-alter').val($courseNo)


        $.post("/CourseSelection/Teacher/getCourse", {
            courseNo: $courseNo
        }, function(data){
            $('#ipt-courseName-alter').val(data.courseName)
            $('#ipt-maxNumber-alter').val(data.maxNumber)
            $('#ipt-hidden-maxNumber-alter').val(data.maxNumber)
            $('#ipt-hidden-nowNumber-alter').val(data.nowNumber)
            $('#ipt-hidden-isCheck-alter').val(0)
            $('#select-beginTime-alter').val(data.beginTime)

        })
    })
})