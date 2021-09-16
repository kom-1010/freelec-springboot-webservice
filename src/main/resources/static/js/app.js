const loginMenu = document.querySelector(".login-menu");
const nav = document.querySelector("header nav");

loginMenu.addEventListener("click", () => {
  nav.classList.toggle("active");
});