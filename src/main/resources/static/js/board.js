let index = {
    init:function(){
        $("#btn-save").on("click", ()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서!!
            this.save();
        });
        $("#btn-delete").on("click", ()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서!!
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서!!
            this.update();
        });

    },
    save:function(){
        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        };

        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function (resp){
            alert('글쓰기가 완료되었습니다.');
            //console.log(resp);
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    deleteById:function(){
        let id = $("#id").text();
        $.ajax({
            type:"DELETE",
            url:"/api/board/"+id,
            dataType:"json"
        }).done(function (resp){
            alert('삭제가 완료되었습니다.');
            //console.log(resp);
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    update:function(){
        let id = $("#id").val();
        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        };

        $.ajax({
            type:"PUT",
            url:"/api/board/"+id,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function (resp){
            alert('글수정이 완료되었습니다.');
            //console.log(resp);
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },


}

index.init();
// joinForm.jsp가 실행되면 index.init() 실행되고, 클릭을 하면 save함수가 실햄됨.