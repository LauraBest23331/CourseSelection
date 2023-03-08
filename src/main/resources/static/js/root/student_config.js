$(function (){
    $('.close').click(function() {
        $('.my-alter-info').css('display', 'none')
    })
    $("#form-add").submit(function (){
        let $studentNo = $('#ipt-studentNo').val()
        let isGo = true;
        $.ajaxSettings.async = false;
        $.post("/CourseSelection/Root/isStudentExist", {
            studentNo: $studentNo
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
        console.log("进入2")
        let $id = $(this).attr('data_id')
        $('#ipt-studentNo-alter').val($id)
        $.post("/CourseSelection/Root/GetStudentByNo", {
            studentNo: $id
        }, function(data){
            console.log(data)
            $('#ipt-hidden-studentNo-alter').val($id)

            $('#ipt-studentName-alter').val(data.username)
            $('#select-sex-alter').val(data.sex)
            $('#ipt-age-alter').val(data.age)
            $('#ipt-passwd-alter').val(data.passwd)

        })
    })
})