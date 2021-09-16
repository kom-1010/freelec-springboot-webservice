const saveBtn = document.querySelector(".save-btn");
const deleteBtn = document.querySelector(".delete-btn");

saveBtn.addEventListener("click", () => {
    const id = document.getElementById("post-id").value;
    const data = {
        title: document.getElementById("title").value,
        content: document.getElementById("content").value
    };
    post_update(id, data);
});

deleteBtn.addEventListener("click", ()=> {
    const id = document.getElementById("post-id").value;

    post_delete(id);
});