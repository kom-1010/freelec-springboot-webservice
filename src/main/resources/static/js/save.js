const saveBtn = document.querySelector(".save-btn");

saveBtn.addEventListener("click", () => {
    const data = {
        title: document.getElementById("title").value,
        author: document.getElementById("author").value,
        content: document.getElementById("content").value
    };

    post_save(data);
});