document.addEventListener("DOMContentLoaded", () => {
  const hamburger = document.getElementById("hamburger");
  const nav = document.getElementById("nav");
  const overlay = document.getElementById("overlay");

  if (!hamburger || !nav || !overlay) return;

  hamburger.addEventListener("click", () => {
    nav.classList.add("active");
    overlay.classList.add("active");
  });

  overlay.addEventListener("click", () => {
    nav.classList.remove("active");
    overlay.classList.remove("active");
  });
});

