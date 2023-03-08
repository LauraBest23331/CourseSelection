$(function (){
    $('.btn-primary').click(function (){
        let checks = []
        $('.i-checks').each(function (){
            let checked = $(this).prop('checked')
            if (checked) {
                checks.push($(this))
            }
        })
        let courseNos = []
        checks.forEach(function (e){
            courseNos.push($(e).parent().next().next().html())

        })
        console.log(courseNos)
        if (courseNos.length === 0) {
            alert("请先勾选再提交！")
            return
        }
        $.post("/CourseSelection/addCourseSelection", {
            courseNos:courseNos
        }, function(data){
            if (data.code === 0) {
                window.location.reload()
            }
            else {

                alert(data.msg)
            }
        })

    })

})