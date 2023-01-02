const list = (() => {
    const frm = document.querySelector("#frm");
    const btnSearch = document.querySelector("#btn_search");

    return {
        init: function() {
            btnSearch.addEventListener("click", this.search);
        },
        search: function() {
            frm.page.value = 0;
            frm.submit();
        }
    }
})();

list.init();


