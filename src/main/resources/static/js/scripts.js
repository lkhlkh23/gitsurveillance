String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".send-msg").click(showProfile);

function showProfile(e) {
    var id = $(this).attr('id');
    console.log(id);
    var url = '/api/slack/' + id;
    console.log(url);
    $.ajax({
        type : 'post',
        url : url,
        dataType : 'json',
        error : function (xhr) {
            console.log("메세지 전송 실패!");
            console.log(xhr);
        },
        success : function (data, status, xhr) {
            console.log("메세지 전송 성공");
        }
    });
}
