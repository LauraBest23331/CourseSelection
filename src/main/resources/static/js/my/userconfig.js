$(function (){
    $('.btn-alter').click(function() {
        console.log("进入123")
        let $id = $(this).attr('data_id')
        $('#ipt-id-alter').val($id)
        $.post("/CourseSelection/getUserById", {
            id: $id
        }, function(data){
            $('#ipt-account-alter').val(data.account)
            $('#ipt-username-alter').val(data.username)
            $('#select-sex-alter').val(data.sex)
            $('#ipt-password-alter').val(data.password)

        })
    })
})