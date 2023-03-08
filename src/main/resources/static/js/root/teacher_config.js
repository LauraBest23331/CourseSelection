$(function (){
    $('.close').click(function() {
        $('.my-alter-info').css('display', 'none')
    })
    $("#form-add").submit(function (){
        let $teacherNo = $('#ipt-studentNo').val()
        let isGo = true;
        $.ajaxSettings.async = false;
        $.post("/CourseSelection/Root/isTeacherExist", {
            teacherNo: $teacherNo
        }, function(data){
            if (data.code !== 0) {
                $('.my-alter-info').css("display", "block")
                $('.alter-text').html("添加失败！该账号已经存在")
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
        console.log("进入3")
        let $id = $(this).attr('data_id')
        $('#ipt-teacherNo-alter').val($id)
        $.post("/CourseSelection/Root/GetTeacherByNo", {
            teacherNo : $id
        }, function(data){
            console.log(data)
            $('#ipt-hidden-teacherNo-alter').val($id)
            $('#ipt-teacherName-alter').val(data.username)
            $('#select-sex-alter').val(data.sex)
            $('#ipt-age-alter').val(data.age)
            $('#ipt-passwd-alter').val(data.passwd)

        })
    })
})