// Small UI helpers. No framework.
(function () {
    "use strict";

    // Mobile sidebar toggle
    var toggle = document.querySelector("[data-nav-toggle]");
    if (toggle) {
        toggle.addEventListener("click", function () {
            document.body.classList.toggle("nav-open");
        });
        document.addEventListener("click", function (e) {
            if (!document.body.classList.contains("nav-open")) return;
            if (e.target.closest(".sidebar") || e.target.closest("[data-nav-toggle]")) return;
            document.body.classList.remove("nav-open");
        });
    }

    // Segmented controls (purely visual until the views exist)
    document.querySelectorAll(".segmented").forEach(function (seg) {
        seg.addEventListener("click", function (e) {
            var btn = e.target.closest("button");
            if (!btn) return;
            seg.querySelectorAll("button").forEach(function (b) { b.classList.remove("active"); });
            btn.classList.add("active");
        });
    });

    // Live margin (dækningsgrad) preview on the order page
    var salesInput = document.querySelector("[data-sales-price]");
    var costEl = document.querySelector("[data-cost-price]");
    var marginEl = document.querySelector("[data-margin]");
    if (salesInput && costEl && marginEl) {
        var cost = parseFloat(costEl.getAttribute("data-cost-price"));
        var update = function () {
            var sales = parseFloat(String(salesInput.value).replace(/\./g, "").replace(",", "."));
            if (!isFinite(sales) || sales <= 0) { marginEl.textContent = "–"; return; }
            var margin = (sales - cost) / sales * 100;
            marginEl.textContent = margin.toLocaleString("da-DK", { minimumFractionDigits: 1, maximumFractionDigits: 1 }) + " %";
            marginEl.classList.toggle("margin-good", margin >= 25);
            marginEl.classList.toggle("margin-bad", margin < 25);
        };
        salesInput.addEventListener("input", update);
        update();
    }

    // Shed fields enabled only when "Med redskabsrum" is checked
    var shedToggle = document.querySelector("[data-shed-toggle]");
    if (shedToggle) {
        var shedFields = document.querySelectorAll("[data-shed-field]");
        var sync = function () {
            shedFields.forEach(function (f) { f.disabled = !shedToggle.checked; });
        };
        shedToggle.addEventListener("change", sync);
        sync();
    }
})();
