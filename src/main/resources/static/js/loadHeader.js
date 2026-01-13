// 共通ヘッダーを読み込み、タイトルとハンバーガーメニューを制御する
fetch("/html/header.html")
  .then(res => res.text())
  .then(data => {
    // ヘッダーを挿入
    document.getElementById("header").innerHTML = data;

    // ★ AppName を API から取得してヘッダーに反映
    fetch("/madaka/appname")
      .then(res => res.text())
      .then(appName => {
        const siteTitle = document.getElementById("site-title");
        if (siteTitle) {
          siteTitle.textContent = appName;
        }

        const pageTitle = document.getElementById("page-title");
        if (pageTitle) {
          pageTitle.textContent = appName;
        }
      });

    // ハンバーガーメニュー制御
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
