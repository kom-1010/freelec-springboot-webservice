function post_save(data){
    $.ajax({
    type: "POST",
    url: "/api/v1/posts",
    dataType: "json",
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
  })
    .done(() => {
      alert("글이 등록되었습니다.");
      window.location.href = "/";
    })
    .fail((error) => {
      alert(JSON.stringify(error));
    });
}

function post_update(id, data){
    $.ajax({
        type: 'PUT',
        url: '/api/v1/posts/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(() => {
        alert('글이 수정되었습니다.');
        window.location.href = '/';
    }).fail((error) => {
        alert(JSON.stringify(error));
    });
}

function post_delete(id){
    $.ajax({
    type: 'DELETE',
    url: '/api/v1/posts/' + id,
    dataType: 'json',
    contentType: 'application/json; charset=utf-8',
    }).done(() => {
        alert('글이 삭제되었습니다.');
    window.location.href = '/';
    }).fail((error) => {
        alert(JSON.stringify(error));
    });
}